package ufcg.ES.RU.service.marmita;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Model.DTO.marmita.MarmitaPostDTO;
import ufcg.ES.RU.Model.Item;
import ufcg.ES.RU.Model.Marmita;
import ufcg.ES.RU.Repository.MarmitaRepository;
import ufcg.ES.RU.service.item.ItemBuscarService;
import ufcg.ES.RU.service.item.ItemMudarService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MarmitaV1CriarService implements MarmitaCriarService {

    @Autowired
    private MarmitaRepository marmitaRepository;

    @Autowired
    private ItemBuscarService itemBuscarService;

    @Autowired
    private ItemMudarService itemMudarService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Marmita criarMarmita(MarmitaPostDTO marmitaPostDTO) {
        Set <Item> itens = new HashSet<>();

        Marmita marmita = Marmita.builder()
                .tipoMarmita(marmitaPostDTO.getTipoMarmita())
                .itens(itens)
                .build();

        Marmita marmitaNow = marmitaRepository.save(marmita);

        itens = marmitaPostDTO.getItens().stream()
                .map(item -> {
                    Item itemNow = modelMapper.map(itemBuscarService.buscarItemPorId(item.getId()), Item.class);
                    itemMudarService.adicionarMarmita(itemNow.getId(), marmitaNow.getId());

                    return itemNow;
                })
                .collect(Collectors.toSet());

        marmitaNow.setItens(itens);


        return marmitaRepository.save(marmitaNow);
    }
}