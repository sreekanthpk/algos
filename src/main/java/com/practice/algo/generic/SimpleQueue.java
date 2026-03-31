package com.practice.algo.generic;

public class SimpleQueue<T> {
    int head  = 0;
    int tail = 0;
    int size = 0;
    T[] queue;
    public SimpleQueue(int size) {
        this.size = size;
        queue = (T[]) new Object[size];
    }

    public boolean offer(T item) {
        int newTail = (tail + 1) % size;
        if(newTail == head) {
            return false;
        }
        queue[tail] = item;
        tail = newTail;
        return true;
    }

    public T peek(){
        return queue[head];
    }

    public T poll(){
        if (head == tail) {
            return null; // queue empty
        }
        T item = queue[head];
        queue[head] = null; // avoid memory leak
        head = (head + 1) % size;
        return item;
    }

    public static void main(String... args){
        SimpleQueue queue = new SimpleQueue(10);
        queue.offer(1);
        queue.offer(2);

        System.out.println(queue.poll()); // 1
        System.out.println(queue.poll()); // 2
        System.out.println(queue.poll()); // null
    }
}
