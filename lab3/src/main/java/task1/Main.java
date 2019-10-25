package task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Printer> printers = new ArrayList<>();

    public static void main(String[] args) {
        int threadCount = 10;
        int printerCount = 3;

        for (int i = 0; i < printerCount; i++) {
            Printer printer = new Printer(i);
            printers.add(printer);
        }

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < threadCount; i++){
            threads.add(new Thread(new PrintingTask()));
        }

        for(int i = 0; i < threadCount; i++){
            threads.get(i).start();
        }
    }

    static void print(int printerNumber){
        printers.get(printerNumber).print();
    }
}
