package ufcg.ES.RU.service.item;

import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;

public interface ItemMudarService {

    public ItemPostPutDTO mudarItem(ItemPostPutDTO itemPostPutDTO, Long id);

}
