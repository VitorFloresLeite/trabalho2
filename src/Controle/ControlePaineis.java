package Controle;
import Interface.*;
public class ControlePaineis {
    private JanelaPrincipal janelaPrincipal;
    private static Painel painelAtual;
    private Painel painelInicial=new PainelInicial("inicial", this);
    private Painel grade=new PainelGrade("Grade", this);
    private Painel cadastroProfessor=new PainelCadastroProfessor("Cadastro de Professor", this);
    public ControlePaineis(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipal = janelaPrincipal;
        painelAtual=new PainelInicial("inicial", this);
        this.janelaPrincipal.setPainel(painelAtual);
    }
    public void trocarPainel(PaineisDoPrograma painel){
        switch(painel){
            case PaineisDoPrograma.INICIAL: 
                painelAtual=painelInicial;
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.GRADE: 
                painelAtual=grade;
                janelaPrincipal.setPainel(painelAtual);
                break;
            case PaineisDoPrograma.CADASTRO_PROFESSOR:
                painelAtual=cadastroProfessor;
                janelaPrincipal.setPainel(painelAtual);
                break;
        }
    }
    public Painel getPainelAtual() {
        return painelAtual;
    }
}
