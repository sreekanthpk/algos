package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    BlockingQueue<Runnable> queue =  new LinkedBlockingQueue<>();
    List<Thread> threads = new ArrayList<>();
    boolean shutdown = false;
    public CustomThreadPool(int numberThread){
        for(int i=0;i<numberThread;i++){
            threads.add(new Thread(()->{
                while(!shutdown) {
                    try {
                        queue.take().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }

        for(Thread thread:threads){
                thread.start();
        }
    }

    public void shubmitTask(Runnable task){
        queue.add(task);
    }

    public void shutdown(){
        this.shutdown = true;
    }

}
