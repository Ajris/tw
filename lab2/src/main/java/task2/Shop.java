package task2;

class Shop {
    private volatile int bucket;

    Shop(int bucket) {
        this.bucket = bucket;
    }

    synchronized void takeBucket() {
        while (bucket == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is taking bucket " + bucket);
        bucket--;
    }

    synchronized void loseBucket() {
        bucket++;
        notifyAll();
    }
}
