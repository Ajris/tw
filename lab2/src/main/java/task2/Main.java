package task2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int BUCKETS = 5;
    private static final int CLIENTS = 10;

    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop(BUCKETS);

        List<Thread> clients = new ArrayList<>();

        for(int i = 0; i < CLIENTS; i++){
            clients.add(new Thread(new Client().takeBucket(shop)));
        }

        long start = System.currentTimeMillis();

        for(int i = 0; i < CLIENTS; i++){
            clients.get(i).start();
        }

        for(int i = 0; i < CLIENTS; i++){
            clients.get(i).join();
        }

        long end = System.currentTimeMillis();


        System.out.println("TIME: " + (end - start));
    }
}
