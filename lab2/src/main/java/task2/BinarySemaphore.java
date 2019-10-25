package task2;

class BinarySemaphore {
    private BinarySemaphoreState state = BinarySemaphoreState.FREE;

    synchronized void takeSemaphore() {
        while (state.equals(BinarySemaphoreState.TAKEN)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = BinarySemaphoreState.TAKEN;
    }

    synchronized void releaseSemaphore() {
        state = BinarySemaphoreState.FREE;
        notifyAll();
    }
}
