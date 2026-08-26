package com.practice.algo.multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {

    static ReentrantLock lock = new ReentrantLock();

   static  Condition pingCondition = lock.newCondition();
   static  Condition pongCondition = lock.newCondition();

    static boolean pingTurn = true;

    public static void ping(){
        for(int i=0;i<20;i++) {
            lock.lock();
            try {
                while(!pingTurn)
                    pingCondition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Ping");
            pingTurn = false;
            pongCondition.signal();
            lock.unlock();

        }
    }

    public static void pong() {
        for(int i=0;i<20;i++) {
            lock.lock();
            try {
                while(pingTurn)
                    pongCondition.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Pong");
            pingTurn = true;
            pingCondition.signal();
            lock.unlock();
        }
    }

    public static void main(String... args){
        Thread t1 = new Thread(PingPong::ping);

        Thread t2 = new Thread(PingPong::pong);

        t1.start();
        t2.start();
    }
}
