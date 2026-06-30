package Interface;

import java.awt.*;
import javax.swing.JFrame;

public abstract class Janela extends JFrame {
    protected int width = 1920/2;
    protected int height = 1080/2;
    protected int offset = 10;
    protected boolean visible = true;
    protected boolean resizable = true;

    public Janela(String titulo) {
        super(titulo);
        configurarJanela();
        inicializarComponentes();
        setVisible(visible);
    }

    private void configurarJanela() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(offset, offset));
        setResizable(resizable);
        getContentPane().setBackground(new Color(176, 11, 105)); 
    }

    protected abstract void inicializarComponentes();

    protected void setWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
        setSize(width, height);
    }

    protected void setWindowOffset(int offset) {
        this.offset = offset;
        setLayout(new BorderLayout(offset, offset));
    }

    protected void setWindowVisibility(boolean visible) {
        this.visible = visible;
        setVisible(visible);
    }

    protected void setWindowResizable(boolean resizable) {
        this.resizable = resizable;
        setResizable(resizable);
    }
}