package task1;

import java.util.Random;

import static task1.Main.FORMAT;

public class Transformer implements Runnable {
    private final Buffer buffer;
    private final int id;

    Transformer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int resource = 0; resource < buffer.getBufferSize(); resource++) {
                int resourceVal = buffer.getResource(resource, id);
                Thread.sleep(Math.abs(new Random().nextInt()) % 100);
                buffer.setResource(resource, resourceVal + 1);
                System.out.format(FORMAT, this, resource, resourceVal);
                buffer.releaseResource(resource, id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Transformer{" +
                "id=" + id +
                '}';
    }
}
