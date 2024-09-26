package ufcg.ES.RU.Model.DTO.cardapio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufcg.ES.RU.Model.Item;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioPutItensDTO {


    @JsonProperty("itens")
    private List<Item> itens;
}
