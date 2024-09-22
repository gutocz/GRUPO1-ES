package ufcg.ES.RU.exceptions;

public class MarmitaNotExistsException extends RUException{
    public MarmitaNotExistsException(Long id){
        super("Marmita de id: " + id + " não existe.");
    }
}
