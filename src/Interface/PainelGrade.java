package Interface;
import Controle.*;
import Modelo.Alocacao;
import Modelo.Grade;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PainelGrade extends Painel{
    private JTable tabelaGrade;

    public PainelGrade(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }
    
    @Override
    protected void inicializarComponentes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        tabelaGrade = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaGrade.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tabelaGrade);
        scroll.setPreferredSize(new Dimension(600, 200));
        scroll.setMinimumSize(new Dimension(300, 120));

        add(scroll);
        add(Box.createVerticalStrut(20));

        Botao button = new Botao("Voltar ao painel inicial", 10);
        button.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);
        
    }

    private class DadosGrade{
        public final String[] Colunas;
        public final Object[][] Dados;
        public DadosGrade(String[] col, Object[][] data){
            Colunas = col;
            Dados = data;
        }
    }
    private DadosGrade ConverterGrade(Grade grade){
        String [] colunas = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        Object[][] dadoss = new Object[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                dadoss[i][j] = "não reservado";
            }
        }
        List<Alocacao> alocacoes = grade.getAlocacoes();
        for (Alocacao a : alocacoes) {
            int col = a.getHorario().getDia().ordinal();
            if (col < 0 || col >= 5) continue;
            int row = a.getHorario().getTurno().ordinal() * 2 + a.getHorario().getPeriodo().ordinal();
            dadoss[row][col] = a.getDisciplina().toString();
        }
        return new DadosGrade(colunas, dadoss);
    }
}
