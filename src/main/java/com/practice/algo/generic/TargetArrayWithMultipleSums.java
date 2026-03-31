package com.practice.algo.generic;

import java.util.*;

/**
 * Construct Target Array with Multiple Sums:
 * You start with an array of 1s. In each step,
 * you replace an element with the sum of all current elements.
 * Can you reach the target?
 */
public class TargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        if (target.length == 1) return target[0] == 1;

        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        for (int t : target) {
            sum += t;
            pq.offer((long) t);
        }

        while (true) {
            long largest = pq.poll();
            long rest = sum - largest;

            if (largest == 1 || rest == 1) return true;
            if (rest == 0 || largest < rest) return false;

            largest %= rest;
            if (largest == 0) return false;

            sum = rest + largest;
            pq.offer(largest);
        }
    }

    public static void main(String[] args) {
        TargetArrayWithMultipleSums sol = new TargetArrayWithMultipleSums();
        int[] target = {9,3,5};
        System.out.println(sol.isPossible(target)); // Output: true
    }
}
