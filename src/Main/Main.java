package Main;
import  Modelo.*;

public class Main {
    public static void main (String[] args) {
        Professor prof1 = new Professor("Ceslo");
        prof1.adicionarCompetencia(Disciplina.CALCULO);

        Turma turma1 = new Turma(Cursos.CIENCIA_DA_COMPUTACAO, 2);
        Horario horarioSegunda = new Horario(Dias.SEGUNDA, Turnos.NOITE, Periodos.SEGUNDO);

        Grade grade = new Grade();

        System.out.println("Tentando alocar aula 1...");
        try {
            Alocacao aula1 = new Alocacao(turma1, Disciplina.CALCULO, prof1, horarioSegunda);
            grade.AdicionarAlocacao(aula1);
            System.out.println("Primeira aula alocada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar alocação: " + e.getMessage());
        }
        //2. Testar bloqueio de competencia: tentar alocar o Celso para POO (ele nao tem competencia para isso)
        System.out.println("\nTentando alocar Celso para disciplina sem competência (POO)...");
        try {
            Turma turma3 = new Turma(Cursos.SISTEMAS_DE_INFORMACAO, 3);
            Horario horarioTerca = new Horario(Dias.TERCA, Turnos.NOITE, Periodos.PRIMEIRO);

            //Alocacao de aula invalida (exceção de competencia)
            Alocacao aulaInvalida = new Alocacao(turma3, Disciplina.PROGRAMACAO_ORIENTADA_A_OBJETOS, prof1, horarioTerca);
            grade.AdicionarAlocacao(aulaInvalida);
        } catch (IllegalArgumentException e) {
            System.out.println("SUCESSO: O sistema barrou corretamente! Motivo: " + e.getMessage());
        }

        //Exibir o resultado final do teste
        grade.exibirGradeTeste();
    }
}
