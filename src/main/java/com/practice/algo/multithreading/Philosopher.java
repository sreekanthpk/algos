package com.practice.algo.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

    private final Lock left;
    private final Lock right;

    Philosopher(Lock left, Lock right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            Lock first = left;
            Lock second = right;

            // Global lock ordering
            if (System.identityHashCode(first)
                    > System.identityHashCode(second)) {
                first = right;
                second = left;
            }

            first.lock();

            try {
                second.lock();

                try {
                    eat();
                } finally {
                    second.unlock();
                }

            } finally {
                first.unlock();
            }
        }
    }

    private void eat() {
        System.out.println(
                Thread.currentThread().getName() + " eating");
    }
}