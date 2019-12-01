package task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final int forksCount = 15;
    static List<Lock> forks = new ArrayList<>();
    static Semaphore waiter = new Semaphore(4);

    public static void main(String[] args) throws InterruptedException, IOException {
        for (int i = 0; i < forksCount; i++)
            forks.add(new ReentrantLock());

        List<Philosopher> philosophers = new ArrayList<>();
        for (int i = 0; i < forksCount; i++)
            philosophers.add(new Philosopher(i));

        philosophers.forEach(philosopher -> new Thread(philosopher).start());

        Thread.sleep(2000);
        String fileName = "ARBITERlogs" + forksCount + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < forksCount; i++) {
            String data = String.format("%.2f\n", philosophers.get(i).getAverageTime());
            writer.write(data);
        }
        writer.close();
    }
}
 