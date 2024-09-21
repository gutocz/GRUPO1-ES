package ufcg.ES.RU.service.cardapio;

import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.cardapio.CardapioGetDTO;
import ufcg.ES.RU.Model.DTO.item.ItemGetDTO;
import ufcg.ES.RU.Repository.CardapioRepository;
import ufcg.ES.RU.exceptions.CardapioNotExistException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardapioV1BuscarService implements CardapioBuscarService {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CardapioGetDTO> listarTodosCardapios() {
        List<Cardapio> cardapios = cardapioRepository.findAll();

        return cardapios.stream()
                .map(cardapio -> buscarCardapioPorId(cardapio.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CardapioGetDTO buscarCardapioPorId(Long id) {
        if (cardapioRepository.existsById(id)){
            Cardapio cardapio = cardapioRepository.findById(id).get();

            List<ItemGetDTO> itens = cardapio.getItens().stream()
                    .map(item -> modelMapper.map(item, ItemGetDTO.class))
                    .collect(Collectors.toList());

            return CardapioGetDTO.builder()
                    .id(cardapio.getId())
                    .diaDaSemana(cardapio.getDiaDaSemana().toString())
                    .tipoRefeicao(cardapio.getTipoRefeicao().toString())
                    .items(itens)
                    .build();
        } else {
            throw new CardapioNotExistException(id);
        }
    }

    @Override
    public List<CardapioGetDTO> buscarCardapioPorDia(String diaSemana) {
        List<Cardapio> cardapios = cardapioRepository.findAll().stream()
                .filter(cardapio -> cardapio.getDiaDaSemana().toString().equals(diaSemana))
                .collect(Collectors.toList());

        return cardapios.stream()
                .map(cardapio -> modelMapper.map(cardapio, CardapioGetDTO.class))
                .collect(Collectors.toList());
    }
}
