package ufcg.ES.RU.exceptions;

public class CardapioNotExistException extends RUException{

    public CardapioNotExistException(Long id){
        super("Cardapio com id: " + id + " não cadastrado");
    }
}
