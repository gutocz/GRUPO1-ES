package ufcg.ES.RU.service;

import ufcg.ES.RU.Model.Pagamento;
import ufcg.ES.RU.Model.Pedido;
import ufcg.ES.RU.Repository.PagamentoRepository;
import ufcg.ES.RU.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pagamento processarPagamento(Long pedidoId, double valor) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // Usando o Builder para criar o objeto Pagamento
        Pagamento pagamento = Pagamento.builder()
                .pedido(pedido)
                .valor(valor)
                .build();

        pedido.setEntregue(true);
        pedidoRepository.save(pedido);

        return pagamentoRepository.save(pagamento);
    }
}
