package ufcg.ES.RU.service.item;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Repository.ItemRepository;
import ufcg.ES.RU.exceptions.ItemNotExistException;

@Service
public class ItemV1MudarService implements ItemMudarService {

    @Autowired
    private ItemRepository itemRepository;

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
}
