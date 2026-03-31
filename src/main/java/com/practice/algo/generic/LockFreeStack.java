package com.practice.algo.generic;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> {
    AtomicReference<Node> head = new AtomicReference<>();
    class Node<T>{
        T  data;
        Node<T> next;
    }

    public void push( T t){
        Node<T> node = new Node<T>();
        node.data = t;
        Node<T> oldHead;
        do {
            oldHead = head.get();
            node.next = oldHead;
        }while (!head.compareAndSet(oldHead, node));
    }

    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = head.get();
            if(oldHead == null){ return null;}
            newHead = oldHead.next;
        }while (!head.compareAndSet(oldHead, newHead));
        return oldHead.data;
    }


    public static void main(String... args){

    }
}
