package com.practice.algo.java;


import java.util.concurrent.atomic.AtomicStampedReference;

class ABAExample {
    public static void main(String[] args) {
        // Initial value = 100, stamp = 0
        int initialRef = 100;
        int initialStamp = 0;
        AtomicStampedReference<Integer> atomicRef =
                new AtomicStampedReference<>(initialRef, initialStamp);

        // Thread 1 reads value and stamp
        int[] stampHolder = new int[1];
        Integer value = atomicRef.get(stampHolder);
        int stamp = stampHolder[0];

        System.out.println("Thread 1 sees value = " + value + ", stamp = " + stamp);

        // Thread 2 changes value from 100 -> 200 -> 100
        int[] stampHolder2 = new int[1];
        atomicRef.get(stampHolder2);
        int stamp2 = stampHolder2[0];
        boolean t2cas1 = atomicRef.compareAndSet(100, 200, stamp2, stamp2 + 1);
        System.out.println("Thread 2 CAS 100->200 success: " + t2cas1);

        atomicRef.get(stampHolder2);
        int stamp3 = stampHolder2[0];
        boolean t2cas2 = atomicRef.compareAndSet(200, 100, stamp3, stamp3 + 1);
        System.out.println("Thread 2 CAS 200->100 success: " + t2cas2);

        // Thread 1 tries CAS using old stamp
        boolean t1cas = atomicRef.compareAndSet(100, 300, stamp, stamp + 1);
        System.out.println("Thread 1 CAS 100->300 success: " + t1cas);

        System.out.println("Final value = " + atomicRef.getReference() +
                ", stamp = " + atomicRef.getStamp());
    }
}