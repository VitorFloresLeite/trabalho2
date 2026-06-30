import Modelo.*;
import java.util.ArrayList;
import java.util.List;

public class MainTeste {
    public static void main(String[] args) {
        // ========== CRIAR DADOS DE TESTE ==========

        // Criar professores com competências
        Professor prof1 = new Professor("Dr. Silva");
        prof1.adicionarCompetencia(Disciplina.ALGORITMOS);
        prof1.adicionarCompetencia(Disciplina.PROGRAMACAO_ORIENTADA_A_OBJETOS);
        prof1.adicionarDisponibilidade(new Horario(Dias.SEGUNDA, Turnos.MANHA, Periodos.PRIMEIRO));
        prof1.adicionarDisponibilidade(new Horario(Dias.QUARTA, Turnos.TARDE, Periodos.PRIMEIRO));
        prof1.adicionarDisponibilidade(new Horario(Dias.SEXTA, Turnos.MANHA, Periodos.SEGUNDO));

        Professor prof2 = new Professor("Dra. Costa");
        prof2.adicionarCompetencia(Disciplina.CALCULO);
        prof2.adicionarCompetencia(Disciplina.PROGRAMACAO_ORIENTADA_A_OBJETOS);
        prof2.adicionarDisponibilidade(new Horario(Dias.TERCA, Turnos.MANHA, Periodos.PRIMEIRO));
        prof2.adicionarDisponibilidade(new Horario(Dias.QUINTA, Turnos.TARDE, Periodos.SEGUNDO));
        prof2.adicionarDisponibilidade(new Horario(Dias.SEXTA, Turnos.NOITE, Periodos.PRIMEIRO));

        Professor prof3 = new Professor("Prof. Santos");
        prof3.adicionarCompetencia(Disciplina.BANCO_DE_DADOS);
        prof3.adicionarCompetencia(Disciplina.CALCULO);
        prof3.adicionarDisponibilidade(new Horario(Dias.SEGUNDA, Turnos.TARDE, Periodos.PRIMEIRO));
        prof3.adicionarDisponibilidade(new Horario(Dias.QUARTA, Turnos.MANHA, Periodos.SEGUNDO));
        prof3.adicionarDisponibilidade(new Horario(Dias.QUINTA, Turnos.NOITE, Periodos.PRIMEIRO));

        // Criar turmas
        Turma turma1 = new Turma(Cursos.SISTEMAS_DE_INFORMACAO, 2);
        Turma turma2 = new Turma(Cursos.ENGENHARIA_DE_SOFTWARE, 1);

        // Criar lista de horários disponíveis
        List<Horario> horarios = new ArrayList<>();
        horarios.add(new Horario(Dias.SEGUNDA, Turnos.MANHA, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.SEGUNDA, Turnos.TARDE, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.TERCA, Turnos.MANHA, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.QUARTA, Turnos.TARDE, Periodos.PRIMEIRO));
        horarios.add(new Horario(Dias.QUINTA, Turnos.TARDE, Periodos.SEGUNDO));
        horarios.add(new Horario(Dias.SEXTA, Turnos.MANHA, Periodos.SEGUNDO));

        // ========== TESTAR SIMPLE STRATEGY ==========
        System.out.println("\n========== TESTE COM SIMPLE STRATEGY ==========");
        List<Professor> professores = new ArrayList<>();
        professores.add(prof1);
        professores.add(prof2);
        professores.add(prof3);

        List<Disciplina> disciplinas = new ArrayList<>();
        disciplinas.add(Disciplina.ALGORITMOS);
        disciplinas.add(Disciplina.CALCULO);
        disciplinas.add(Disciplina.PROGRAMACAO_ORIENTADA_A_OBJETOS);
        disciplinas.add(Disciplina.BANCO_DE_DADOS);

        List<Turma> turmas = new ArrayList<>();
        turmas.add(turma1);
        turmas.add(turma2);

        GeradorGrade gerador = new GeradorGrade(new SimpleStrategy());
        Grade gradeSimple = gerador.gerar(disciplinas, professores, turmas, horarios);
        gradeSimple.exibirGradeTeste();

        // ========== TESTAR BALANCE STRATEGY ==========
        System.out.println("\n========== TESTE COM BALANCE STRATEGY ==========");
        GeradorGrade gerador2 = new GeradorGrade(new BalanceStrategy());
        Grade gradeBalance = gerador2.gerar(disciplinas, professores, turmas, horarios);
        gradeBalance.exibirGradeTeste();

        // ========== TESTE DE CONFLITO ==========
        System.out.println("\n========== TESTE DE TRATAMENTO DE CONFLITO ==========");
        try {
            // Tenta criar uma alocação com professor que não tem competência
            Alocacao alocacaoInvalida = new Alocacao(turma1, Disciplina.BANCO_DE_DADOS, prof1,
                    new Horario(Dias.SEGUNDA, Turnos.MANHA, Periodos.PRIMEIRO));
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
        }

        System.out.println("\nTestes concluídos com sucesso!");
    }
}