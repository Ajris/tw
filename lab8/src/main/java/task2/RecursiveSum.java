package task2;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class RecursiveSum extends RecursiveTask<BigInteger> {
    private int maxNumbersInOneAddition;
    private List<BigInteger> data;
    private int start;
    private int end;

    public RecursiveSum(List<BigInteger> data, int start, int end, int maxNumbersInOneAddition) {
        this.maxNumbersInOneAddition = maxNumbersInOneAddition;
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        BigInteger sum = new BigInteger("0");
        if((end - start) < maxNumbersInOneAddition) {
            for(int i = start; i < end; i++) {
                sum = sum.add(data.get(i));
            }
        } else {
            int middle = (start + end) / 2;
            RecursiveSum firstHalf = new RecursiveSum(data, start, middle, maxNumbersInOneAddition);
            RecursiveSum secondHalf = new RecursiveSum(data, middle, end, maxNumbersInOneAddition);
            firstHalf.fork();
            secondHalf.fork();

            sum = sum.add(firstHalf.join())
                    .add(secondHalf.join());
        }
        return sum;
    }
}