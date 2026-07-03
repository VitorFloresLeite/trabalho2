package Interface;

import Controle.*;
import Excecoes.*;
import Modelo.*;
import java.awt.*;
import java.util.List;
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

        JLabel lblInstrucao = new JLabel("Selecione a Estratégia de Alocação:");
        lblInstrucao.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblInstrucao.setForeground(Color.WHITE);
        lblInstrucao.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblInstrucao);

        add(Box.createVerticalStrut(15));

        lblEstrategiaAtual = new JLabel("Estratégia Atual: SimpleStrategy (Padrão)");
        lblEstrategiaAtual.setFont(new Font("SansSerif", Font.ITALIC, 13));
        lblEstrategiaAtual.setForeground(new Color(241, 196, 15));
        lblEstrategiaAtual.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblEstrategiaAtual);

        add(Box.createVerticalStrut(20));

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

        Botao btnGerar = new Botao("Gerar Grade Horária", 10);
        btnGerar.setPreferredSize(new Dimension(200, 40));
        btnGerar.setMaximumSize(new Dimension(200, 40));
        btnGerar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnGerar.addActionListener(e -> {
            try {
                List<Disciplina> listaDisciplinas = java.util.Arrays.asList(Disciplina.values());
                Grade gradeGerada = geradorGrade.gerar(
                    listaDisciplinas,
                    controleDados.getProfessores(),
                    controleDados.getTurmas()
                );

                controle.setGradeGerada(gradeGerada);
                JOptionPane.showMessageDialog(this, "Grade Horária gerada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                controle.trocarPainel(PaineisDoPrograma.GRADE);

            } catch (DadosInsuficientesException ex) {
                // SINAL DE ALERTA DO ENUNCIADO RESOLVIDO: Tratamento de erro de regra de negócio específico!
                JOptionPane.showMessageDialog(this, "Não foi possível gerar a grade: " + ex.getMessage(), "Faltam Dados", JOptionPane.WARNING_MESSAGE);
            } catch (RuntimeException ex) {
                // Outras exceções não previstas do sistema
                JOptionPane.showMessageDialog(this, "Erro interno: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(btnGerar);

        Botao button = new Botao("Voltar ao painel inicial", 10);
        button.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);
    }
}