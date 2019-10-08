package task2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        int count = 100;
        Consumer consumer = new Consumer(buffer, count);
        Producer producer = new Producer(buffer, count);
        Thread consThread = new Thread(consumer);
        Thread prodThread = new Thread(producer);

        long start = System.currentTimeMillis();
        prodThread.start();
        consThread.start();
        consThread.join();
        prodThread.join();
        long end = System.currentTimeMillis();

        System.out.println("TIME: " + (end - start));
    }
}
