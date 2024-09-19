package ufcg.ES.RU.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity
@Data
@Builder

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno; // Relacionamento com o Aluno

    private String tipoMarmita; // "Vegana", "Padrão", etc.

    private int quantidade;

    private boolean pago;

    private boolean entregue;

    private LocalDateTime dataHoraPedido;

}
