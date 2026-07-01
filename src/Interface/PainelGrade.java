package Interface;
import Controle.*;
import java.awt.*;
import javax.swing.*;

public class PainelGrade extends Painel{
    // private static final int TABLE_TOP_OFFSET = 10;
    // private static final int TABLE_SIDE_OFFSET = 10;
    public PainelGrade(String titulo, ControlePaineis controle) {
        super(titulo, controle);
    }
    
    @Override
    protected void inicializarComponentes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        String[] columns = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        Object[][] data = {
            {"Periodo 1", "Periodo 1", "Periodo 1", "Periodo 1", "Periodo 1"},
            {"Periodo 2", "Periodo 2", "Periodo 2", "Periodo 2", "Periodo 2"},
            {"Periodo 3", "Periodo 3", "Periodo 3", "Periodo 3", "Periodo 3"},
            {"Periodo 4", "Periodo 4", "Periodo 4", "Periodo 4", "Periodo 4"},
            {"Periodo 5", "Periodo 5", "Periodo 5", "Periodo 5", "Periodo 5"},
            {"Periodo 6", "Periodo 6", "Periodo 6", "Periodo 6", "Periodo 6"}
        };

        JTable table = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(600, 200));
        scroll.setMinimumSize(new Dimension(300, 120));

        add(scroll);
        add(Box.createVerticalStrut(20));

        Botao button = new Botao("Voltar ao painel inicial", 10);
        button.addActionListener(e -> controle.trocarPainel(PaineisDoPrograma.INICIAL));
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);
        
    }
}
