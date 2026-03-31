package com.practice.algo.java;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ArraySum extends RecursiveTask<Long> {
    private int[] array;
    private int start;
    private int end;
    private static final int THRESHOLD = 1000;

    public ArraySum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // Compute directly
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Split task
            int mid = (start + end) / 2;
            ArraySum leftTask = new ArraySum(array, start, mid);
            ArraySum rightTask = new ArraySum(array, mid, end);
            leftTask.fork();
            long rightResult = rightTask.compute(); // Right task computes directly (no need to fork)
            long leftResult = leftTask.join(); // Wait for the left task
            return leftResult + rightResult; // Combine results
        }
    }

    public static void main(String[] args) {
        int[] array = new int[10_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1; // Simple case: all elements are 1
        }
    }
}