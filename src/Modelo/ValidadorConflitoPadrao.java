package Modelo;

import java.util.List;

public class ValidadorConflitoPadrao implements ValidaAlocacaoStrategy {
    
    @Override
    public void validar(Alocacao novaAlocacao, List<Alocacao> alocacoesExistentes) throws IllegalStateException{
        //predicado para verificar se o horario coincide (Dia, Turno, Periodo)
        boolean mesmoHorario = alocacoesExistentes.stream().anyMatch (existente->
                existente.getHorario().getDia() == novaAlocacao.getHorario().getDia() &&
                existente.getHorario().getTurno() == novaAlocacao.getHorario().getTurno() &&
                existente.getHorario().getPeriodo() == novaAlocacao.getHorario().getPeriodo()
        );

        if(mesmoHorario){
            //uso de Streams para verificar conflito de professor no mesmo horario
            alocacoesExistentes.stream()
                    .filter(existente -> existente.getHorario().getDia() == novaAlocacao.getHorario().getDia() &&
                            existente.getHorario().getTurno() == novaAlocacao.getHorario().getTurno() &&
                            existente.getHorario().getPeriodo() == novaAlocacao.getHorario().getPeriodo())
                    .filter(existente -> existente.getProfessor().getNome().equals(novaAlocacao.getProfessor().getNome()))
                    .findFirst()
                    .ifPresent(conflito->{
                        throw new IllegalStateException("Conflito de Horario: o professor " +
                                novaAlocacao.getProfessor().getNome() + "já possui aula neste horário.");
                    });

            //Uso de Streams para verificar conflito de Turma/Curso/Semestre no mesmo horario
            alocacoesExistentes.stream()
                    .filter(existente-> existente.getHorario().getDia() == novaAlocacao.getHorario().getDia() &&
                            existente.getHorario().getTurno() == novaAlocacao.getHorario().getTurno() &&
                            existente.getHorario().getPeriodo() == novaAlocacao.getHorario().getPeriodo())
                    .filter(existente-> existente.getTurma().getCurso() == novaAlocacao.getTurma().getCurso() &&
                            existente.getTurma().getSemestre() == novaAlocacao.getTurma().getSemestre())
                    .findFirst()
                    .ifPresent(conflito-> {
                        throw new IllegalStateException("Conflito de horario: A turma de " +
                                novaAlocacao.getTurma().getCurso() + "(Semestre "+
                                novaAlocacao.getTurma().getSemestre() + ") já possui aula neste horario.");
                    });
        }
    }
}