package com.practice.algo.java;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numberOfThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> System.out.println("All threads reached the barrier!"));

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadId = i;
            executor.submit(() -> {
                System.out.println("Thread " + threadId + " is doing some work.");
                try {
                    Thread.sleep(1000); // Simulate work
                    barrier.await(); // Wait at the barrier
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Thread " + threadId + " is continuing after the barrier.");
            });
        }

        executor.shutdown();
    }
}