package ufcg.ES.RU.service.prato;

import ufcg.ES.RU.Model.DTO.prato.PratoPostPutDTO;
import ufcg.ES.RU.Model.Prato;

public interface PratoCriarService {

    public Prato criarPrato(PratoPostPutDTO pratoPostPutDTO);

}
