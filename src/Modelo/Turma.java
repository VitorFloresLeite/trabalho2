package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Turma{
    private Cursos curso;
    private int semestre;
    //private List<Horario> horarios;
    private List<Disciplina> disciplinasDoSemestre;


    public Turma(Cursos curso, int semestre){
        this.curso = curso;
        this.semestre = semestre;
        this.disciplinasDoSemestre = new ArrayList<>();
    }

    public Cursos getCurso() {
        return curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public void adicionarDisciplina(Disciplina d){
        if(d != null && !disciplinasDoSemestre.contains(d)){
            disciplinasDoSemestre.add(d);
        }
    }

    public List<Disciplina> getDisciplinasDoSemestre(){
        return new ArrayList<>(disciplinasDoSemestre); //retorna a copia para proteção de estado
    }
}