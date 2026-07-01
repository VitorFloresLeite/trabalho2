package Interface;

import Modelo.Alocacao;
import java.awt.*;
import javax.swing.*;

public class PainelAlocacao extends JPanel{

    private final Alocacao alocacao;

    public PainelAlocacao(Alocacao alocacao){
        this.alocacao = alocacao;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(110, 60));
        montarConteudo();
    }

    private void montarConteudo() {
        if (alocacao == null) {
            setBackground(new Color(230, 230, 230));
            JLabel label = new JLabel("Livre", SwingConstants.CENTER);
            label.setForeground(Color.DARK_GRAY);
            add(label, BorderLayout.CENTER);
            return;
        }

        setBackground(new Color(200, 230, 255));
        JLabel disciplinaLabel = new JLabel(alocacao.getDisciplina().toString(), SwingConstants.CENTER);
        disciplinaLabel.setFont(disciplinaLabel.getFont().deriveFont(Font.BOLD, 11f));

        JLabel professorLabel = new JLabel(alocacao.getProfessor().getNome(), SwingConstants.CENTER);
        professorLabel.setFont(professorLabel.getFont().deriveFont(10f));

        JPanel textos = new JPanel(new GridLayout(2, 1));
        textos.setOpaque(false);
        textos.add(disciplinaLabel);
        textos.add(professorLabel);
        add(textos, BorderLayout.CENTER);

        setToolTipText(alocacao.toString());
    }
}
