package task2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Random random = new Random();

        List<BigInteger> numbers = new ArrayList<>();

        for(int i = 0; i < 10_000_000; i++) {
            numbers.add(new BigInteger(String.valueOf(random.nextInt())));
        }

        RecursiveSum task = new RecursiveSum(numbers, 0, numbers.size(), 1000);
        BigInteger sum = forkJoinPool.invoke(task);
        System.out.println(sum);
    }
}
