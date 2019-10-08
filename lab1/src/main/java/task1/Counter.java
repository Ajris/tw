package task1;

class Counter {
    private int num;

    synchronized void increment(){
        num++;
    }

    synchronized void decrement(){
        num--;
    }

    int getNum() {
        return num;
    }
}
