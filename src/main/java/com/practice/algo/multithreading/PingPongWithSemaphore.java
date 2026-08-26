package com.practice.algo.multithreading;

import java.util.concurrent.Semaphore;

public class PingPongWithSemaphore {
    static Semaphore pingSem = new Semaphore(1);
    static Semaphore pongSem = new Semaphore(0);

    public static void ping(){
        for(int i=0;i<20;i++){
            try {
                pingSem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Ping");
            pongSem.release();
        }
    }

    public static void pong(){
        for(int i=0;i<20;i++){
            try {
                pongSem.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pong");

            pingSem.release();
        }
    }

    public static void main(String... args){
        Thread t1 = new Thread(PingPongWithSemaphore::ping);
        Thread t2 = new Thread(PingPongWithSemaphore::pong);
        t1.start();
        t2.start();
    }
}
