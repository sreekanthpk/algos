package com.practice.algo.generic;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
    Queue<T> queue = new LinkedList<>();
    int size;
    public MyBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T t) throws InterruptedException {
        synchronized (this) {
            if(queue.size() == size) {
                System.out.println("waiting2");
                wait();
            }

            queue.offer(t);
            if(queue.size() == 1) {
                notifyAll();
                System.out.println("notified2");
            }
        }
    }

    public T take() throws InterruptedException {
        synchronized (this) {
            if(queue.isEmpty()) { wait();
                System.out.println("waiting1");}

            T t = queue.poll();
            if(queue.size() == size-1){
                notifyAll();
                System.out.println("notified1");
            }
            return t;
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue<String> q = new MyBlockingQueue<>(10);

        new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    System.out.println(q.take());
                }
            } catch (InterruptedException e) {}
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 50; i++) {
                    q.offer("" + i);
                }
            } catch (InterruptedException e) {}
        }).start();

    }
}
