package task2;

class Client {
    Runnable takeBucket(Shop shop) {
        return () -> {
            shop.takeBucket();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shop.loseBucket();
        };
    }
}
