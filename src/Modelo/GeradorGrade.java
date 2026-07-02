package Modelo;

import java.util.List;

public class GeradorGrade {
    private EstrategiaAlocacao estrategia;

    public GeradorGrade(EstrategiaAlocacao estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("A estratégia de alocação não pode ser nula.");
        }
        this.estrategia = estrategia;
    }
    public GeradorGrade(){
        this.estrategia=null;
    }

    public Grade gerar(List<Disciplina> disciplinas,
                       List<Professor> professores,
                       List<Turma> turmas,
                       List<Horario> horarios) {
        if (disciplinas == null || disciplinas.isEmpty()) {
            throw new IllegalArgumentException("A lista de disciplinas não pode estar vazia.");
        }
        if (professores == null || professores.isEmpty()) {
            throw new IllegalArgumentException("A lista de professores não pode estar vazia.");
        }
        if (turmas == null || turmas.isEmpty()) {
            throw new IllegalArgumentException("A lista de turmas não pode estar vazia.");
        }
        if (horarios == null || horarios.isEmpty()) {
            throw new IllegalArgumentException("A lista de horários não pode estar vazia.");
        }

        return estrategia.gerar(disciplinas, professores, turmas, horarios);
    }

    public void setEstrategia(EstrategiaAlocacao novaEstrategia) {
        if (novaEstrategia == null) {
            throw new IllegalArgumentException("A estratégia de alocação não pode ser nula.");
        }
        this.estrategia = novaEstrategia;
    }
}
