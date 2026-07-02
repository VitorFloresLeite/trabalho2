package Modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Professor {

    private String nome;
    private List<Disciplina> competencias;
    private List<Horario> disponibilidade;

    public Professor(String nome){
        // if(nome == null || nome.trim().isEmpty()){
        //     throw new ProfessorNuloException("O nome do professor não pode ser vazio.");
        // }
        this.nome = nome;
        this.competencias = new ArrayList<>();
        this.disponibilidade = new ArrayList<>();
    }

    public void adicionarCompetencia(Disciplina disciplina){
        if (disciplina != null && !competencias.contains(disciplina)){
            this.competencias.add(disciplina);
        }
    }

    public void adicionarDisponibilidade(Horario horario){
        if(horario != null && !disponibilidade.contains(horario)){
            this.disponibilidade.add(horario);
        }
    }

    public String getNome(){
        return this.nome;
    }

    public List<Disciplina> getCompetencias(){
        return Collections.unmodifiableList(competencias);
    }

    public List<Horario> getDisponibilidade(){
        return Collections.unmodifiableList(disponibilidade);
    }

    public boolean temCompetencia(Disciplina disciplina){
        return this.competencias.contains(disciplina);
    }

    @Override
    public String toString(){
        return "Professor: " + nome + ", Competências: " + getCompetencias().toString();//, Disponibilidade: " + disponibilidade.toString()*/";
    }
}

