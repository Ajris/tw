package task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        int operationNumber = 1000000;
        Thread incrementThread = new Thread(operation(counter, operationNumber, new IncrementOperation()));
        Thread decrementThread = new Thread(operation(counter, operationNumber, new DecrementOperation()));

        long start = System.currentTimeMillis();
        incrementThread.start();
        decrementThread.start();
        incrementThread.join();
        decrementThread.join();
        long end = System.currentTimeMillis();

        System.out.println(counter.getNum());
        System.out.println("TIME: " + (end - start));
    }

    private static Runnable operation(Counter counter, int operationNumber, Operation operation) {
        return () -> {
            for(int i = 0; i < operationNumber; i++){
                operation.runOperation(counter);
            }
        };
    }
}
