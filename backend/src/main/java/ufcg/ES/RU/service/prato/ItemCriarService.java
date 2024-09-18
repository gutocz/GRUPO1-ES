package ufcg.ES.RU.service.prato;

import ufcg.ES.RU.Model.DTO.prato.ItemPostPutDTO;
import ufcg.ES.RU.Model.Item;

public interface ItemCriarService {

    public Item criarPrato(ItemPostPutDTO itemPostPutDTO);

}
