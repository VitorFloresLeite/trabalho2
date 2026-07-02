package Modelo;

import java.util.List;

public class ValidadorConflitoPadrao implements ValidaAlocacaoStrategy {
    
    @Override
    public void validar(Alocacao novaAlocacao, List<Alocacao> alocacoesExistentes) throws IllegalStateException {
        
        // Filtra de uma só vez APENAS as alocações que estão no exato mesmo horário
        alocacoesExistentes.stream()
            .filter(existente -> 
                existente.getHorario().getDia() == novaAlocacao.getHorario().getDia() &&
                existente.getHorario().getTurno() == novaAlocacao.getHorario().getTurno() &&
                existente.getHorario().getPeriodo() == novaAlocacao.getHorario().getPeriodo()
            )
            .forEach(conflito -> {
                // Se entrou aqui, já sabemos que é no mesmo horário. Basta verificar QUEM está em conflito.
                
                // 1. Verifica conflito de Professor
                if (conflito.getProfessor().getNome().equals(novaAlocacao.getProfessor().getNome())) {
                    throw new IllegalStateException("Conflito de Horário: o professor " + 
                            novaAlocacao.getProfessor().getNome() + " já possui aula neste horário.");
                }
                
                // 2. Verifica conflito de Turma
                if (conflito.getTurma().getCurso() == novaAlocacao.getTurma().getCurso() && 
                    conflito.getTurma().getSemestre() == novaAlocacao.getTurma().getSemestre()) {
                    throw new IllegalStateException("Conflito de Horário: A turma de " + 
                            novaAlocacao.getTurma().getCurso() + " (Semestre " + 
                            novaAlocacao.getTurma().getSemestre() + ") já possui aula neste horário.");
                }
            });
    }
}