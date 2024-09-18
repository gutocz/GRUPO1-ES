package ufcg.ES.RU.service.prato;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import ufcg.ES.RU.Model.DTO.prato.PratoGetDTO;
import ufcg.ES.RU.Model.Prato;
import ufcg.ES.RU.Repository.PratoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PratoV1BuscarService implements PratoBuscarService{

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PratoGetDTO> listarPratos() {
        List<Prato> pratos = pratoRepository.findAll();

        return pratos.stream()
                .map(prato -> modelMapper.map(prato, PratoGetDTO.class))
                .collect(Collectors.toList());
    }
}
