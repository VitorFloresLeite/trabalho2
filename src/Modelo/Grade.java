package Modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grade {

    private final List<Alocacao> alocacoes;
    private final List<CriterioAlocacao> criterios;

    public Grade (){
        this.alocacoes = new ArrayList<>();
        this.criterios = new ArrayList<>();

        this.criterios.add(new ValidadorConflitoHorario());
    }

    public void AdicionarAlocacao(Alocacao novaAlocacao) {
        if (novaAlocacao == null){
            throw new IllegalStateException("A alocação não pode ser nula.");
        }
        for (CriterioAlocacao criterio : criterios){
            criterio.validar(novaAlocacao, alocacoes);
        }
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
