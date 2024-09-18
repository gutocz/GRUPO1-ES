package ufcg.ES.RU.service.prato;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.DTO.prato.ItemGetDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Repository.ItemRepository;
import ufcg.ES.RU.exceptions.ItemNotExistException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemV1BuscarService implements ItemBuscarService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ItemGetDTO> listarItem() {
        List<Item> items = itemRepository.findAll();

        return items.stream()
                .map(item -> modelMapper.map(item, ItemGetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemGetDTO buscarItemPorId(Long id){
        if (itemRepository.existsById(id)){
            Item item = itemRepository.findById(id).get();
            return ItemGetDTO.builder()
                    .id(item.getId())
                    .nome(item.getNome())
                    .descricao(item.getDescricao())
                    .build();
        } else {
            throw new ItemNotExistException();
        }
    }


}
