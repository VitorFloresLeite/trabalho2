package Interface;

import Controle.ControlePaineis;
import Modelo.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PainelCadastroProfessor extends Painel {

    public PainelCadastroProfessor(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setOpaque(false);
        formulario.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel painelNome = new JPanel(new BorderLayout(5, 5));
        painelNome.setOpaque(false);
        JLabel labelNome = new JLabel("Nome do professor:");
        labelNome.setForeground(Color.WHITE);
        JTextField campoNome = new JTextField();
        painelNome.add(labelNome, BorderLayout.NORTH);
        painelNome.add(campoNome, BorderLayout.CENTER);
        formulario.add(painelNome);
        formulario.add(Box.createVerticalStrut(10));

        JPanel painelCompetencias = new JPanel();
        painelCompetencias.setLayout(new BoxLayout(painelCompetencias, BoxLayout.Y_AXIS));
        painelCompetencias.setOpaque(false);
        JLabel labelCompetencias = new JLabel("Competências:");
        labelCompetencias.setForeground(Color.WHITE);
        painelCompetencias.add(labelCompetencias);

        JPanel gradesCompetencias = new JPanel(new GridLayout(0, 2, 8, 5));
        gradesCompetencias.setOpaque(false);
        List<JCheckBox> caixasCompetencia = new ArrayList<>();
        for (Disciplina disciplina : Disciplina.values()) {
            JCheckBox checkBox = new JCheckBox(disciplina.toString().replace("_", " "));
            checkBox.setOpaque(false);
            checkBox.setForeground(Color.WHITE);
            caixasCompetencia.add(checkBox);
            gradesCompetencias.add(checkBox);
        }
        painelCompetencias.add(gradesCompetencias);
        formulario.add(painelCompetencias);
        formulario.add(Box.createVerticalStrut(10));

        JPanel painelDisponibilidade = new JPanel();
        painelDisponibilidade.setLayout(new BoxLayout(painelDisponibilidade, BoxLayout.Y_AXIS));
        painelDisponibilidade.setOpaque(false);
        JLabel labelDisponibilidade = new JLabel("Disponibilidade:");
        labelDisponibilidade.setForeground(Color.WHITE);
        painelDisponibilidade.add(labelDisponibilidade);

        JPanel painelHorario = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        painelHorario.setOpaque(false);
        JComboBox<Dias> comboDias = new JComboBox<>(Dias.values());
        JComboBox<Turnos> comboTurnos = new JComboBox<>(Turnos.values());
        JComboBox<Periodos> comboPeriodos = new JComboBox<>(Periodos.values());
        painelHorario.add(new JLabel("Dia:"));
        painelHorario.add(comboDias);
        painelHorario.add(new JLabel("Turno:"));
        painelHorario.add(comboTurnos);
        painelHorario.add(new JLabel("Período:"));
        painelHorario.add(comboPeriodos);
        painelDisponibilidade.add(painelHorario);

        DefaultListModel<String> modeloHorarios = new DefaultListModel<>();
        JList<String> listaHorarios = new JList<>(modeloHorarios);
        listaHorarios.setVisibleRowCount(4);
        listaHorarios.setSelectionBackground(new Color(70, 130, 180));
        JScrollPane scrollHorarios = new JScrollPane(listaHorarios);
        painelDisponibilidade.add(Box.createVerticalStrut(5));
        painelDisponibilidade.add(scrollHorarios);

        JPanel painelBotoesHorario = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        painelBotoesHorario.setOpaque(false);
        Botao botaoAdicionarHorario = new Botao("Adicionar horário", 10);
        Botao botaoRemoverHorario = new Botao("Remover seleção", 10);
        painelBotoesHorario.add(botaoAdicionarHorario);
        painelBotoesHorario.add(botaoRemoverHorario);
        painelDisponibilidade.add(painelBotoesHorario);
        formulario.add(painelDisponibilidade);

        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelAcoes.setOpaque(false);
        Botao botaoSalvar = new Botao("Salvar professor", 10);
        painelAcoes.add(botaoSalvar);
        formulario.add(Box.createVerticalStrut(10));
        formulario.add(painelAcoes);

        add(formulario, BorderLayout.CENTER);

        List<Horario> horariosSelecionados = new ArrayList<>();

        botaoAdicionarHorario.addActionListener(e -> {
            Horario horario = new Horario(
                    (Dias) comboDias.getSelectedItem(),
                    (Turnos) comboTurnos.getSelectedItem(),
                    (Periodos) comboPeriodos.getSelectedItem()
            );
            if (!horariosSelecionados.contains(horario)) {
                horariosSelecionados.add(horario);
                modeloHorarios.addElement(horario.toString());
            }
        });

        botaoRemoverHorario.addActionListener(e -> {
            int indiceSelecionado = listaHorarios.getSelectedIndex();
            if (indiceSelecionado >= 0) {
                horariosSelecionados.remove(indiceSelecionado);
                modeloHorarios.remove(indiceSelecionado);
            }
        });

        botaoSalvar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o nome do professor.");
                return;
            }

            Professor professor = new Professor(nome);
            for (JCheckBox caixa : caixasCompetencia) {
                if (caixa.isSelected()) {
                    professor.adicionarCompetencia(Disciplina.valueOf(caixa.getText().replace(' ', '_').toUpperCase()));
                }
            }
            for (Horario horario : horariosSelecionados) {
                professor.adicionarDisponibilidade(horario);
            }

            JOptionPane.showMessageDialog(this,
                    "Professor cadastrado com sucesso:\n" + professor.getNome() + "\n" +
                            "Competências: " + professor.getCompetencias() + "\n" +
                            "Disponibilidade: " + professor.getDisponibilidade());
            
                                        
        });

        Botao botaoVoltar = new Botao("Voltar", 10);
        botaoVoltar.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
        painelAcoes.add(botaoVoltar);
    }
}
