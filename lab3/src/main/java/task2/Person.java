package task2;

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
                WaiterMonitor.takeTable(pair);
                System.out.println(name + " is eating");
                WaiterMonitor.leaveTable();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
