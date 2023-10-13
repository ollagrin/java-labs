import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Window extends JFrame {

    private boolean isStoppedRadius = false;
    private boolean isStoppedColor = false;
    private final Lock lock = new ReentrantLock();
    private final Object object = new Object();

    public Window() {
        setTitle("Пересдача");
        setSize(800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        MyPanel panel = new MyPanel();
        Container container = getContentPane();
        container.add(panel);

        Executor executor = Executors.newFixedThreadPool(2);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isStoppedColor = !isStoppedColor;
                    if (!isStoppedColor) {
                        synchronized (object) {
                            object.notify();
                            System.out.println("notify");
                        }
                    }
                }
            }
        });
        executor.execute(new ColorThread(panel));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!isStoppedRadius) {
                        lock.lock();
                        isStoppedRadius = true;
                    } else {
                        lock.unlock();
                        isStoppedRadius = false;
                    }
                }
            }
        });

        executor.execute(new BiggerThread(panel));
    }

    class ColorThread implements Runnable {
        private MyPanel panel;

        public ColorThread(MyPanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            final Random random = new Random();
            while (true) {
                if (isStoppedColor)
                    synchronized (object) {
                        try {
                            System.out.println("wait");
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                    Color color = new Color(random.nextInt(256),
                            random.nextInt(256),
                            random.nextInt(256),
                            random.nextInt(256));
                    panel.setColor(color);
                    panel.repaint();
            }
        }
    }

    class BiggerThread implements Runnable {
        private MyPanel panel;

        public BiggerThread(MyPanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
                int radius;
                lock.lock();
                try {
                    radius = panel.getRadius() + 1;
                } finally {
                    lock.unlock();
                }

                panel.setRadius(radius);
                panel.repaint();
            }
        }
    }
}
