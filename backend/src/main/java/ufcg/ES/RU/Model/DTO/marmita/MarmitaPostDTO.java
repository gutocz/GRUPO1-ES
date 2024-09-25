package ufcg.ES.RU.Model.DTO.marmita;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.TipoMarmita;
import ufcg.ES.RU.Model.TipoRefeicao;
import ufcg.ES.RU.exceptions.InvalidEnumValueException;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarmitaPostDTO {

    @JsonProperty("tipoRefeicao")
    private TipoMarmita tipoMarmita;

    @JsonProperty("itens")
    private List<Item> itens;

    public TipoMarmita getTipoMarmita(String tipoRefeiceiStr){
        try {
            return TipoMarmita.valueOf(tipoRefeiceiStr.toUpperCase().trim());
        } catch (IllegalArgumentException e){
            throw new InvalidEnumValueException("Tipo refeicao inválido: " + tipoRefeiceiStr);
        }
    }
}
