package ufcg.ES.RU.service.marmita;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.DTO.marmita.MarmitaGetDTO;
import ufcg.ES.RU.Model.Marmita;
import ufcg.ES.RU.Repository.MarmitaRepository;
import ufcg.ES.RU.exceptions.MarmitaNotExistsException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarmitaV1BuscarService implements MarmitaBuscarService {

    @Autowired
    private MarmitaRepository marmitaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MarmitaGetDTO> listarTodasMarmitas() {
        List<Marmita> marmitas = marmitaRepository.findAll();

        return marmitas.stream()
                .map(marmita -> modelMapper.map(marmita, MarmitaGetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MarmitaGetDTO buscarMarmitaPorId(Long id) {
        if(marmitaRepository.existsById(id)){
            Marmita marmita = marmitaRepository.findById(id).get();
            return modelMapper.map(marmita, MarmitaGetDTO.class);
        } else {
            throw new MarmitaNotExistsException(id);
        }

    }
}
