package different;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean[] blockedByLeft = new boolean[6];
    static boolean[] blockedByRight = new boolean[6];
    static int[] claiming = new int[6];
    static int[] marking = new int[6];


    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 6; i++){
            blockedByLeft[i] = false;
            blockedByRight[i] = false;
            claiming[i] = 0;
        }

        List<Thread> philosophers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
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
