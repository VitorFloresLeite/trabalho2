package Controle;
import Interface.*;
import Modelo.*;

public class ControlePaineis {
    private final JanelaPrincipal janelaPrincipal;
    private static Painel painelAtual;

    private final Painel painelInicial = new PainelInicial("inicial", this);
    private final PainelGrade grade = new PainelGrade("Grade", this);
    private final Painel cadastroProfessor = new PainelCadastroProfessor("Cadastro de Professor", this);
    private final Painel cadastroTurma = new PainelCadastroTurma("Cadastro de Turma", this);
    private final PainelExibirTurmas exibirTurmas = new PainelExibirTurmas("Exibir Turmas", this);
    private final PainelExibirProfessores exibirProfessores = new PainelExibirProfessores("Exibir Professores", this);

    private final ControleDados controleDados;
    private Grade gradeGerada;

    public ControlePaineis(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        this.controleDados = new ControleDados();
        painelAtual = new PainelInicial("inicial", this);
        this.janelaPrincipal.setPainel(painelAtual);
    }

    public void trocarPainel(PaineisDoPrograma painel){
        switch(painel){
            case PaineisDoPrograma.INICIAL: 
                painelAtual = painelInicial;
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.GRADE: 
                painelAtual = grade;
                grade.atualizarGrade(gradeGerada);
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.GERAR_GRADE:
                painelAtual = new PainelGerarGrade("Gerar Grade", this, controleDados);
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.CADASTRO_PROFESSOR:
                painelAtual = cadastroProfessor;
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.CADASTRO_TURMA:
                painelAtual = cadastroTurma;
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.EXIBIR_TURMAS:
                painelAtual = exibirTurmas;
                exibirTurmas.atualizarTabela(controleDados.getTurmas());
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.EXIBIR_PROFESSORES:
                painelAtual = exibirProfessores;
                exibirProfessores.atualizarTabela(controleDados.getProfessores());
                janelaPrincipal.setPainel(painelAtual);
                break;
        }
    }
    
    public Painel getPainelAtual() {
        return painelAtual;
    }

    public void setGradeGerada(Grade gradeGerada) {
        this.gradeGerada = gradeGerada;
    }

    public Grade getGradeGerada() {
        return gradeGerada;
    }

    public void cadastrarProfessor(Professor professor){
        controleDados.cadastrarProfessor(professor);
    }
    public Professor buscarProfessor(String nome){
        return controleDados.buscarProfessor(nome);
    }
    public void cadastrarTurma(Turma turma){
        controleDados.cadastrarTurma(turma);
    }
    public Turma buscarTurma(Cursos curso, int semestre){
        return controleDados.buscarTurma(curso, semestre);
    }
}
