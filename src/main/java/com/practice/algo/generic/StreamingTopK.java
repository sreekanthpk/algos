package com.practice.algo.generic;

import java.util.PriorityQueue;

public class StreamingTopK {

    private final int K;
    private final PriorityQueue<Integer> minHeap;

    public StreamingTopK(int K) {
        this.K = K;
        this.minHeap = new PriorityQueue<>(K); // min-heap
    }

    // Feed a new price into the stream
    public void addPrice(int price) {
        if (minHeap.size() < K) {
            minHeap.offer(price); // heap not full
        } else if (price > minHeap.peek()) {
            minHeap.poll();       // remove smallest
            minHeap.offer(price); // add new bigger price
        }
    }

    // Get current top K prices (unsorted)
    public int[] getTopK() {
        int[] result = new int[minHeap.size()];
        int i = 0;
        for (int price : minHeap) {
            result[i++] = price;
        }
        return result;
    }

    // Optional: get sorted top K descending
    public int[] getTopKSorted() {
        int[] topK = getTopK();
        java.util.Arrays.sort(topK);
        return topK;
    }

    public static void main(String[] args) {
        StreamingTopK topKTracker = new StreamingTopK(3);

        // Simulate a streaming feed of prices
        int[] pricesStream = {100, 50, 200, 150, 120, 90, 250};
        for (int price : pricesStream) {
            topKTracker.addPrice(price);
        }

        int[] topK = topKTracker.getTopKSorted();
        System.out.println("Top 3 prices in stream:");
        for (int p : topK) {
            System.out.println(p);
        }
    }
}
