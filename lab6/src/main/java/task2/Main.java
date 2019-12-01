package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static final int forksCount = 5;
    static List<Lock> forks = new ArrayList<>();
    static Semaphore waiter = new Semaphore(forksCount - 1);

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
 