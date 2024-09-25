package ufcg.ES.RU.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Repository.ItemRepository;
import ufcg.ES.RU.Repository.MarmitaRepository;
import ufcg.ES.RU.exceptions.ItemNotExistException;

@Service
public class ItemV1DeleteService implements ItemDeleteService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MarmitaRepository marmitaRepository;

    @Override
    public void deleteItem(Long id) {
        if(itemRepository.existsById(id)){
            Item item = itemRepository.getById(id);
            marmitaRepository.findAll().forEach(marmita -> marmita.getItens().remove(item));
            itemRepository.deleteById(id);
        } else {
            throw new ItemNotExistException(id);
        }
    }
}
