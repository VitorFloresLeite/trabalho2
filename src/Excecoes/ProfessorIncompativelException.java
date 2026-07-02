package Excecoes;

public class ProfessorIncompativelException extends RuntimeException {
    public ProfessorIncompativelException(String mensagem){
        super(mensagem);
    }
}
