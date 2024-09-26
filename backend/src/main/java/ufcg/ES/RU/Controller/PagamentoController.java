package ufcg.ES.RU.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ufcg.ES.RU.Model.Pagamento;
import ufcg.ES.RU.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/processar/{pedidoId}")
    public Pagamento processarPagamento(@PathVariable Long pedidoId, @RequestParam double valor) {
        return pagamentoService.processarPagamento(pedidoId, valor);
    }
}
