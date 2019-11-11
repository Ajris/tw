package task2.fair;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final Semaphore semaphorePut = new Semaphore(1, true);
    private final Semaphore semaphoreGet = new Semaphore(1, true);
    private final Lock lock = new ReentrantLock();
    private final Condition waitBuffer = lock.newCondition();
    private String buffer = "";
    private int emptySize;


    Buffer(int bufferSize) {
        this.emptySize = bufferSize;
    }

    void put(String data) throws InterruptedException {
        semaphorePut.acquire();
        int size = data.length();
        lock.lock();
        while (size > emptySize) {
            waitBuffer.await();
        }
        buffer = buffer + data;
        emptySize -= data.length();
        waitBuffer.signalAll();
        lock.unlock();
        semaphorePut.release();
    }

    String get(int size) throws InterruptedException {
        semaphoreGet.acquire();
        lock.lock();
        while (size > buffer.length()) {
            waitBuffer.await();
        }
        String result = buffer.substring(0, size);
        buffer = buffer.substring(size);
        emptySize += size;
        waitBuffer.signalAll();
        lock.unlock();
        semaphoreGet.release();
        return result;
    }
}