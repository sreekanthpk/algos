package com.practice.algo.queue;

public interface DQI<E> {
    void insertRear(E e);
    void insertFront(E e);
    E removeRear();
    E removeFront();
    int size();
    boolean isFull();
    boolean isEmpty();
    E peerRear();
    E peekFront();
}
