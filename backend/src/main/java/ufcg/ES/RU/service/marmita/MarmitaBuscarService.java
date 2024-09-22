package ufcg.ES.RU.service.marmita;

import ufcg.ES.RU.Model.DTO.marmita.MarmitaGetDTO;

import java.util.List;

public interface MarmitaBuscarService {

    public List<MarmitaGetDTO> listarTodasMarmitas();

    public MarmitaGetDTO buscarMarmitaPorId(Long id);
}
