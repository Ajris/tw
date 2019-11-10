package task1;

import java.util.Random;

public class Consumer implements Runnable {
    private final Buffer buffer;
    private final int id;

    Consumer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int resource = 0; resource < buffer.getBufferSize(); resource++) {
                int resourceVal = buffer.getResource(resource, id);
                Thread.sleep(Math.abs(new Random().nextInt()) % 100);
                System.out.format(Main.FORMAT, this, resource, resourceVal);
                buffer.releaseResource(resource, id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                '}';
    }
}
