package task1;

import java.util.Random;

public class PrintingTask implements Runnable {
    @Override
    public void run() {
        while(true){
            int printerNumber = PrinterMonitor.takeMonitor();
            try {
                Thread.sleep(100 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.print(printerNumber);
            PrinterMonitor.leaveMonitor(printerNumber);
        }
    }
}
