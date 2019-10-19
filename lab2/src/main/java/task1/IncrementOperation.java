package task1;

public class IncrementOperation implements Operation {
    @Override
    public void runOperation(Counter counter) {
        counter.getBinarySemaphore().takeSemaphore();
        counter.increaseBy1();
        counter.getBinarySemaphore().releaseSemaphore();
    }
}
