package task2;

import java.util.Optional;

class Buffer {
    private String string;

    synchronized void put(String s) {
        while (Optional.ofNullable(string).isPresent()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.string = new String(s);
        notifyAll();
    }

    synchronized String take() {
        while (Optional.ofNullable(string).isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String ret = new String(string);
        string = null;
        notifyAll();
        return ret;
    }

    String getString() {
        return string;
    }
}
