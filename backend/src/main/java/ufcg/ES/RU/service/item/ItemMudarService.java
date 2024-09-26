package ufcg.ES.RU.service.item;

import ufcg.ES.RU.Model.DTO.item.ItemGetDTO;
import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;

public interface ItemMudarService {

    public ItemPostPutDTO mudarItem(ItemPostPutDTO itemPostPutDTO, Long id);

    public ItemGetDTO adicionarCardapio(Long idItem, Long idCardapio);

    public ItemGetDTO adicionarMarmita(Long idItem, Long idCardapio);

}
