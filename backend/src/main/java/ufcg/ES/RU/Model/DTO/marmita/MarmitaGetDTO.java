package ufcg.ES.RU.Model.DTO.marmita;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.TipoMarmita;
import ufcg.ES.RU.Model.TipoRefeicao;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarmitaGetDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("tipoRefeicao")
    private TipoMarmita tipoMarmita;

    @JsonProperty("itens")
    private List<Item> itens;

}
