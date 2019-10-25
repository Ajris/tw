package task1;

public class PrintingTask implements Runnable {
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int printerNumber = PrinterMonitor.takeMonitor();
            Main.print(printerNumber);
            PrinterMonitor.leaveMonitor(printerNumber);
        }
    }
}
