package com.practice.algo.generic;

/**
 *
 */
import java.util.concurrent.atomic.AtomicReference;

public class MSQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final Node<E> dummy = new Node<>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public void enqueue(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();

            if (curTail == tail.get()) { // Check for consistency
                if (tailNext != null) {
                    // Queue is in intermediate state!
                    // Help Thread A by moving the tail forward.
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // Quiescent state: try to insert
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // Insertion succeeded; now try to move tail to newNode
                        tail.compareAndSet(curTail, newNode);
                        return;
                    }
                }
            }
        }
    }
}