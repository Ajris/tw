package task2.fair;

import java.util.Random;

public class Consumer implements Runnable {
    private Random generator = new Random();
    private final Buffer buffer;
    private final int maxLen;

    Consumer(Buffer buffer, int maxLen) {
        this.buffer = buffer;
        this.maxLen = maxLen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int productSize = generator.nextInt(maxLen - 1) + 1;
                String dataFromBuffer = buffer.get(productSize);
                System.out.format("%15s%15s%15s\n", "Consumer", productSize, dataFromBuffer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}