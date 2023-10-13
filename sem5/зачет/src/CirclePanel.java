import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.EventListener;

public class CirclePanel extends JPanel {
    private Color color;
    private Point point;

    public CirclePanel() {
        this.color = Color.CYAN;
        this.point = new Point(0,0);
        listenerList = new EventListenerList();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(point.x, point.y, 100, 100);
        g.setColor(color);
        g.fillOval(point.x, point.y, 100, 100);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addSpaceListener(SpaceListener l) {
        listenerList.add(SpaceListener.class, l);
    }

    public void removeSpaceListener(SpaceListener l) {
        listenerList.remove(SpaceListener.class, l);
    }

    @Override
    public void processEvent(AWTEvent event) {
        if (event instanceof CircleEvent) {
            EventListener[] listeners = listenerList.getListeners(SpaceListener.class);
            for (EventListener listener : listeners) ((SpaceListener) listener).spaceClicked((CircleEvent) event);
        } else
            super.processEvent(event);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}

