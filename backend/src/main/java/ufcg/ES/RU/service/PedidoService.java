    package ufcg.ES.RU.service;
    
    import ufcg.ES.RU.Model.Aluno;
    import ufcg.ES.RU.Model.Pedido;
    import ufcg.ES.RU.Repository.PedidoRepository;
    import ufcg.ES.RU.Repository.UsuarioRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import ufcg.ES.RU.service.Token.TokenGenerator;
    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.stream.Collectors;
    
    @Service
    public class PedidoService {
    
        @Autowired
        public PedidoRepository pedidoRepository;
    
        @Autowired
        public UsuarioRepository usuarioRepository;
    
        @Autowired
        public AlunoService alunoService;
    
        private int marmitasPadraoAEntregar = 0;
        private int marmitasVeganasAEntregar = 0;
    
        // Cria um novo pedido para marmita
        public Pedido criarPedido(String matricula, String tipo) {
            Aluno aluno = usuarioRepository.findById(matricula)
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    
            double valorMarmita = (tipo.equals("Vegana")) ? 10.0 : 8.0; // Exemplo de preços
    
            // Verifica se o aluno tem saldo suficiente e decrementar o saldo
            alunoService.decrementarSaldo(aluno.getMatricula(), valorMarmita);
    
            // Cria o pedido com o token gerado
            Pedido pedido = Pedido.builder()
                    .aluno(aluno)
                    .tipo(tipo)
                    .entregue(false) // Pedido inicialmente não foi entregue
                    .dataHoraPedido(LocalDateTime.now())
                    .token(TokenGenerator.gerarToken()) // Gera o token
                    .build();
    
            pedidoRepository.save(pedido);
    
            // Atualiza a contagem de marmitas
            if (tipo.equals("Vegana")) {
                marmitasVeganasAEntregar++;
            } else {
                marmitasPadraoAEntregar++;
            }
    
            return pedido;
        }
    
        // Pedido para comer no local
        public Pedido criarPedidoComerNoLocal(String matricula) {
            Aluno aluno = usuarioRepository.findById(matricula)
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    
            double valorRefeicao = 12.0; // Exemplo de valor fixo para comer no local
    
            // Verifica se o aluno tem saldo suficiente e decrementar o saldo
            alunoService.decrementarSaldo(aluno.getMatricula(), valorRefeicao);
    
            // Cria o pedido para comer no local
            Pedido pedido = Pedido.builder()
                    .aluno(aluno)
                    .tipo("Local")
                    .entregue(false) // Inicialmente não entregue
                    .dataHoraPedido(LocalDateTime.now())
                    .token(TokenGenerator.gerarToken()) // Gera o token
                    .build();
    
            pedidoRepository.save(pedido);
    
            return pedido;
        }
    
        // Confirmar entrega com o token
        public Pedido confirmarEntrega(String token) {
            Pedido pedido = pedidoRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    
            if (pedido.isEntregue()) {
                throw new RuntimeException("Pedido já foi entregue.");
            }
    
            // Marca o pedido como entregue
            pedido.setEntregue(true);
            pedidoRepository.save(pedido);
    
            // Decrementa a contagem de marmitas a serem entregues
            if (pedido.getTipo().equals("Vegana")) {
                marmitasVeganasAEntregar--;
            } else if (pedido.getTipo().equals("Padrão")) {
                marmitasPadraoAEntregar--;
            }
    
            return pedido;
        }
    
        // Método para buscar tokens de pedidos não entregues de um aluno
        public List<String> buscarTokensAtivosPorAluno(String alunoId) {
            List<Pedido> pedidosAtivos = pedidoRepository.findByAlunoIdAndEntregueFalse(alunoId);
            return pedidosAtivos.stream()
                    .map(Pedido::getToken) // Mapeia os pedidos para seus tokens
                    .collect(Collectors.toList());
        }
    
        // Buscar pedido por token
        public Pedido buscarPorToken(String token) {
            return pedidoRepository.findByToken(token)
                    .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        }
    }
    
