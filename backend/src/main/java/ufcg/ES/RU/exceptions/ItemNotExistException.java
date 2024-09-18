package ufcg.ES.RU.exceptions;

public class ItemNotExistException extends RUException{

    public ItemNotExistException(){
        super("Item não cadastrado");
    }
}
