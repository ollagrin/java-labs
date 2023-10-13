import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import java.util.EventObject;

public class CircleEvent extends AWTEvent {
    private Color color;

    public CircleEvent(CirclePanel panel) {
        super(panel, EVENT_ID);
        this.color = panel.getColor();
    }

    public static final int EVENT_ID = AWTEvent.RESERVED_ID_MAX + 5555;

    public Color getColor() {
        return color;
    }
}
