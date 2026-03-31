package com.practice.algo.generic;

import java.util.PriorityQueue;

/**
 * Minimum Cost to Connect Ropes:
 * Given ropes of different lengths,
 * connect them into one rope with
 * minimum cost (cost = sum of lengths of two ropes being connected).
 */
public class MinCostToConnectRopes {

    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all ropes to min heap
        for (int rope : ropes) {
            minHeap.add(rope);
        }

        int totalCost = 0;

        // Keep connecting two smallest ropes until one rope remains
        while (minHeap.size() > 1) {
            int first = minHeap.poll();  // smallest
            int second = minHeap.poll(); // second smallest

            int cost = first + second;
            totalCost += cost;

            // Add the combined rope back to the heap
            minHeap.add(cost);
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Minimum cost to connect ropes: " + minCost(ropes));
    }
}
