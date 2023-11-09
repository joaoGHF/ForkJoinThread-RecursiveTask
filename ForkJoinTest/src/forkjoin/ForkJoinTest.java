package forkjoin;

import java.util.concurrent.*;
import java.util.function.*;

public class ForkJoinTest {
    public static void main(String[] args) throws Exception {
        final int SIZE = 10000000; // 10 billion
        double[] numbers = new double[SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Math.random();
        }
        // Counter counter = 
        
        
        
        
        // TODO: continue in main method

    }
}

class Counter extends RecursiveTask<Integer> {
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from, to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;


    }

    @Override
    protected Integer compute() {
        if (to - from < THRESHOLD) {
            int count = 0;
            for (int i = from; i < to; i++) {
                if (filter.test(values[i])) {
                    count++;
                }
            }
            return count;
        } else {
            int mid = (from + to) / 2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to, filter);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }

}