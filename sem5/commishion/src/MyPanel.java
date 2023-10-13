import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private Color color;
    private int radius;

    public MyPanel() {
        this.color = Color.CYAN;
        this.radius = 10;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(this.getWidth() / 2 - this.radius, this.getHeight() / 2 - this.radius,
                2 * this.radius, 2 * this.radius);
        g.setColor(color);
        g.fillRect(this.getWidth() / 2 - this.radius, this.getHeight() / 2 - this.radius,
                2 * this.radius, 2 * this.radius);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
