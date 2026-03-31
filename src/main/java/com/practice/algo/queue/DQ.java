package com.practice.algo.queue;

import java.lang.reflect.Array;

/**
 * Double ended queue
 * @param <E>
 */

public class DQ<E> implements DQI<E>{

    private final  E[] ele;
    private int rear;
    private int front;
    private final int capacity;

    public DQ(Class<E> type, int capacity){
        front = -1;
        rear = 0;
        ele = (E[]) Array.newInstance(type, capacity);
        this.capacity = capacity;
    }

    @Override
    public void insertRear(E e) {
        if (isFull())
        {
            throw new RuntimeException("Queue is full");
        }

        if (front == -1)
        {
            front = 0;
            rear = 0;
        }

        else if (rear == capacity-1)
            rear = 0;

        else
            rear = rear+1;

        ele[rear] = e ;

    }

    @Override
    public void insertFront(E e) {
        if (isFull())
        {
           throw new RuntimeException("Queue is full");
        }

        if (front == -1)
        {
            front = 0;
            rear = 0;
        }

        else if (front == 0)
            front = capacity - 1 ;

        else
            front = front-1;

        ele[front] = e ;
    }

    @Override
    public E removeRear() {
        if (isEmpty())
        {
            new RuntimeException("Queue is empty");
        }

        E e  = ele[rear];

        if (front == rear)
        {
            front = -1;
            rear = -1;
        }
        else if (rear == 0)
            rear = capacity-1;
        else
            rear = rear-1;

        return e;
    }

    @Override
    public E removeFront() {
        if (isEmpty())
        {
           throw new RuntimeException("Queue is empty");
        }

        E val = ele [front];

        if (front == rear)
        {
            front = -1;
            rear = -1;
        }
        else

        if (front == capacity -1)
            front = 0;
        else
            front = front+1;

        return val;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isFull() {
        return ((front == 0 && rear == capacity-1)||
                front == rear+1);
    }

    @Override
    public boolean isEmpty() {
        return (front == -1);

    }

    @Override
    public E peerRear() {
        if (isEmpty())
        {
            new RuntimeException("Queue is empty");
        }

        return ele[rear];
    }

    @Override
    public E peekFront() {
        if (isEmpty())
        {
            new RuntimeException("Queue is empty");
        }

        return ele[front];
    }
}
