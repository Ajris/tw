package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class WaiterMonitor {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition isTableTaken = lock.newCondition();
    private static List<Condition> waitingForPair = pairWaiting();
    private static int peopleInTable = 0;

    private static List<Condition> pairWaiting(){
        List<Condition> conditions = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            conditions.add(lock.newCondition());
        }
        return conditions;
    }


    static void takeTable(int pairNumber) throws InterruptedException {
        lock.lock();

        if(lock.getWaitQueueLength(waitingForPair.get(pairNumber)) == 0){
            waitingForPair.get(pairNumber).await();
        } else {
           if(peopleInTable > 0)
               isTableTaken.await();
           peopleInTable = 2;
           waitingForPair.get(pairNumber).signal();
        }

    }

    static void leaveTable() {
        peopleInTable--;
        if(peopleInTable == 0){
            isTableTaken.signal();
        }
        lock.unlock();
    }
}
