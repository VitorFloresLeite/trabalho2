package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grade {

    private final List<Alocacao> alocacoes;

    public Grade (){
        this.alocacoes = new ArrayList<>();
    }

    public void AdicionarAlocacao(Alocacao novaAlocacao){
        if (novaAlocacao == null){
            throw new IllegalArgumentException("A alocação não pode ser nula.");
        }

        verificarConflitos(novaAlocacao);
        this.alocacoes.add(novaAlocacao);
    }

    private void verificarConflitos(Alocacao novaAlocacao) {
        for (Alocacao existente : alocacoes) {
            boolean mesmoHorario =
                    existente.getHorario().getDia() == novaAlocacao.getHorario().getDia() &&
                            existente.getHorario().getTurno() == novaAlocacao.getHorario().getTurno() &&
                            existente.getHorario().getPeriodo() == novaAlocacao.getHorario().getPeriodo();

            if (mesmoHorario) {
                if (existente.getProfessor().getNome().equals(novaAlocacao.getProfessor().getNome())) {
                    throw new IllegalStateException("Conflito de Horário: O professor " +
                            novaAlocacao.getProfessor().getNome() + " já possui aula neste horário.");
                }

                if (existente.getTurma().getCurso() == novaAlocacao.getTurma().getCurso() &&
                        existente.getTurma().getSemestre() == novaAlocacao.getTurma().getSemestre()) {
                    throw new IllegalStateException("Conflito de Horário: A turma de " +
                            novaAlocacao.getTurma().getCurso() + " (Semestre " +
                            novaAlocacao.getTurma().getSemestre() + ") já possui aula neste horário.");
                }
            }
        }
    }
    public List<Alocacao> getAlocacoes() {
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
