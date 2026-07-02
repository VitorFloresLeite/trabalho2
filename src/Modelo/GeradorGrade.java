package Modelo;

import Excecoes.*;
import java.util.List;

public class GeradorGrade {
    private EstrategiaAlocacao estrategia;

    public GeradorGrade(EstrategiaAlocacao estrategia) {
        if (estrategia == null) {
            throw new AlocacaoException("A estratégia de alocação não pode ser nula.");
        }
        this.estrategia = estrategia;
    }
    public GeradorGrade(){
        this.estrategia=null;
    }

    public Grade gerar(List<Disciplina> disciplinas,
                       List<Professor> professores,
                       List<Turma> turmas) {
        if (disciplinas == null || disciplinas.isEmpty()) {
            throw new DadosInsuficientesException("A lista de disciplinas não pode estar vazia.");
        }
        if (professores == null || professores.isEmpty()) {
            throw new DadosInsuficientesException("A lista de professores não pode estar vazia.");
        }
        if (turmas == null || turmas.isEmpty()) {
            throw new DadosInsuficientesException("A lista de turmas não pode estar vazia.");
        }
        if (estrategia == null) {
            throw new DadosInsuficientesException("A estratégia de alocação não foi definida.");
        }
        return estrategia.gerar(disciplinas, professores, turmas);
    }

    public void setEstrategia(EstrategiaAlocacao novaEstrategia) {
        if (novaEstrategia == null) {
            throw new AlocacaoException("A estratégia de alocação não pode ser nula.");
        }
        this.estrategia = novaEstrategia;
    }
}
