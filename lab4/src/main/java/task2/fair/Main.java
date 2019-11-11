package task2.fair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int INPUT_SIZE = 10;
    private static final int PRODUCERS = 3;
    private static final int CONSUMERS = 3;

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        Buffer buffer = new Buffer(INPUT_SIZE * 2);

        for(int i = 0; i < PRODUCERS; i++){
            Producer producer = new Producer(buffer, INPUT_SIZE);
            threads.add(new Thread(producer));
        }

        for(int i = 0; i < CONSUMERS; i++){
            Consumer consumer = new Consumer(buffer, INPUT_SIZE);
            threads.add(new Thread(consumer));
        }

        threads.forEach(Thread::start);
    }
}