package Modelo;
import java.util.List;

public class BalanceStrategy implements EstrategiaAlocacao {

    @Override
    public Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas, List<Horario> horarios) {
        Grade grade = new Grade();

        // Para cada disciplina, aloca tentando balancear carga
        for (Disciplina disciplina : disciplinas) {
            boolean alocada = false;

            for (Turma turma : turmas) {
                if (alocada) break;

                // Encontra professor com competência E menos aulas já alocadas
                Professor professorMenosOcupado = null;
                int menorCarga = Integer.MAX_VALUE;

                for (Professor professor : professores) {
                    if (!professor.temCompetencia(disciplina)) {
                        continue;
                    }

                    // Conta quantas aulas ele já tem nessa grade
                    int cargaAtual = 0;
                    for (Alocacao a : grade.getAlocacoes()) {
                        if (a.getProfessor().getNome().equals(professor.getNome())) {
                            cargaAtual++;
                        }
                    }

                    if (cargaAtual < menorCarga) {
                        menorCarga = cargaAtual;
                        professorMenosOcupado = professor;
                    }
                }

                // Usa o professor com menos carga
                if (professorMenosOcupado != null) {
                    for (Horario horario : horarios) {
                        try {
                            if (!professorMenosOcupado.getDisponibilidade().contains(horario)) {
                                continue;
                            }

                            Alocacao alocacao = new Alocacao(turma, disciplina,
                                    professorMenosOcupado, horario);
                            grade.AdicionarAlocacao(alocacao);
                            alocada = true;
                            break;

                        } catch (IllegalStateException e) {
                            continue;
                        }
                    }
                }
            }

            if (!alocada) {
                System.out.println("Aviso: Não foi possível alocar a disciplina " +
                        disciplina + " com balanceamento de carga.");
            }
        }

        return grade;
    }
}
