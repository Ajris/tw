package task2;

class Consumer implements Runnable {
    private Buffer buffer;
    private int count;

    Consumer(Buffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            String message = buffer.take();
            System.out.println("CONSUMING: " + message);
        }
    }
}
