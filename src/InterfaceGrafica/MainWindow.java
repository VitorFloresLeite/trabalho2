package InterfaceGrafica;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainWindow {
    // Offset editável da tabela em relação ao topo (em pixels)
    private static final int TABLE_TOP_OFFSET = 10;
    private static final int TABLE_SIDE_OFFSET = 10;
    
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Gerenciador de Alocação de Horários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton editAlocacaoButton = new JButton("Editar Alocação");

        CriarTabelaEPainel();

        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //buttonPanel.add(editAlocacaoButton);
        //frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void CriarTabelaEPainel(){
        frame = new JFrame("Gerenciador de Alocação de Horários");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Tabela de Exemplo", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

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

        // Painel para a tabela com offset editável
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new EmptyBorder(TABLE_TOP_OFFSET, TABLE_SIDE_OFFSET, 10, TABLE_SIDE_OFFSET));
        tablePanel.add(scroll, BorderLayout.CENTER);

        frame.add(tablePanel, BorderLayout.CENTER);
    }
}