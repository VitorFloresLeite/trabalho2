package Excecoes;

public class DadosInsuficientesException extends RuntimeException{
    public DadosInsuficientesException(String mensagem){
        super(mensagem);
    }
}
