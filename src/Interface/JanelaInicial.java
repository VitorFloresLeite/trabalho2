package Interface;
import java.awt.*;
import javax.swing.*;

public class JanelaInicial extends Janela {
    
    public JanelaInicial(String titulo) {
        super(titulo);
    }

    private static final int TABLE_TOP_OFFSET = 10;
    private static final int TABLE_SIDE_OFFSET = 10;

    @Override
    protected void inicializarComponentes() {
        
        // Cria um painel para organizar a tabela e o botão verticalmente
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Tabela de Exemplo", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

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

        painel.add(scroll);
        painel.add(Box.createVerticalStrut(10));

        Botao button = new Botao("Clique aqui", 10);
        button.addActionListener(e -> button.setText("Botão clicado!"));
        painel.add(button);
        
        getContentPane().add(painel, BorderLayout.CENTER);
    }
}