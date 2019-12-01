package task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final int forksCount = 17;
    static List<Lock> forks = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < forksCount; i++)
            forks.add(new ReentrantLock());

        List<Thread> philosophers = new ArrayList<>();
        for (int i = 0; i < forksCount; i++) {
            Philosopher p = new Philosopher(i);
            Thread t = new Thread(p);
            philosophers.add(t);
        }

        philosophers.forEach(Thread::start);

        for (Thread philosopher : philosophers) {
            philosopher.join();
        }
    }
}
 