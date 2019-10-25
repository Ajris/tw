package task2;

public class Semaphore {
    private volatile int count;

    Semaphore(int count) {
        this.count = count;
    }

    synchronized void p() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
    }

    synchronized void v() {
        count++;
        notifyAll();
    }
}
