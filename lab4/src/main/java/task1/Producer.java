package task1;

import java.util.Random;

public class Producer implements Runnable {
    private static final int STARTING_VALUE = 0;
    private final Buffer buffer;
    private final int id;

    Producer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int resource = 0; resource < buffer.getBufferSize(); resource++) {
                buffer.getResource(resource, id);
                Thread.sleep(Math.abs(new Random().nextInt()) % 100);
                buffer.setResource(resource, STARTING_VALUE);
                System.out.format(Main.FORMAT, this, resource, STARTING_VALUE);
                buffer.releaseResource(resource, id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                '}';
    }
}
