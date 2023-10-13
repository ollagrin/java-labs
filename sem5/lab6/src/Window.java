import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class Window extends JFrame {
    private final int[] keys = {65, 66, 83, 68, 90}; //a,s,d,z

    public Window() {
        setTitle("Lab6-sem5");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        Panel panel = new Panel(this);
        Container container = getContentPane();
        container.add(panel);
    }

    private class Panel extends JPanel {
        JList<String> list = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        public Panel(JFrame frame) {
            CustomPanel customPanel = new CustomPanel();

            KeyAction action = new KeyAction();
            customPanel.addPanelListener(action);


            JLabel label = new JLabel("Press key('a', 's', 'd' or 'z') to get some result");
            add(label, "NORTH");
            JPanel panel = new JPanel();
            list.setFixedCellWidth(350);
            panel.add(list);
            JScrollPane scrollPane = new JScrollPane(list);
            panel.add(scrollPane);
            add(panel, "SOUTH");

            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (Arrays.stream(keys)
                            .boxed()
                            .collect(Collectors.toSet())
                            .contains(e.getKeyCode())) {

                        PanelEvent panelEvent = new PanelEvent(customPanel, String.valueOf(e.getKeyChar()));
                        EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
                        queue.postEvent(panelEvent);
                    }
                }
            });
        }

        private class KeyAction implements PanelListener {
            @Override
            public void keyPressed(PanelEvent e) {
                StringBuilder stringBuilder = new StringBuilder().
                        append("Date: ").
                        append(e.getDate().toString()).
                        append(" ").
                        append("Key pressed: ").append(e.getKey());
                listModel.addElement(stringBuilder.toString());
                list.setModel(listModel);
            }
        }
    }


}
