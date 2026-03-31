package com.practice.algo.generic;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Finding median in a stream of data
 */
public class MedianFinder1 {

    // Max heap for lower half
    private PriorityQueue<Integer> maxHeap;

    // Min heap for upper half
    private PriorityQueue<Integer> minHeap;

    public MedianFinder1() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    // Insert number
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if(minHeap.size()> maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }

    // Get median
    public double findMedian() {
        if(minHeap.size() == maxHeap.size()){
            return (minHeap.peek() + maxHeap.peek())/2;
        } else{
            return maxHeap.peek();
        }
    }

    // Demo
    public static void main(String[] args) {
        MedianFinder1 mf = new MedianFinder1();

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