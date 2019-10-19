package task1;

public class DecrementOperation implements Operation {
    @Override
    public void runOperation(Counter counter) {
        counter.getBinarySemaphore().takeSemaphore();
        counter.decreaseBy1();
        counter.getBinarySemaphore().releaseSemaphore();
    }
}
