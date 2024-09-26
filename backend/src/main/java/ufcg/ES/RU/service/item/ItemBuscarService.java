package ufcg.ES.RU.service.item;

import ufcg.ES.RU.Model.DTO.item.ItemGetDTO;

import java.util.List;

public interface ItemBuscarService {

    public List<ItemGetDTO> listarItem();

    public ItemGetDTO buscarItemPorId(Long id);

}
