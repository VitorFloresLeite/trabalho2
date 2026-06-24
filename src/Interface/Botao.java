package Interface;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Botao extends JButton {
    private final int cornerRadius;

    // Constructor accepts the text and the arc radius for the corners
    public Botao(String label) {
        super(label);
        this.cornerRadius = 0; // Default corner radius
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    public Botao(String label, int radius) {
        super(label);
        this.cornerRadius = radius;
        
        // Prevent Swing from painting default rectangle background
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    // Paint the background
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (getModel().isArmed()) {
            g2.setColor(Color.LIGHT_GRAY);
        } else {
            g2.setColor(getBackground());
        }
        
        // Paint rounded background using the custom radius
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
        
        super.paintComponent(g);
    }

    // Paint the border
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        
        // Paint rounded border outline using the custom radius
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }

    // Restrict the clickable area to the rounded shape
    private Shape shape;
    @Override
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        }
        return shape.contains(x, y);
    }
}
