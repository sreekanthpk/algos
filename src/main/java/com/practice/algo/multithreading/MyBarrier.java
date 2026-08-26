package com.practice.algo.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBarrier {

    private final int parties;
    private int waiting = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public MyBarrier(int parties) {
        this.parties = parties;
    }

    public void await() throws InterruptedException {
        lock.lock();

        try {
            waiting++;

            if (waiting == parties) {
                waiting = 0;
                condition.signalAll();
                return;
            }

            while (waiting != 0) {
                condition.await();
            }

        } finally {
            lock.unlock();
        }
    }
}