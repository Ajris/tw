package different;

import static different.Main.*;

public class Philosopher implements Runnable {
    private int philosopherId = 0;
    private int numberOfDinners = 10;


    public Philosopher(int philosopherId) {
        this.philosopherId = philosopherId;
    }

    @Override
    public void run() {
        while (numberOfDinners > 0) {
            while (true) {
                for (int i = 1; i < 5; i++) {
                    claiming[philosopherId] = i;
                    marking[i] = philosopherId;
                    while (((claiming[(philosopherId + 1) % 5]) >= 5 || (claiming[(philosopherId + 2) % 5]) >= 5 || (claiming[(philosopherId + 3) % 5]) >= 5 || (claiming[(philosopherId + 4) % 5]) >= 5) && (marking[i] == philosopherId))
                        ;
                }
                claiming[philosopherId] = 5;
                if (claiming[(philosopherId - 1) % 5] < 0) {
                    blockedByLeft[philosopherId] = true;
                } else if (claiming[(philosopherId + 1) % 5] < 0) {
                    blockedByRight[philosopherId] = true;
                } else if (!blockedByRight[(philosopherId - 1) % 5] && !blockedByLeft[(philosopherId + 1) % 5]) {
                    break;
                }
            }
            claiming[philosopherId] = -1;
            blockedByLeft[philosopherId] = false;
            blockedByRight[philosopherId] = false;
            System.out.println("EATING " + philosopherId);
            numberOfDinners--;
            claiming[philosopherId] = 0;
        }
    }
}
