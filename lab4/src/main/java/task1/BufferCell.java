package task1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BufferCell {
    private int prevId = -1;
    private final Lock lock = new ReentrantLock();
    private final Condition waitTime = lock.newCondition();
    private int value = 0;

    int getResource(int id) throws InterruptedException {
        lock.lock();
        while (prevId + 1 != id) {
            waitTime.await();
        }
        return value;
    }

    void setValue(int newValue){
        value = newValue;
    }

    void releaseResource(int id) {
        this.prevId = id;
        waitTime.signalAll();
        lock.unlock();
    }
}
