package Interface;

import Controle.ControlePaineis;
import Modelo.Professor;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PainelExibirProfessores extends Painel {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public PainelExibirProfessores(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configuração do modelo da tabela para não permitir edição das células
        String[] colunas = {"Nome do Professor", "Competências"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior para o botão voltar
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSul.setOpaque(false);
        
        Botao btnVoltar = new Botao("Voltar ao painel inicial", 10);
        btnVoltar.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
        painelSul.add(btnVoltar);
        
        add(painelSul, BorderLayout.SOUTH);
    }

    // Método para ser chamado pelo ControlePaineis antes de exibir a tela
    public void atualizarTabela(List<Professor> professores) {
        modeloTabela.setRowCount(0); // Limpa a tabela atual
        for (Professor p : professores) {
            // Formata a lista de competências para exibição em texto
            String competencias = p.getCompetencias().toString().replace("[", "").replace("]", "");
            modeloTabela.addRow(new Object[]{p.getNome(), competencias});
        }
    }
}