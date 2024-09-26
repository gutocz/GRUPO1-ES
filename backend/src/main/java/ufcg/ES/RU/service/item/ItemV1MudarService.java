package ufcg.ES.RU.service.item;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.item.ItemGetDTO;
import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.Marmita;
import ufcg.ES.RU.Repository.ItemRepository;
import ufcg.ES.RU.exceptions.CardapioNotExistException;
import ufcg.ES.RU.exceptions.ItemNotExistException;
import ufcg.ES.RU.exceptions.MarmitaNotExistsException;
import ufcg.ES.RU.service.cardapio.CardapioBuscarService;
import ufcg.ES.RU.service.marmita.MarmitaBuscarService;

import javax.sound.midi.MidiMessage;
import java.util.Set;

@Service
public class ItemV1MudarService implements ItemMudarService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CardapioBuscarService cardapioBuscarService;

    @Autowired
    private MarmitaBuscarService marmitaBuscarService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemPostPutDTO mudarItem(ItemPostPutDTO itemPostPutDTO, Long id) {
        if(itemRepository.existsById(id)) {
            Item item = Item.builder()
                    .id(id)
                    .nome(itemPostPutDTO.getNome())
                    .descricao(itemPostPutDTO.getDescricao())
                    .build();

            itemRepository.save(item);

            return modelMapper.map(item, ItemPostPutDTO.class);

        } else {
            throw new ItemNotExistException(id);
        }
    }

    @Override
    public ItemGetDTO adicionarCardapio(Long idItem, Long idCardapio) {
        if(itemRepository.existsById(idItem)) {
            Item item = itemRepository.getById(idItem);
            Set<Cardapio> cardapios = item.getCadapios();
            cardapios.add(modelMapper.map(cardapioBuscarService.buscarCardapioPorId(idCardapio), Cardapio.class));
            item.setCadapios(cardapios);

            itemRepository.save(item);

            return modelMapper.map(item, ItemGetDTO.class);

        } else {
            throw new CardapioNotExistException(idCardapio);
        }
    }

    @Override
    public ItemGetDTO adicionarMarmita(Long idItem, Long idMarmita) {
        if(itemRepository.existsById(idItem)) {
            Item item = itemRepository.getById(idItem);
            Set<Marmita> marmitas = item.getMarmitas();
            marmitas.add(modelMapper.map(marmitaBuscarService.buscarMarmitaPorId(idMarmita), Marmita.class));
            item.setMarmitas(marmitas);

            itemRepository.save(item);

            return modelMapper.map(item, ItemGetDTO.class);

        } else {
            throw new MarmitaNotExistsException(idMarmita);
        }
    }


}
