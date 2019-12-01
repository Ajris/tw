package task3;

import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable {
    private int id;
    private final int WAIT_TIME = 100;
    private Lock leftFork;
    private Lock rightFork;
    private long forkAcquiringCount = 0;
    private long forkAcquiringTime = 0;

    public Philosopher(int id) {
        this.id = id;
        this.leftFork = Main.forks.get(id);
        this.rightFork = Main.forks.get((id + 1) % Main.forks.size());
    }

    public float getAverageTime() {
        return (float) forkAcquiringTime / forkAcquiringCount;
    }

    @Override
    public void run() {
        long lastEatingTime = System.currentTimeMillis();
        while (forkAcquiringCount < 10) {
            if (leftFork.tryLock()) {
                if (rightFork.tryLock()) {
                    System.out.printf("Zaczynam jesc: %d\n", id);
                    long curr = System.currentTimeMillis();
                    forkAcquiringTime += curr - lastEatingTime;
                    lastEatingTime = curr;
                    forkAcquiringCount++;
                    System.out.printf("Koncze jesc: %d\n", id);
                    rightFork.unlock();
                    leftFork.unlock();
                } else {
                    System.out.printf("NIE MA PRAWEGO: %d\n", id);
                }
            } else {
                System.out.printf("NIE MA LEWEGO: %d\n", id);
            }
            waitForFork();
        }
        try {
            Thread.sleep(3000); // WAIT FOR OTHERS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%.2f\n", getAverageTime());
    }

    private void waitForFork() {
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
