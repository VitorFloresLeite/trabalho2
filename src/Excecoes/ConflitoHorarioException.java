package Excecoes;

public class ConflitoHorarioException extends RuntimeException{
    public ConflitoHorarioException(String mensagem){
        super(mensagem);
    }
}
