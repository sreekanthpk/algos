package com.practice.algo.generic;

import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeCounter {
     AtomicInteger counter =new AtomicInteger(0);


     public LockFreeCounter(){}

    public void increment(){
         int oldValue;
         int newValue ;
         do {
             oldValue = counter.get();
             newValue = oldValue+1;

         }while(!counter.compareAndSet(oldValue, newValue));

    }

}
