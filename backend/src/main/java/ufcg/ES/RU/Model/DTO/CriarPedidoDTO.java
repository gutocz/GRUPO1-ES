package ufcg.ES.RU.Model.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CriarPedidoDTO {

    // Getters e Setters
    private String Matricula;
    private String tipo; // "Vegana", "Padrão" ou "Local"

}
