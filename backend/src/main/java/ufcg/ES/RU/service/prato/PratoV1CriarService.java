package ufcg.ES.RU.service.prato;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.DTO.prato.PratoPostPutDTO;
import ufcg.ES.RU.Model.Prato;
import ufcg.ES.RU.Repository.PratoRepository;

@Service
public class PratoV1CriarService implements PratoCriarService{

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Prato criarPrato(PratoPostPutDTO pratoPostPutDTO){
        return pratoRepository.save(modelMapper.map(pratoRepository, Prato.class));
    }
}
