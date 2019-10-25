package task2;

class Client {
    Runnable takeBucket(Shop shop) {
        return () -> {
            Bucket currentBucket = shop.takeBucket();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shop.loseBucket(currentBucket);
        };
    }
}
