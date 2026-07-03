package Interface;

import Controle.ControlePaineis;
import Modelo.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class PainelCadastroTurma extends Painel {

    public PainelCadastroTurma(String titulo, ControlePaineis controle) {
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

        // Seleção do Curso (Enum Cursos)
        JPanel painelCurso = new JPanel(new BorderLayout(5, 5));
        painelCurso.setOpaque(false);
        painelCurso.setMaximumSize(new Dimension(300, 20));
        JLabel labelCurso = new JLabel("Curso do Semestre:");
        labelCurso.setFont(new Font("Arial", Font.BOLD, 15));
        labelCurso.setForeground(Color.WHITE);
        JComboBox<Cursos> comboCursos = new JComboBox<>(Cursos.values());
        painelCurso.add(labelCurso, BorderLayout.NORTH);
        painelCurso.add(comboCursos, BorderLayout.CENTER);
        formulario.add(painelCurso);
        formulario.add(Box.createVerticalStrut(10));

        // Seleção do Semestre (combo numérico baseado nos limites comuns de 1 a 8)
        JPanel painelSemestre = new JPanel(new BorderLayout(5, 5));
        painelSemestre.setOpaque(false);
        painelSemestre.setMaximumSize(new Dimension(300, 20));
        JLabel labelSemestre = new JLabel("Semestre:");
        labelSemestre.setFont(new Font("Arial", Font.BOLD, 15));
        labelSemestre.setForeground(Color.WHITE);
        Integer[] semestres = {1, 2, 3, 4, 5, 6, 7, 8};
        JComboBox<Integer> comboSemestre = new JComboBox<>(semestres);
        painelSemestre.add(labelSemestre, BorderLayout.NORTH);
        painelSemestre.add(comboSemestre, BorderLayout.CENTER);
        formulario.add(painelSemestre);
        formulario.add(Box.createVerticalStrut(10));

        // Seleção de Horários da Turma (Preencher a lista interna de horários da turma)
        JPanel painelHorariosTurma = new JPanel();
        painelHorariosTurma.setLayout(new BoxLayout(painelHorariosTurma, BoxLayout.Y_AXIS));
        painelHorariosTurma.setOpaque(false);
        JLabel labelHorarios = new JLabel("Horários da Turma:");
        labelHorarios.setForeground(Color.WHITE);
        painelHorariosTurma.add(labelHorarios);

        JPanel painelHorarioSeletor = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        painelHorarioSeletor.setOpaque(false);
        JComboBox<Dias> comboDias = new JComboBox<>(Dias.values());
        JComboBox<Turnos> comboTurnos = new JComboBox<>(Turnos.values());
        JComboBox<Periodos> comboPeriodos = new JComboBox<>(Periodos.values());
        painelHorarioSeletor.add(new JLabel("Dia:"));
        painelHorarioSeletor.add(comboDias);
        painelHorarioSeletor.add(new JLabel("Turno:"));
        painelHorarioSeletor.add(comboTurnos);
        painelHorarioSeletor.add(new JLabel("Período:"));
        painelHorarioSeletor.add(comboPeriodos);
        painelHorariosTurma.add(painelHorarioSeletor);

        DefaultListModel<String> modeloHorarios = new DefaultListModel<>();
        JList<String> listaHorarios = new JList<>(modeloHorarios);
        listaHorarios.setVisibleRowCount(4);
        listaHorarios.setSelectionBackground(new Color(70, 130, 180));
        JScrollPane scrollHorarios = new JScrollPane(listaHorarios);
        painelHorariosTurma.add(Box.createVerticalStrut(5));
        painelHorariosTurma.add(scrollHorarios);

        JPanel painelBotoesHorario = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        painelBotoesHorario.setOpaque(false);
        Botao botaoAdicionarHorario = new Botao("Adicionar horário", 10);
        Botao botaoRemoverHorario = new Botao("Remover seleção", 10);
        painelBotoesHorario.add(botaoAdicionarHorario);
        painelBotoesHorario.add(botaoRemoverHorario);
        painelHorariosTurma.add(painelBotoesHorario);
        formulario.add(painelHorariosTurma);

        // Painel de Ações inferiores
        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelAcoes.setOpaque(false);
        Botao botaoSalvar = new Botao("Salvar turma", 10);
        Botao botaoVoltar = new Botao("Voltar", 10);
        painelAcoes.add(botaoSalvar);
        painelAcoes.add(botaoVoltar);
        formulario.add(Box.createVerticalStrut(10));
        formulario.add(painelAcoes);

        add(formulario, BorderLayout.CENTER);

        List<Horario> horariosSelecionados = new ArrayList<>();

        // Ação para adicionar horários na listagem visual temporária
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

        // Ação para remover o horário selecionado da listagem visual temporária
        botaoRemoverHorario.addActionListener(e -> {
            int indiceSelecionado = listaHorarios.getSelectedIndex();
            if (indiceSelecionado >= 0) {
                horariosSelecionados.remove(indiceSelecionado);
                modeloHorarios.remove(indiceSelecionado);
            }
        });

        // Ação para instanciar e salvar o objeto mapeado
        botaoSalvar.addActionListener(e -> {
            Cursos curso = (Cursos) comboCursos.getSelectedItem();
            int semestre = (Integer) comboSemestre.getSelectedItem();

            // Instancia o objeto do modelo Turma
            Turma turma = new Turma(curso, semestre);
            
            // Alimenta a lista de horários da turma usando o método setHorarios existente
            for (Horario horario : horariosSelecionados) {
                turma.setHorarios(horario);
            }

            try{
                controle.cadastrarTurma(turma);
                JOptionPane.showMessageDialog(this,
                    "Turma cadastrada com sucesso:\n" +
                            "Curso: " + turma.getCurso().toString().replace('_', ' ') + "\n" +
                            "Semestre: " + turma.getSemestre() + "º Semestre\n" +
                            "Quantidade de horários: " + horariosSelecionados.size());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar turma: " + ex.getMessage());
                return;
            }
            
        });

        botaoVoltar.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
    }
}