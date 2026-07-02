package Interface;

import Controle.ControlePaineis;
import Modelo.Cursos;
import Modelo.Disciplina;
import java.awt.*;
import javax.swing.*;

public class PainelCadastroTurma extends Painel {

    public PainelCadastroTurma(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Container de tamanho limitado para a Turma
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setOpaque(false);
        painelFormulario.setPreferredSize(new Dimension(450, 550));
        painelFormulario.setMaximumSize(new Dimension(450, 550));

        // Título Interno
        JLabel lblTitulo = new JLabel("Cadastrar Nova Turma");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFormulario.add(lblTitulo);
        painelFormulario.add(Box.createVerticalStrut(20));

        // Seleção de Curso
        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setForeground(Color.WHITE);
        lblCurso.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComboBox<Cursos> cbCursos = new JComboBox<>(Cursos.values());
        cbCursos.setAlignmentX(Component.LEFT_ALIGNMENT);
        // REFINO: Impede o ComboBox de ficar excessivamente alto no BoxLayout
        cbCursos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        painelFormulario.add(lblCurso);
        painelFormulario.add(Box.createVerticalStrut(5));
        painelFormulario.add(cbCursos);
        painelFormulario.add(Box.createVerticalStrut(15));

        // Seleção de Semestre
        JLabel lblSemestre = new JLabel("Semestre Letivo:");
        lblSemestre.setForeground(Color.WHITE);
        lblSemestre.setAlignmentX(Component.LEFT_ALIGNMENT);

        Integer[] semestres = {1, 2, 3, 4, 5, 6, 7, 8};
        JComboBox<Integer> cbSemestre = new JComboBox<>(semestres);
        cbSemestre.setAlignmentX(Component.LEFT_ALIGNMENT);
        // REFINO: Impede o ComboBox de ficar excessivamente alto no BoxLayout
        cbSemestre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        painelFormulario.add(lblSemestre);
        painelFormulario.add(Box.createVerticalStrut(5));
        painelFormulario.add(cbSemestre);
        painelFormulario.add(Box.createVerticalStrut(15));

        // Grade de Disciplinas (Matrizes Esperadas)
        JLabel lblDisciplinas = new JLabel("Disciplinas Requeridas para o Semestre:");
        lblDisciplinas.setForeground(Color.WHITE);
        lblDisciplinas.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelFormulario.add(lblDisciplinas);
        painelFormulario.add(Box.createVerticalStrut(5));

        JPanel gradeCheckboxes = new JPanel(new GridLayout(0, 1, 0, 5));
        gradeCheckboxes.setOpaque(false);

        for (Disciplina disc : Disciplina.values()) {
            JCheckBox cb = new JCheckBox(disc.toString().replace("_", " "));
            cb.setOpaque(false);
            cb.setForeground(Color.WHITE);
            gradeCheckboxes.add(cb);
        }

        JScrollPane scrollDisciplinas = new JScrollPane(gradeCheckboxes);
        scrollDisciplinas.setPreferredSize(new Dimension(450, 180));
        scrollDisciplinas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180)); // REFINO: Trava a altura do Scroll
        scrollDisciplinas.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollDisciplinas.setOpaque(false);
        scrollDisciplinas.getViewport().setOpaque(false);
        painelFormulario.add(scrollDisciplinas);

        painelFormulario.add(Box.createVerticalStrut(20));

        // Botões de Ação
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelBotoes.setOpaque(false);
        painelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);

        Botao btnSalvar = new Botao("Salvar Turma", 10);
        btnSalvar.setPreferredSize(new Dimension(150, 35));
        Botao btnVoltar = new Botao("Voltar", 10);
        btnVoltar.setPreferredSize(new Dimension(100, 35));

        painelBotoes.add(btnSalvar);
        painelBotoes.add(Box.createHorizontalStrut(10));
        painelBotoes.add(btnVoltar);
        painelFormulario.add(painelBotoes);

        add(painelFormulario);

        btnVoltar.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
    }
}