package ufcg.ES.RU.service.cardapio;

import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioGetDTO;

import java.util.List;


public interface CardapioBuscarService {

    public List<CardapioGetDTO> listarTodosCardapios();

    public CardapioGetDTO buscarCardapioPorId(Long id);

    public List<CardapioGetDTO> buscarCardapioPorDia(String diaSemana);
}
