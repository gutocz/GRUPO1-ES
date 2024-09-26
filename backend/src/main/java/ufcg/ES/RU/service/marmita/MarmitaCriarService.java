package ufcg.ES.RU.service.marmita;

import ufcg.ES.RU.Model.DTO.marmita.MarmitaPostDTO;
import ufcg.ES.RU.Model.Marmita;

public interface MarmitaCriarService {

    public Marmita criarMarmita(MarmitaPostDTO marmitaPostDTO);

    public void deleteMarmita(Long id);
}
