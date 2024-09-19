package ufcg.ES.RU.service;

import ufcg.ES.RU.Model.Aluno;
import ufcg.ES.RU.Model.Pedido;
import ufcg.ES.RU.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Aluno aluno, String tipoMarmita, int quantidade) {
        Pedido pedido = Pedido.builder()
                .aluno(aluno)
                .tipoMarmita(tipoMarmita)
                .quantidade(quantidade)
                .pago(false)
                .entregue(false)
                .dataHoraPedido(LocalDateTime.now())
                .build();

        return pedidoRepository.save(pedido);
    }

    public void confirmarEntrega(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setEntregue(true);
        pedidoRepository.save(pedido);
    }
}
