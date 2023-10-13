import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.EventListener;

public class CustomPanel extends JPanel {
    public CustomPanel() {
        listenerList = new EventListenerList();
    }

    public void addPanelListener(PanelListener l) {
        listenerList.add(PanelListener.class, l);
    }

    public void removePanelListener(PanelListener l) {
        listenerList.remove(PanelListener.class, l);
    }

    public void processEvent(AWTEvent event) {
        if (event instanceof PanelEvent) {
            EventListener[] listeners = listenerList.getListeners(PanelListener.class);
            for (EventListener listener : listeners) ((PanelListener) listener).keyPressed((PanelEvent) event);
        } else
            super.processEvent(event);
    }
}
