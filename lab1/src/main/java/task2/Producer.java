package task2;

class Producer implements Runnable {
    private Buffer buffer;
    private int count;

    Producer(Buffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            buffer.put("message " + i);
            System.out.println("PRODUCING: " + buffer.getString());
        }
    }
}
