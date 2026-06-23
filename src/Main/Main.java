package main;
import  modelo.*;

public class Main {
    public static void main (String[] args){
        Professor prof1 = new Professor("Ceslo");
        Turma turma1 = new Turma(Cursos.CIENCIA_DA_COMPUTACAO, 2);
        Horario horarioSegunda = new Horario(Dias.SEGUNDA, Turnos.NOITE, Periodos.SEGUNDO);

        Grade grade = new Grade();

        Alocacao aula1 = new Alocacao(turma1, Disciplina.CALCULO, prof1, horarioSegunda);
        System.out.println("Alocando Aula...");
        grade.AdicionarAlocacao(aula1);
        System.out.println("Primeira aula alocada com sucesso!");

        Turma turma2 = new Turma(Cursos.ENGENHARIA_DE_SOFTWARE, 1);
        Alocacao aulaConflito = new Alocacao(turma2,  Disciplina.CALCULO, prof1, horarioSegunda);

        System.out.println("\nAlocando Aula...");
        try{
            grade.AdicionarAlocacao(aulaConflito);
        } catch (IllegalStateException e){
            System.out.println("ERRO DE DOMINIO BARRADO S   " + e.getMessage());
        }

        grade.exibirGradeTeste();

    }
}
