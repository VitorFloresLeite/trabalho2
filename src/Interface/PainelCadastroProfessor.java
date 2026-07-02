package Interface;

import Controle.ControlePaineis;
import java.awt.*;
import javax.swing.*;

public class PainelCadastroProfessor extends Painel {

    public PainelCadastroProfessor(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }

    @Override
    protected void inicializarComponentes() {
        // GridBagLayout centraliza o formulário vertical e horizontalmente na tela
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Container que limita o tamanho máximo do formulário
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setOpaque(false);
        painelFormulario.setPreferredSize(new Dimension(450, 400));
        painelFormulario.setMaximumSize(new Dimension(450, 400));

        // Título Interno
        JLabel lblTitulo = new JLabel("Cadastrar Novo Professor");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFormulario.add(lblTitulo);
        painelFormulario.add(Box.createVerticalStrut(20));

        // Campo Nome
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtNome = new JTextField();
        txtNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        // REFINO: Impede que o campo estique verticalmente mantendo uma altura elegante
        txtNome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        painelFormulario.add(lblNome);
        painelFormulario.add(Box.createVerticalStrut(5));
        painelFormulario.add(txtNome);
        painelFormulario.add(Box.createVerticalStrut(15));

        // Botões de Ação
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelBotoes.setOpaque(false);
        painelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);

        Botao btnSalvar = new Botao("Salvar Professor", 10);
        btnSalvar.setPreferredSize(new Dimension(150, 35));
        Botao btnVoltar = new Botao("Voltar", 10);
        btnVoltar.setPreferredSize(new Dimension(100, 35));

        painelBotoes.add(btnSalvar);
        painelBotoes.add(Box.createHorizontalStrut(10));
        painelBotoes.add(btnVoltar);

        painelFormulario.add(Box.createVerticalStrut(10));
        painelFormulario.add(painelBotoes);

        // Adiciona o container limitado ao painel centralizado
        add(painelFormulario);

        // Ações dos botões
        btnVoltar.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
    }
}