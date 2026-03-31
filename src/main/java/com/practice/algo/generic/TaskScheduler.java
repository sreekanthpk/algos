package com.practice.algo.generic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskScheduler {

    PriorityBlockingQueue<Task> tasks = new PriorityBlockingQueue<>( 1000, (t1,t2) -> Long.compare(t1.executionTime, t2.executionTime) );

    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final AtomicBoolean running = new AtomicBoolean(true);
    public TaskScheduler(){

        Thread consumer = new Thread(this::processTasks);
        consumer.setDaemon(true);
        consumer.start();
    }

    private void processTasks() {
        try {
            while (running.get()) {
                Task task = tasks.take(); // blocks until a task is available
                long delay = task.executionTime - System.currentTimeMillis();

                if (delay > 0) {
                    Thread.sleep(delay);
                }

                executor.execute(task);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void scheduleTask( Task task ) {
        tasks.add(task);
    }

    public static void main(String... args) throws InterruptedException {
        TaskScheduler s  = new TaskScheduler();
        Task t2 = new Task(System.currentTimeMillis()+10000);
        Task t3 = new Task(System.currentTimeMillis()+15000);
        s.scheduleTask(t2);
        s.scheduleTask(t3);
        Thread.sleep(500000);
    }

}

class Task implements Runnable {

    long executionTime;

    public Task(long executionTime) {
        this.executionTime = executionTime;
    }

    public void execute() {
        System.out.println("Task is running");
    }

    @Override
    public void run() {
        this.execute();
    }


}
