import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class ColorThread implements Runnable {
    private CirclePanel panel;

    public ColorThread(CirclePanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            final Random random = new Random();
            Color color = new Color(random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256),
                    random.nextInt(256));
            panel.setColor(color);
            panel.repaint();
        }

        /*ColorAction action = new ColorAction();
            panel.addColorListener(action);*/
    }

    /*private class ColorAction implements ColorListener {

        @Override
        public void colorChange(CircleEvent event) {
            while (true) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                final Random random = new Random();
                Color color = new Color(random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256));
                panel.setColor(color);
                panel.repaint();
            }
        }
    }*/
}


