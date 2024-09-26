package ufcg.ES.RU.service.cardapio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioPostDTO;
import ufcg.ES.RU.Model.DiaDaSemana;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.TipoRefeicao;
import ufcg.ES.RU.Repository.CardapioRepository;
import ufcg.ES.RU.service.item.ItemBuscarService;
import ufcg.ES.RU.service.item.ItemMudarService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardapioV1CriarService implements CardapioCriarService{

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ItemBuscarService itemBuscarService;

    @Autowired
    private ItemMudarService itemMudarService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Cardapio criarCardapio(CardapioPostDTO cardapioPostDTO) {

        Set<Item> itens = new HashSet<>();

        Cardapio cardapio = Cardapio.builder()
                .diaDaSemana(cardapioPostDTO.getDiaDaSemana(cardapioPostDTO.getDiaDaSemana()))
                .tipoRefeicao(cardapioPostDTO.getTipoRefeicao(cardapioPostDTO.getTipoRefeicao()))
                .itens(itens)
                .build();

        Cardapio cardapioNow =  cardapioRepository.save(cardapio);

        itens = cardapioPostDTO.getItens().stream()
                .map(item -> {
                    Item itemNow = modelMapper.map(itemBuscarService.buscarItemPorId(item.getId()), Item.class);
                    itemMudarService.adicionarCardapio(itemNow.getId(), cardapioNow.getId());

                    return itemNow;
                })
                .collect(Collectors.toSet());

        cardapioNow.setItens(itens);

        return cardapioRepository.save(cardapioNow);

    }
}