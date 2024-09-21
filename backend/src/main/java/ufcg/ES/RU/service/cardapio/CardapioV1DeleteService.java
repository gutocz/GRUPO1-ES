package ufcg.ES.RU.service.cardapio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufcg.ES.RU.Model.Cardapio;
import ufcg.ES.RU.Repository.CardapioRepository;
import ufcg.ES.RU.exceptions.CardapioNotExistException;

@Service
public class CardapioV1DeleteService implements CardapioDeleteService{

    @Autowired
    private CardapioRepository cardapioRepository;

    @Override
    public void deleteCardapio(Long id) {
        if (cardapioRepository.existsById(id)){
            cardapioRepository.deleteById(id);
        }
        else {
            throw new CardapioNotExistException(id);
        }
    }
}
