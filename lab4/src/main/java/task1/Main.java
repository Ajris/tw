package task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String FORMAT = "%25s%15s%15s\n";

    private static final int BUFFER_SIZE = 100;
    private static final int TRANSFORMER_COUNT = 5;
    private static final int PROCESSES_COUNT = TRANSFORMER_COUNT + 2;

    private static final int CONSUMER_ID = PROCESSES_COUNT - 1;
    private static final int PRODUCER_ID = 0;

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>(PROCESSES_COUNT);
        Buffer buffer = new Buffer(BUFFER_SIZE);

        addProducer(threadList, buffer);
        addTransformers(threadList, buffer);
        addConsumer(threadList, buffer);

        threadList.forEach(Thread::start);
    }

    private static void addConsumer(List<Thread> threadList, Buffer buffer) {
        Consumer consumer = new Consumer(CONSUMER_ID, buffer);
        threadList.add(new Thread(consumer));
    }

    private static void addTransformers(List<Thread> threadList, Buffer buffer) {
        for(int id = 1; id <= TRANSFORMER_COUNT; id++){
            Transformer cp = new Transformer(id, buffer);
            threadList.add(new Thread(cp));
        }
    }

    private static void addProducer(List<Thread> threadList, Buffer buffer) {
        Producer producer = new Producer(PRODUCER_ID, buffer);
        threadList.add(new Thread(producer));
    }
}