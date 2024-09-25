package ufcg.ES.RU.service.cardapio;

import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioGetDTO;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioPostDTO;

public interface CardapioCriarService {

    public Cardapio criarCardapio(CardapioPostDTO cardapioPostDTO);
}
