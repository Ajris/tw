package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class PrinterMonitor {
    private static List<Lock> locks = createPrinterMonitor();

    private PrinterMonitor() {
    }

    private static List<Lock> createPrinterMonitor(){
        int initialCapacity = 3;
        List<Lock> locks = new ArrayList<>(initialCapacity);
        for(int i = 0; i < initialCapacity; i++){
            locks.add(new ReentrantLock());
        }
        return locks;
    }

    static int takeMonitor() {
        int index = 0;
        while(!locks.get(index).tryLock()){
            index++;
            index %= locks.size();
        }
        return index;
    }

    static void leaveMonitor(int printerNumber) {
        locks.get(printerNumber).unlock();
    }
}
