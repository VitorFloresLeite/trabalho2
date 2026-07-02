package Modelo;
import java.util.List;

public class BalanceStrategy implements EstrategiaAlocacao {

    @Override
    public Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas) {
        Grade grade = new Grade();

        // INVERSÃO CORRETA: Para cada turma, aloca tentando balancear a carga dos professores
        for (Turma turma : turmas) {
            for (Disciplina disciplina : disciplinas) {
                boolean alocada = false;

                Professor professorMenosOcupado = null;
                int menorCarga = Integer.MAX_VALUE;

                // Busca o professor com competência e menos carga atual
                for (Professor professor : professores) {
                    if (!professor.temCompetencia(disciplina)) {
                        continue;
                    }

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

                // Usa o professor com menos carga e tenta encontrar um horário livre
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
