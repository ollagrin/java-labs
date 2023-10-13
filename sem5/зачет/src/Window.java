import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Window extends JFrame {

    public Window() {
        setTitle("Пересдача");
        setSize(800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        CirclePanel circlePanel = new CirclePanel();
        Container container = this.getContentPane();
        container.add(circlePanel);

        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new ColorThread(circlePanel));

        RunStopAction action = new RunStopAction();
        circlePanel.addSpaceListener(action);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    CircleEvent circleEvent = new CircleEvent(circlePanel);
                    EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
                    queue.postEvent(circleEvent);
                }
            }
        });

        executor.execute(new MovingThread(circlePanel, lock));
    }

    private AtomicBoolean captured = new AtomicBoolean(false);
    private Lock lock = new ReentrantLock();

    private class RunStopAction implements SpaceListener {
        @Override
        public void spaceClicked(CircleEvent e) {
            if (!captured.get()) {
                lock.lock();
                captured.set(true);
            } else {
                lock.unlock();
                captured.set(false);
            }
        }
    }

}
