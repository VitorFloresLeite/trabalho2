package Dados;
import Modelo.Cursos;
import Modelo.Dias;
import Modelo.Disciplina;
import Modelo.Horario;
import Modelo.Periodos;
import Modelo.Professor;
import Modelo.Turma;
import Modelo.Turnos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ListasDeDados {
    private static List<Horario> horarios = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();
    public ListasDeDados() {
        
        horarios.add(new Horario(Dias.SEGUNDA, Turnos.MANHA, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.SEGUNDA, Turnos.TARDE, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.TERCA, Turnos.MANHA, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.QUARTA, Turnos.TARDE, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.QUINTA, Turnos.TARDE, Periodos.SEGUNDO));
        horarios.add(new Horario(Dias.SEXTA, Turnos.MANHA, Periodos.SEGUNDO));

        disciplinas.add(Disciplina.ALGORITMOS);
        disciplinas.add(Disciplina.CALCULO);
        disciplinas.add(Disciplina.PROGRAMACAO_ORIENTADA_A_OBJETOS);
        disciplinas.add(Disciplina.BANCO_DE_DADOS);
        
        Turma turma1 = new Turma(Cursos.SISTEMAS_DE_INFORMACAO, 2);
        Turma turma2 = new Turma(Cursos.ENGENHARIA_DE_SOFTWARE, 1);
        turmas.add(turma1);
        turmas.add(turma2);
    }

    public static void AdicionarProfessor(Professor prof){
        professores.add(prof);
    }

    public List<Horario> getHorarios() {
        return Collections.unmodifiableList(horarios);
    }
}
