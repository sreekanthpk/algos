package com.practice.algo.multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeferredCallback {
    private final ScheduledExecutorService executor =
            Executors.newSingleThreadScheduledExecutor();

    private final AtomicBoolean executed = new AtomicBoolean(false);

    public void register(Runnable callback, long delayMs) {
        executor.schedule(() -> {
            if (executed.compareAndSet(false, true)) {
                callback.run();
            }
        }, delayMs, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        executor.shutdown();
    }



    public static void main(String[] args) {
        DeferredCallback callback = new DeferredCallback();

        callback.register(
                () -> System.out.println("Callback executed"),
                1000
        );
    }
}