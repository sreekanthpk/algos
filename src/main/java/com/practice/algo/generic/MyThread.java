package com.practice.algo.generic;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class MyThread {
    public static void main(String[] args) {
        new Thread(()->{System.out.println("Hello");}).start();
        new Thread(MyRunnable::new).start();
        new Thread(MyCallable::new).start();

        CompletableFuture<Void> future =
                CompletableFuture.runAsync(() -> {
                    System.out.println("Running in another thread");
                });

        future.join();
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello");
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Hello");
        return "Success";
    }
}
