package ufcg.ES.RU.exceptions;

public class RUException extends RuntimeException{

    public RUException(){
        super("Erro inesperado!");
    }

    public RUException(String message){
        super(message);
    }
}
