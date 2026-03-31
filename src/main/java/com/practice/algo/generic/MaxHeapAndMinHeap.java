package com.practice.algo.generic;

import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeapAndMinHeap {

    public static void main(String[] args) {
        var g = new PriorityQueue<>();
        g.add(3);
        g.add(2);
        g.add(1);


        System.out.println(g.poll());
        System.out.println(g.poll());

        var max = new PriorityQueue<>(Collections.reverseOrder());

        max.add(2);
        max.add(3);
        max.add(1);
        System.out.println(max.poll());
        System.out.println(max.poll());


    }
}
