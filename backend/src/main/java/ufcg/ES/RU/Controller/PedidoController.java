package ufcg.ES.RU.Controller;

import ufcg.ES.RU.Model.Pedido;
import ufcg.ES.RU.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criar")
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.criarPedido(pedido.getAluno(), pedido.getTipoMarmita(), pedido.getQuantidade());
    }

    @PostMapping("/confirmar-entrega/{id}")
    public void confirmarEntrega(@PathVariable Long id) {
        pedidoService.confirmarEntrega(id);
    }
}
