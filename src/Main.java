import Controle.*;
import Interface.*;

public class Main {
    
    private static ControlePaineis controlePaineis;
    private static JanelaPrincipal janelaPrincipal;
    
    public static void main(String[] args) {
        janelaPrincipal = new JanelaPrincipal("Janela Principal");
        controlePaineis = new ControlePaineis(janelaPrincipal);
        janelaPrincipal.setControle(controlePaineis);
    }
}