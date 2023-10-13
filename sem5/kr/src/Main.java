import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {

        StringBuffer stringBuffer = new StringBuffer();
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(new FirstThread(stringBuffer, lock));
        Thread thread2 = new Thread(new SecondThread(stringBuffer, lock));
        thread1.start();
        thread2.start();
    }
}

class FirstThread implements Runnable {
    private StringBuffer stringBuffer;
    private Lock lock;

    public FirstThread(StringBuffer stringBuffer, Lock lock) {
        this.stringBuffer = stringBuffer;
        this.lock = lock;
    }

    @Override
    public void run() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        
        for (int i = 0; i < 50; ++i) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            char c = alphabet.charAt(r.nextInt(alphabet.length()));
            lock.lock();
            try {
                stringBuffer.insert(0, c);
            } finally {
                lock.unlock();
            }
            System.out.println(stringBuffer);
        }
    }
}

class SecondThread implements Runnable {
    private StringBuffer stringBuffer;
    private Lock lock;

    public SecondThread(StringBuffer stringBuffer, Lock lock) {
        this.stringBuffer = stringBuffer;
        this.lock = lock;
    }


    @Override
    public void run() {

        for (int i = 0; i < 50; ++i) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            } finally {
                lock.unlock();
            }
            System.out.println(stringBuffer);
        }
    }
}


