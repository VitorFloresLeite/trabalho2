package Modelo;
import java.util.List;

public class ValidadorConflitoHorario implements CriterioAlocacao {

    @Override
    public boolean validar(Alocacao nova, List<Alocacao> existentes) {

        boolean professorOcupado = existentes.stream().anyMatch(e ->
                mesmoHorario(e.getHorario(), nova.getHorario()) &&
                        e.getProfessor().getNome().equals(nova.getProfessor().getNome())
        );

        if (professorOcupado) {
            throw new IllegalStateException("Conflito de Horário: O professor " +
                    nova.getProfessor().getNome() + " já possui aula neste horário.");
        }

        boolean turmaOcupada = existentes.stream().anyMatch(e ->
                mesmoHorario(e.getHorario(), nova.getHorario()) &&
                        e.getTurma().getCurso() == nova.getTurma().getCurso() &&
                        e.getTurma().getSemestre() == nova.getTurma().getSemestre()
        );

        if (turmaOcupada) {
            throw new IllegalStateException("Conflito de Horário: A turma " +
                    nova.getTurma().getCurso() + " já possui aula neste horário.");
        }

        return true;
    }

    private boolean mesmoHorario(Horario h1, Horario h2) {
        return h1.getDia() == h2.getDia() &&
                h1.getTurno() == h2.getTurno() &&
                h1.getPeriodo() == h2.getPeriodo();
    }
}