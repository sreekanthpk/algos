package com.practice.algo.java;

import java.util.concurrent.Semaphore;

class SharedResource {
    private Semaphore semaphore = new Semaphore(3); // 3 permits

    public void accessResource(String threadName) {
        try {
            System.out.println(threadName + " trying to acquire permit...");
            semaphore.acquire(); // acquire permit

            System.out.println(threadName + " acquired permit!");
            Thread.sleep(2000); // simulate work

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(threadName + " releasing permit.");
            semaphore.release(); // release permit
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        for (int i = 1; i <= 6; i++) {
            String name = "Thread-" + i;
            new Thread(() -> resource.accessResource(name)).start();
        }
    }
}