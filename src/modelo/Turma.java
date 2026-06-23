package modelo;
import java.util.ArrayList;
import java.util.List;

public class Turma{
    private Cursos curso;
    private int semestre;
    private List<Horario> horarios;

    public Turma(Cursos curso, int semestre){
        this.curso = curso;
        try{
            if(semestre < 1 || semestre > 8){
                throw new IllegalArgumentException("Semestre deve ser entre 1 e 8.");
            }
            this.semestre = semestre;
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        this.horarios = new ArrayList<>();
    }
    public Cursos getCurso() {
        return curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(Horario horarios) {
        this.horarios.add(horarios);
    }
}