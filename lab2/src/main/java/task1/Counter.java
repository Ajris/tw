package task1;

class Counter {
    private int num;
    private BinarySemaphore binarySemaphore = new BinarySemaphore();

    BinarySemaphore getBinarySemaphore() {
        return binarySemaphore;
    }

    int getNum() {
        return num;
    }

    void increaseBy1(){
        num = num + 1;
    }

    void decreaseBy1(){
        num = num - 1;
    }
}
