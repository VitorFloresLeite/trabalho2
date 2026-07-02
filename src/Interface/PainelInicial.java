package Interface;
import Controle.*;
import javax.swing.*;
public class PainelInicial extends Painel {
    public PainelInicial(String titulo, ControlePaineis controle) {
        super(titulo,controle);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Botao button = new Botao("Exibir Grade", 10);
        button.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.GRADE));
        button.setAlignmentX(CENTER_ALIGNMENT);
        
        Botao button2 = new Botao("Cadastrar Professor", 10);
        button2.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.CADASTRO_PROFESSOR));
        button2.setAlignmentX(CENTER_ALIGNMENT);
        
        Botao button3 = new Botao("Cadastrar Turma", 10);
        button3.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.CADASTRO_TURMA));
        button3.setAlignmentX(CENTER_ALIGNMENT);
        
        Botao button4 = new Botao("Exibir Turmas", 10);
        button4.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.EXIBIR_TURMAS));
        button4.setAlignmentX(CENTER_ALIGNMENT);
        
        Botao button5 = new Botao("Exibir Professores", 10);
        button5.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.EXIBIR_PROFESSORES));
        button5.setAlignmentX(CENTER_ALIGNMENT);

        Botao button6 = new Botao("Gerar Grade", 10);
        button6.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.GERAR_GRADE));
        button6.setAlignmentX(CENTER_ALIGNMENT);

        
        add(button);
        add(button6);
        
        add(button2);
        add(button5);
        
        add(button3);
        add(button4);
    }
}