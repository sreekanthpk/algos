package com.practice.algo.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExceptionExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            int numerator = 10;
            int denominator = 0; // This will cause an exception
            if (denominator == 0) {
                throw new ArithmeticException("Division by zero!");
            }
            return numerator / denominator;
        };

        Future<Integer> future = executor.submit(task);

        try {
            Integer result = future.get(); // This will throw ExecutionException
            System.out.println("Result: " + result);
        } catch (ExecutionException e) {
            System.err.println("Task failed with exception: " + e.getCause());
        } catch (InterruptedException e) {
            System.err.println("Task was interrupted");
        } finally {
            executor.shutdown();
        }
    }
}