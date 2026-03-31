package com.practice.algo.generic;

import java.util.*;

/**
 * Minimize Deviation
 * in Array: You can perform operations
 * (multiply by 2 or divide by 2) on
 * array elements to minimize the difference
 * between the max and min elements.
 */
public class MinimizeDeviation {

    public static int minimumDeviation(int[] nums) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        int min = Integer.MAX_VALUE;

        // Step 1: Normalize
        for (int num : nums) {
            if (num % 2 == 1) {
                num *= 2;
            }
            maxHeap.offer(num);
            min = Math.min(min, num);
        }

        int deviation = Integer.MAX_VALUE;

        while (true) {
            int max = maxHeap.poll();

            deviation = Math.min(deviation, max - min);

            if (max % 2 == 1) {
                break;  // cannot reduce further
            }

            max /= 2;
            min = Math.min(min, max);
            maxHeap.offer(max);
        }

        return deviation;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(minimumDeviation(nums)); // 1
    }
}
