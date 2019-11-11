package task2.naive;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private final Lock lock = new ReentrantLock();
    private final Condition waitBuffer = lock.newCondition();
    private String buffer = "";
    private int emptySize;

    Buffer(int bufferSize) {
        this.emptySize = bufferSize;
    }

    void put(String data) throws InterruptedException {
        int size = data.length();
        lock.lock();
        while (size > emptySize) {
            waitBuffer.await();
        }
        buffer = buffer + data;
        emptySize -= data.length();
        waitBuffer.signalAll();
        lock.unlock();
    }

    String get(int size) throws InterruptedException {
        lock.lock();
        while (size > buffer.length()) {
            waitBuffer.await();
        }
        String result = buffer.substring(0, size);
        buffer = buffer.substring(size);
        emptySize += size;
        waitBuffer.signalAll();
        lock.unlock();
        return result;
    }
}