package ufcg.ES.RU.exceptions;

public class ItemNotExistException extends RUException{

    public ItemNotExistException(Long id){
        super("Item de id: " + id + " não cadastrado");
    }
}
