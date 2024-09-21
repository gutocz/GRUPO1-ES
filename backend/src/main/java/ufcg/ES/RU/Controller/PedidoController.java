package ufcg.ES.RU.Controller;

import ufcg.ES.RU.Model.DTO.CriarPedidoDTO;
import ufcg.ES.RU.Model.DTO.PedidoConfirmacaoDTO;
import ufcg.ES.RU.Model.Pedido;
import ufcg.ES.RU.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Criar pedido de marmita
    @PostMapping("/marmita")
    public ResponseEntity<Pedido> criarPedidoMarmita(@RequestBody CriarPedidoDTO criarPedidoDTO) {
        Pedido novoPedido = pedidoService.criarPedido(criarPedidoDTO.getMatricula(), criarPedidoDTO.getTipo());
        return ResponseEntity.ok(novoPedido);
    }

    // Criar pedido para comer no local
    @PostMapping("/local")
    public ResponseEntity<Pedido> criarPedidoComerNoLocal(@RequestBody CriarPedidoDTO criarPedidoDTO) {
        Pedido novoPedido = pedidoService.criarPedidoComerNoLocal(criarPedidoDTO.getMatricula());
        return ResponseEntity.ok(novoPedido);
    }


    // Confirmar entrega via token
    @PostMapping("/confirmar-entrega")
    public ResponseEntity<PedidoConfirmacaoDTO> confirmarEntrega(@RequestParam("token") String token) {
        Pedido pedido = pedidoService.confirmarEntrega(token);
        PedidoConfirmacaoDTO confirmacaoDTO = new PedidoConfirmacaoDTO();
        confirmacaoDTO.setToken(pedido.getToken());
        confirmacaoDTO.setEntregue(pedido.isEntregue());
        return ResponseEntity.ok(confirmacaoDTO);
    }

    // Buscar um pedido pelo token
    @GetMapping("/buscar-por-token")
    public ResponseEntity<Pedido> buscarPedidoPorToken(@RequestParam("token") String token) {
        Pedido pedido = pedidoService.buscarPorToken(token);
        return ResponseEntity.ok(pedido);
    }

    // Endpoint para buscar tokens de pedidos não entregues de um aluno
    @GetMapping("/ativos/{alunoId}")
    public ResponseEntity<List<String>> buscarTokensAtivos(@PathVariable("alunoId") String alunoId) {
        List<String> tokensAtivos = pedidoService.buscarTokensAtivosPorAluno(alunoId);
        return ResponseEntity.ok(tokensAtivos);
    }
}

