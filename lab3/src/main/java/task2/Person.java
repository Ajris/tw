package task2;

import java.util.Random;

public class Person implements Runnable {
    private int pair;
    private String name;

    Person(int pair, String name) {
        this.pair = pair;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(name + " is trying to sit");
                WaiterMonitor.takeTable(pair);
                System.out.println(name + " is eating");
                try {
                    Thread.sleep(100 + new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(name + " is leaving");
                WaiterMonitor.leaveTable();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
