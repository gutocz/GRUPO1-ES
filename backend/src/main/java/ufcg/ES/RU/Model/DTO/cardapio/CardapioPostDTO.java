package ufcg.ES.RU.Model.DTO.cardapio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ufcg.ES.RU.Model.DiaDaSemana;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.TipoRefeicao;
import ufcg.ES.RU.exceptions.InvalidEnumValueException;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardapioPostDTO {


    @JsonProperty("diaDaSemana")
    private String diaDaSemana;

    @JsonProperty("tipoRefeicao")
    private String tipoRefeicao;

    @JsonProperty("itens")
    private List<Item> itens;

    public TipoRefeicao getTipoRefeicao(String tipoRefeicaoStr) {
        try {
            return TipoRefeicao.valueOf(tipoRefeicaoStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Tipo de refeição inválido: " + tipoRefeicaoStr);
        }
    }

    public DiaDaSemana getDiaDaSemana(String diaDaSemanaStr) {
        try {
            return DiaDaSemana.valueOf(diaDaSemanaStr.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Dia da semana inválido: " + diaDaSemanaStr);
        }
    }

}
