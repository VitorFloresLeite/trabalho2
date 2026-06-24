package Interface;

import java.awt.BorderLayout;
import javax.swing.*;

public class JanelaInicial extends Janela {
    
    public JanelaInicial(String titulo) {
        super(titulo);
    }

    @Override
    protected void inicializarComponentes() {
        Botao button = new Botao("Clique aqui", 10);
        button.addActionListener(e -> button.setText("Botão clicado!"));
        // Cria um painel para segurar o botão sem esticá-lo
        JPanel painel = new JPanel();
        painel.setOpaque(false); // Deixa o painel transparente para vermos a cor de fundo
        painel.add(button);      // Adiciona o botão ao painel
        
        // Adiciona o painel à janela
        getContentPane().add(painel, BorderLayout.CENTER);
    }
}