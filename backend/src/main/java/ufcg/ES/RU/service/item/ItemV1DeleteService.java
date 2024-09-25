package ufcg.ES.RU.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Repository.ItemRepository;
import ufcg.ES.RU.exceptions.ItemNotExistException;

@Service
public class ItemV1DeleteService implements ItemDeleteService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void deleteItem(Long id) {
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
        } else {
            throw new ItemNotExistException(id);
        }
    }
}
