package ufcg.ES.RU.service.prato;

import ufcg.ES.RU.Model.DTO.prato.PratoGetDTO;

import java.util.List;

public interface PratoBuscarService {

    public List<PratoGetDTO> listarPratos();
}
