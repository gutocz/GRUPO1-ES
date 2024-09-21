package ufcg.ES.RU.service.item;

import ufcg.ES.RU.Model.DTO.item.ItemPostPutDTO;
import ufcg.ES.RU.Model.Item;

public interface ItemCriarService {

    public Item criarPrato(ItemPostPutDTO itemPostPutDTO);

}
