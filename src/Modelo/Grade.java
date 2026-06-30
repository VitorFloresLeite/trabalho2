package Modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grade {

    private final List<Alocacao> alocacoes;
    private final ValidaAlocacaoStrategy validador;

    public Grade () {
        this.alocacoes = new ArrayList<>();
        this.validador = new ValidadorConflitoPadrao();
    }

    public void AdicionarAlocacao(Alocacao novaAlocacao) {
        if (novaAlocacao == null){
            throw new IllegalStateException("A alocação não pode ser nula.");
        }
        this.validador.validar(novaAlocacao, this.alocacoes);
        this.alocacoes.add(novaAlocacao);
    }

    public List<Alocacao> getAlocacoes(){
        return Collections.unmodifiableList(alocacoes);
    }

    public void exibirGradeTeste(){
        System.out.println("\n========== GRADE DE HORÁRIOS ==========");
        if(alocacoes.isEmpty()){
            System.out.println("A Grade está vazia");
        } else {
            for (Alocacao alocacao : alocacoes){
                System.out.println(alocacao.toString());
                System.out.println("---------------------------------------");
            }
        }
        System.out.println("=======================================\n");
    }

}
