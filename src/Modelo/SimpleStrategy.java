package Modelo;
import java.util.List;

public class SimpleStrategy implements EstrategiaAlocacao {

    @Override
    public Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas) {
        Grade grade = new Grade();

        // INVERSÃO CORRETA: Para CADA turma, tenta alocar TODAS as disciplinas
        for (Turma turma : turmas) {
            for (Disciplina disciplina : disciplinas) {
                boolean alocada = false;

                // Encontra um professor com competência
                for (Professor professor : professores) {
                    if (!professor.temCompetencia(disciplina)) {
                        continue;
                    }
                    for (Horario horario : professor.getDisponibilidade()) {
                        try {
                            Alocacao alocacao = new Alocacao(turma, disciplina, professor, horario);
                            grade.AdicionarAlocacao(alocacao);
                            turma.setHorarios(horario); 
                            alocada = true;
                            break;
                        } catch (IllegalStateException e) {
                            continue;
                        }
                    }
                    if (alocada) break;
                }
                
                if (!alocada) {
                    System.out.println("Aviso: Não foi possível alocar " + disciplina + " para a turma de " + turma.getCurso());
                }
            }
        }

        return grade;
    }
}