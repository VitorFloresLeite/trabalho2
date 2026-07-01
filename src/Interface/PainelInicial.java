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
        add(button);
       
    }
}