import Interface.*;

public class Main {
    // Offset editável da tabela em relação ao topo (em pixels)
    // 
    
    private static Janela frame;

    public static void main(String[] args) {
        frame = new JanelaInicial("janela de teste"); 
        //SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }

    //private static void createAndShowGUI() {
        //frame = new JanelaInicial();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JButton editAlocacaoButton = new JButton("Editar Alocação");

        // CriarTabelaEPainel();

        // //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // //buttonPanel.add(editAlocacaoButton);
        // //frame.add(buttonPanel, BorderLayout.SOUTH);
        // Scanner scanner = new Scanner(System.in);
        
        
        // frame.setSize(1000, 650);
        // frame.setLocationRelativeTo(null);
        // for(int i=0;i<10;i++){
            
        //     int num=scanner.nextInt();
        //     scanner.nextLine(); // Consume the newline character
        //     if(num==1){
        //         frame.setVisible(true);
        //     }else{
        //         frame.setVisible(false);
        //     }
        // }
        // scanner.close();
    //}

    // private  void CriarTabelaEPainel(){
    //     
    //}
}