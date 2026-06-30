package Modelo;

import java.util.List;

public class SimpleStrategy implements EstrategiaAlocacao {

    @Override
    public Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas, List<Horario> horarios) {
        Grade grade = new Grade();

        // Para cada disciplina, tenta alocar com a primeira turma
        for (Disciplina disciplina : disciplinas) {
            boolean alocada = false;

            // Tenta com a primeira turma disponível
            for (Turma turma : turmas) {
                if (alocada) break;

                // Encontra um professor com competência
                for (Professor professor : professores) {
                    if (!professor.temCompetencia(disciplina)) {
                        continue;
                    }

                    // Tenta cada horário
                    for (Horario horario : horarios) {
                        try {
                            // Verifica se professor tem disponibilidade
                            if (!professor.getDisponibilidade().contains(horario)) {
                                continue;
                            }

                            // Tenta alocar
                            Alocacao alocacao = new Alocacao(turma, disciplina, professor, horario);
                            grade.AdicionarAlocacao(alocacao);
                            alocada = true;
                            break;

                        } catch (IllegalStateException e) {
                            // Conflito detectado, tenta próximo horário
                            continue;
                        }
                    }
                    if (alocada) break;
                }
            }

            if (!alocada) {
                System.out.println("Aviso: Não foi possível alocar a disciplina " +
                        disciplina + " em nenhuma turma.");
            }
        }

        return grade;
    }
}