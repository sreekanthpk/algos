package com.practice.algo.generic;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Finding median in a stream of data
 */
public class MedianFinder {

    // Max heap for lower half
    private PriorityQueue<Integer> maxHeap;

    // Min heap for upper half
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    // Insert number
    public void addNum(int num) {
        // Step 1: Add to maxHeap
        maxHeap.offer(num);

        // Step 2: Balance order property
        minHeap.offer(maxHeap.poll());

        // Step 3: Balance size property
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // Get median
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    // Demo
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();

        mf.addNum(1);
        System.out.println(mf.findMedian()); // 1.0

        mf.addNum(2);
        System.out.println(mf.findMedian()); // 1.5

        mf.addNum(3);
        System.out.println(mf.findMedian()); // 2.0

        mf.addNum(4);
        System.out.println(mf.findMedian()); // 2.5
    }
}