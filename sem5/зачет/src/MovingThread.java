import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MovingThread implements Runnable {
    private CirclePanel panel;
    private Lock lock;

    public MovingThread(CirclePanel panel, Lock lock) {
        this.panel = panel;
        this.lock = lock;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
            Point point;
            if (panel.getPoint().x == panel.getWidth() - 100) {
                flag = false;
            }
            if (panel.getPoint().x == 0) {
                flag = true;
            }
            lock.lock();
            try {
                if (flag) {
                    point = new Point(panel.getPoint().x + 1, panel.getPoint().y + 1);
                } else {
                    point = new Point(panel.getPoint().x - 1, panel.getPoint().y - 1);
                }
            } finally {
                lock.unlock();
            }

            panel.setPoint(point);
            panel.repaint();
        }
    }

}