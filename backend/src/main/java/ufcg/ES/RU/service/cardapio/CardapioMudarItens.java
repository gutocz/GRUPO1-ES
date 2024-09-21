package ufcg.ES.RU.service.cardapio;

import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioGetDTO;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioPutItensDTO;
import ufcg.ES.RU.Model.Item;

public interface CardapioMudarItens {

    public CardapioGetDTO adicionarItens(CardapioPutItensDTO cardapioPutItensDTO, Long id);
}
