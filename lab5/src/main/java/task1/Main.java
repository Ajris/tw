package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int threads = Runtime.getRuntime().availableProcessors();
        runNThreads(1);
        runNThreads(threads);
        runNThreads(threads * 2);
        System.exit(0);
    }

    private static void runNThreads(int n) {
        runTenTimes(n, 1);
        runTenTimes(n, 10);
        runTenTimes(n, Const.WIDTH * Const.HEIGHT);
    }

    private static void runTenTimes(int n, int taskCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        List<Long> times = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Mandelbrot mandelbrot = new Mandelbrot(taskCount, executorService);
            times.add(mandelbrot.getTime());
        }
        System.out.println("ENDED FOR " + n + " THREADS");
        print(times);
    }

    private static void print(List<Long> times) {
        double average = times.stream()
                .mapToLong(Long::longValue)
                .average().orElseThrow();
        System.out.println("AVG TIME: " + average);
    }
}
