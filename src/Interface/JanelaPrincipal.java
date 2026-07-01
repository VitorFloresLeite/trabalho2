package Interface;

import Controle.*;
import java.awt.*;

public class JanelaPrincipal extends Janela {
    private ControlePaineis controle;

    public JanelaPrincipal(String titulo) {
        super(titulo);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10,10));
    }
    public void setControle(ControlePaineis controle) {
        this.controle = controle;
    }
    public void setPainel(Painel painel) {
        getContentPane().removeAll();
        getContentPane().add(painel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
}
