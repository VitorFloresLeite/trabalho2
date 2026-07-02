package Interface;

import Controle.ControlePaineis;
import Modelo.Turma;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PainelExibirTurmas extends Painel {

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public PainelExibirTurmas(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }

    @Override
    protected void inicializarComponentes() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] colunas = {"Curso", "Semestre", "Possui Alocação?"};
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
    public void atualizarTabela(List<Turma> turmas, Modelo.Grade gradeAtual) {
        modeloTabela.setRowCount(0); // Limpa a tabela atual
        for (Turma t : turmas) {
            boolean temAlocacao = false;
            if(gradeAtual != null && gradeAtual.getAlocacoes() != null){
                for(Modelo.Alocacao alocacao : gradeAtual.getAlocacoes()){
                    if(alocacao.getTurma().equals(t)){
                        temAlocacao = true;
                        break;
                    }
                }
            }
            String alocado = temAlocacao ? "Sim" : "Não";
            String nomeCurso = t.getCurso().toString().replace("_", " ");
            modeloTabela.addRow(new Object[]{nomeCurso, t.getSemestre(), alocado});
        }
    }
}