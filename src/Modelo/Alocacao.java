package Modelo;

//Representa o match entre um professor, uma disciplina e um horario;

public class Alocacao{
    private final Turma turma;
    private final Disciplina disciplina;
    private final Professor professor;
    private final Horario horario;

    public Alocacao(Turma turma, Disciplina disciplina, Professor professor, Horario horario){
        // validação por contratos: elementos nao podem ser nulos
        if(turma == null){
            throw new IllegalArgumentException("A turma não pode ser nula");
        }
        if(disciplina == null){
            throw new IllegalArgumentException("A disciplina não pode ser nula");
        }
        if(professor == null){
            throw new IllegalArgumentException("O professor não pode ser nulo");
        }
        if(horario == null){
            throw new IllegalArgumentException("O horario não pode ser nulo");
        }

        //validação da regra de negocio: saber se o professor tem competencia para essa disciplina
        /*if(!professor.temCompetencia(disciplina)){
            throw new IllegalArgumentException("O professor "+ professor.getNome()+" não tem competencia cadastrada para a disciplina " + disciplina.getNome());
        }*/

        this.turma = turma;
        this.disciplina = disciplina;
        this.horario = horario;
        this.professor = professor;
    }

    public Turma getTurma(){
        return turma;
    }

    public Disciplina getDisciplina(){
        return disciplina;
    }

    public Professor getProfessor(){
        return professor;
    }

    public Horario getHorario(){
        return horario;
    }
    @Override
    public String toString(){
        return "Alocação: " +
                "\nTurma: " + turma.getCurso() + " - Semestre: " + turma.getSemestre() +
                "\nDisciplina: " + disciplina +
                "\nProfessor: " + professor.getNome() +
                "\nHorario: " + horario.toString();
    }
}