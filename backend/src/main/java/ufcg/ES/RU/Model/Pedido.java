package ufcg.ES.RU.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno; // Relacionamento com o Aluno

    private String tipo; // "Vegana", "Padrão", ou "Local"

    private LocalDateTime dataHoraPedido;

    private boolean entregue; // Boolean que indica se o pedido foi entregue

    private String token; // Token gerado para o pedido

    public static Pedido criarPedido(Aluno aluno, String tipo) {
        return Pedido.builder()
                .aluno(aluno)
                .tipo(tipo)
                .entregue(false)
                .dataHoraPedido(LocalDateTime.now())
                .build();
    }
}

