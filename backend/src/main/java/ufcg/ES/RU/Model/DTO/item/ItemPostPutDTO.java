package ufcg.ES.RU.Model.DTO.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPostPutDTO {

    @JsonProperty("nome")
    @NotBlank(message = "nome é necessario")
    private String nome;

    @JsonProperty("descricao")
    @NotBlank(message = "descrição é necessario")
    private String descricao;
}
