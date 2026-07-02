package Modelo;
import Excecoes.ConflitoHorarioException;
import Excecoes.ConflitoProfessorException;
import java.util.List;

public class BalanceStrategy implements EstrategiaAlocacao {

    @Override
    public Grade gerar(List<Disciplina> disciplinas, List<Professor> professores, List<Turma> turmas) {
        Grade grade = new Grade();

        for (Turma turma : turmas) {
            // Utiliza as disciplinas da própria turma
            for (Disciplina disciplina : turma.getDisciplinasEsperadas()) {
                boolean alocada = false;

                Professor professorMenosOcupado = null;
                int menorCarga = Integer.MAX_VALUE;

                // Busca o professor com competência e menos carga atual
                for (Professor professor : professores) {
                    if (!professor.temCompetencia(disciplina)) continue;

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
                    for (Horario horario : professorMenosOcupado.getDisponibilidade()) {
                        try {
                            Alocacao alocacao = new Alocacao(turma, disciplina, professorMenosOcupado, horario);
                            grade.AdicionarAlocacao(alocacao);
                            alocada = true;
                            break;
                        } catch (ConflitoHorarioException | ConflitoProfessorException e) {
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