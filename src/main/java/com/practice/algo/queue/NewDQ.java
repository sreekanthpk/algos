package com.practice.algo.queue;

import java.lang.reflect.Array;

public class NewDQ<E> {

    private E[] elements;
    private int front = -1;
    private int rear = 0;
    private int capacity;

    public NewDQ(Class type, int capacity){
        elements = (E[]) Array.newInstance(type, capacity);
        this.capacity = capacity;
    }

    public void insertFront(E e){
        if(isFull()) throw new RuntimeException("DQ is full");

        if(front == -1){
            front =0 ;
            rear =0;
        } else if(front == 0) {
            front = capacity - 1;
        } else {
            front = front - 1;
        }
        elements[front] = e;
    }

    public void insertRear(E e){
        if(isFull()) throw new RuntimeException("DQ is full");
        if(rear == 0){
            rear = 0;
            front = 0;
        } else if(rear == capacity- 1){
            rear =0;
        } else {
            rear = rear+1;
        }
        elements[rear] = e;
    }

    public E removeFront() {
        if (isEmpty()) throw new RuntimeException("DQ is empty");
        E val = elements [front];
        if (front == rear)
        {
            front = -1;
            rear = -1;
        } else if (front == capacity - 1)
            front = 0;
        else
            front = front + 1;

        return val;
    }

    public E removeRear(){
        if (isEmpty()) throw new RuntimeException("DQ is empty");
        E val = elements [rear];
        if (front == rear)
        {
            front = -1;
            rear = -1;
        } else if (rear == 0)
            rear = capacity-1;
        else
            rear = rear-1;
        return val;
    }

    public boolean isFull(){
        return ((front == 0 && rear == capacity-1)||
                front == rear+1);
    }

    public boolean isEmpty(){
        return (front == -1);
    }


}
