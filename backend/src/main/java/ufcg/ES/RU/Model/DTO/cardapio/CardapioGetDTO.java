package ufcg.ES.RU.Model.DTO.cardapio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufcg.ES.RU.Model.DTO.item.ItemGetDTO;
import ufcg.ES.RU.Model.Item;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioGetDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("diaDaSemana")
    private String diaDaSemana;

    @JsonProperty("tipoRefeicao")
    private String tipoRefeicao;

    @JsonProperty("itens")
    private Set<ItemGetDTO> items;

}
