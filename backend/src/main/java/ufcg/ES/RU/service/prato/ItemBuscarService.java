package ufcg.ES.RU.service.prato;

import ufcg.ES.RU.Model.DTO.prato.ItemGetDTO;

import java.util.List;

public interface ItemBuscarService {

    public List<ItemGetDTO> listarItem();

    public ItemGetDTO buscarItemPorId(Long id);

}
