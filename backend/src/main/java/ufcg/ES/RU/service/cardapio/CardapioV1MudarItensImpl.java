package ufcg.ES.RU.service.cardapio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioGetDTO;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioPutItensDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Repository.CardapioRepository;
import ufcg.ES.RU.exceptions.CardapioNotExistException;
import ufcg.ES.RU.service.item.ItemBuscarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardapioV1MudarItensImpl implements CardapioMudarItens {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemBuscarService itemBuscarService;

    @Override
    public CardapioGetDTO adicionarItens(CardapioPutItensDTO cardapioPutItensDTO, Long id) {
        if (cardapioRepository.existsById(id)){
            Cardapio cardapio = cardapioRepository.getById(id);

            List<Item> itens = cardapioPutItensDTO.getItens().stream()
                    .map(item -> modelMapper.map(itemBuscarService.buscarItemPorId(item.getId()), Item.class))
                    .collect(Collectors.toList());

            cardapio.setItens(itens);

            cardapioRepository.save(cardapio);

            return modelMapper.map(cardapio, CardapioGetDTO.class);
        } else {
            throw new CardapioNotExistException(id);
        }
    }
}
