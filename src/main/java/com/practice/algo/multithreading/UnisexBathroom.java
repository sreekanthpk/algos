package com.practice.algo.multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UnisexBathroom {

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();

    private int men = 0;
    private int women = 0;

    public void enterMan() throws InterruptedException {
        lock.lock();
        try {
            while (women > 0) {
                condition.await();
            }
            men++;
        } finally {
            lock.unlock();
        }
    }

    public void leaveMan() {
        lock.lock();
        try {
            men--;

            if (men == 0) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void enterWoman() throws InterruptedException {
        lock.lock();
        try {
            while (men > 0) {
                condition.await();
            }
            women++;
        } finally {
            lock.unlock();
        }
    }

    public void leaveWoman() {
        lock.lock();
        try {
            women--;

            if (women == 0) {
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}