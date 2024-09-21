package ufcg.ES.RU.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Repository.ItemRepository;

@Service
public class ItemV1CriarService implements ItemCriarService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Item criarPrato(ItemPostPutDTO itemPostPutDTO){
        Item item = Item.builder()
                .nome(itemPostPutDTO.getNome())
                .descricao(itemPostPutDTO.getDescricao())
                .build();
        return itemRepository.save(item);
    }
}
