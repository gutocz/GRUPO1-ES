package ufcg.ES.RU.service.marmita;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.marmita.MarmitaPostDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.Marmita;
import ufcg.ES.RU.Repository.MarmitaRepository;
import ufcg.ES.RU.exceptions.MarmitaNotExistsException;
import ufcg.ES.RU.service.item.ItemBuscarService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarmitaV1CriarService implements MarmitaCriarService {

    @Autowired
    private MarmitaRepository marmitaRepository;

    @Autowired
    private ItemBuscarService itemBuscarService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Marmita criarMarmita(MarmitaPostDTO marmitaPostDTO) {
        List<Item> itens = marmitaPostDTO.getItens().stream()
                .map(item -> modelMapper.map(itemBuscarService.buscarItemPorId(item.getId()), Item.class))
                .collect(Collectors.toList());

        Marmita marmita = Marmita.builder()
                .tipoMarmita(marmitaPostDTO.getTipoMarmita())
                .itens(itens)
                .build();


        return marmitaRepository.save(marmita);
    }

    @Override
    public void deleteMarmita(Long id){
        if (marmitaRepository.existsById(id)){
            Marmita marmita = marmitaRepository.getById(id);
            marmita.setItens(new ArrayList<>());
            marmitaRepository.delete(marmita);
        } else {
            throw new MarmitaNotExistsException(id);
        }
    }
}