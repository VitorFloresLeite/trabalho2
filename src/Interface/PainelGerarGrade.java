package Interface;

import Controle.ControlePaineis;
import Controle.ControleDados;
import Modelo.BalanceStrategy;
import Modelo.SimpleStrategy;
import Modelo.GeradorGrade;
import Modelo.Grade;
import java.awt.*;
import javax.swing.*;

public class PainelGerarGrade extends Painel {

    private JLabel lblEstrategiaAtual;
    private final GeradorGrade geradorGrade;
    private final ControleDados controleDados;

    public PainelGerarGrade(String titulo, ControlePaineis controle, ControleDados controleDados) {
        super(titulo, controle);
        this.controleDados = controleDados;
        this.geradorGrade = new GeradorGrade(new SimpleStrategy());
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título/Instrução
        JLabel lblInstrucao = new JLabel("Selecione a Estratégia de Alocação:");
        lblInstrucao.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblInstrucao.setForeground(Color.WHITE);
        lblInstrucao.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblInstrucao);

        add(Box.createVerticalStrut(15));

        // Feedback dinâmico
        lblEstrategiaAtual = new JLabel("Estratégia Atual: SimpleStrategy (Padrão)");
        lblEstrategiaAtual.setFont(new Font("SansSerif", Font.ITALIC, 13));
        lblEstrategiaAtual.setForeground(new Color(241, 196, 15));
        lblEstrategiaAtual.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblEstrategiaAtual);

        add(Box.createVerticalStrut(20));

        // Painel de botões em FlowLayout (Não sofre stretching)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        painelBotoes.setOpaque(false);

        Botao btnSimple = new Botao("SimpleStrategy", 10);
        btnSimple.setPreferredSize(new Dimension(150, 35));
        btnSimple.addActionListener(e -> {
            geradorGrade.setEstrategia(new SimpleStrategy());
            lblEstrategiaAtual.setText("Estratégia Atual: SimpleStrategy");
        });

        Botao btnBalance = new Botao("BalanceStrategy", 10);
        btnBalance.setPreferredSize(new Dimension(150, 35));
        btnBalance.addActionListener(e -> {
            geradorGrade.setEstrategia(new BalanceStrategy());
            lblEstrategiaAtual.setText("Estratégia Atual: BalanceStrategy");
        });

        painelBotoes.add(btnSimple);
        painelBotoes.add(btnBalance);
        add(painelBotoes);

        add(Box.createVerticalStrut(30));

        // Botão para Processar/Gerar Grade
        Botao btnGerar = new Botao("Gerar Grade Horária", 10);
        btnGerar.setPreferredSize(new Dimension(200, 40));
        btnGerar.setMaximumSize(new Dimension(200, 40)); // REFINO: Garante tamanho fixo ao botão
        btnGerar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnGerar.addActionListener(e -> {
            try {
                Grade novaGrade = geradorGrade.gerar(
                        java.util.Arrays.asList(Modelo.Disciplina.values()),
                        controleDados.getProfessores(),
                        controleDados.getTurmas()
                );

                controle.setGradeGerada(novaGrade);
                JOptionPane.showMessageDialog(this, "Grade Horária gerada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                controle.trocarPainel(PaineisDoPrograma.GRADE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao gerar grade: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(btnGerar);
    }
}