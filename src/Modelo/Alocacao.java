package Modelo;

import Excecoes.*;


public class Alocacao{
    private final Turma turma;
    private final Disciplina disciplina;
    private final Professor professor;
    private final Horario horario;

    public Alocacao(Turma turma, Disciplina disciplina, Professor professor, Horario horario){
        if(turma == null){
            throw new TurmaNulaException("A turma não pode ser nula.");
        }
        if(disciplina == null){
            throw new DisciplinaNulaException("A disciplina não pode ser nula.");
        }
        if(professor == null){
            throw new ProfessorNuloException("O professor não pode ser nulo.");
        }
        if(horario == null){
            throw new HorarioNuloException("O horario não pode ser nulo.");
        }

        //validação da regra de negocio: saber se o professor tem competencia para essa disciplina
        if(!professor.temCompetencia(disciplina)){
            throw new ProfessorIncompativelException("O professor "+ professor.getNome()+" não tem competencia cadastrada para a disciplina " + disciplina.toString());
        }

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