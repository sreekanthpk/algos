package com.practice.algo.java;

import java.util.concurrent.Phaser;

class Worker implements Runnable {
    private Phaser phaser;

    Worker(Phaser phaser) {
        this.phaser = phaser;
        phaser.register(); // register this thread
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " - Phase 1 work");
            Thread.sleep(1000);
            phaser.arriveAndAwaitAdvance(); // wait for others

            System.out.println(Thread.currentThread().getName() + " - Phase 2 work");
            Thread.sleep(1000);
            phaser.arriveAndAwaitAdvance();

            System.out.println(Thread.currentThread().getName() + " - Phase 3 work");
            Thread.sleep(1000);
            phaser.arriveAndAwaitAdvance();

            phaser.arriveAndDeregister(); // done
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); // register main thread

        for (int i = 0; i < 3; i++) {
            new Thread(new Worker(phaser), "Worker-" + i).start();
        }

        phaser.arriveAndDeregister(); // main done
    }
}