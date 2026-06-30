package Modelo;

import java.util.List;

public interface EstrategiaAlocacao {
    Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas, List<Horario> horarios);
}