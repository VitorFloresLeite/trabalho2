package Modelo;
import java.util.List;

public class BalanceStrategy implements EstrategiaAlocacao {

    @Override
    public Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas) {
        Grade grade = new Grade();

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

                if (professorMenosOcupado != null) {
                    for (Horario horario : professorMenosOcupado.getDisponibilidade()) {
                        try {
                            if (!professorMenosOcupado.getDisponibilidade().contains(horario)) {
                                continue;
                            }

                            Alocacao alocacao = new Alocacao(turma, disciplina, professorMenosOcupado, horario);
                            grade.AdicionarAlocacao(alocacao);

                            turma.setHorarios(horario);

                            alocada = true;
                            break;

                        } catch (IllegalStateException e) {
                            continue;
                        }
                    }
                }

                if (!alocada) {
                    System.out.println("Aviso: Não foi possível alocar " + disciplina + " com balanceamento para " + turma.getCurso());
                }
            }
        }

        return grade;
    }
}