package Interface;

import Controle.*;
import java.awt.*;
import javax.swing.*;
public abstract class Painel extends JPanel {
    protected final ControlePaineis controle;
    
    public Painel(String titulo, ControlePaineis controle) {
        this.controle = controle;
        setLayout(new BorderLayout(10, 10));
        setOpaque(false);
        JLabel title = new JLabel(titulo, SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setAlignmentY(TOP_ALIGNMENT);
        add(title, BorderLayout.NORTH);
        
        inicializarComponentes();
    }
    protected abstract void inicializarComponentes();
}
