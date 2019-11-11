package task2.fair;

import java.util.Random;

public class Producer implements Runnable {
    private Random generator = new Random();
    private final Buffer buffer;
    private final int maxLen;

    Producer(Buffer buffer, int maxLen) {
        this.buffer = buffer;
        this.maxLen = maxLen;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++){
                int productSize = generator.nextInt(maxLen - 1) + 1;
                String product = generateProduct(productSize);
                buffer.put(product);
                System.out.format("%15s%15s%15s\n", "Producer", productSize, product);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String generateProduct(int productSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < productSize; i++) {
            stringBuilder.append(getRandomChar());
        }
        return stringBuilder.toString();
    }

    private char getRandomChar() {
        return (char)(generator.nextInt(26) + 97);
    }
}