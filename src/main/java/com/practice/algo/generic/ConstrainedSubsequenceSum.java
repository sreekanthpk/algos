package com.practice.algo.generic;

import java.util.*;

/**
 *  Find the maximum sum of a non-empty
 *  subsequence such that for every
 *  two consecutive integers in the
 *  subsequence, their indices $i$ and $j$ satisfy $j - i \le k$.
 */
public class ConstrainedSubsequenceSum {

    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>(); // store indices
        int maxSum = nums[0];
        dp[0] = nums[0];
        deque.offer(0);

        for (int i = 1; i < n; i++) {
            // Remove indices out of range k
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Max dp in the range [i-k, i-1]
            dp[i] = nums[i] + Math.max(0, dp[deque.peekFirst()]);
            maxSum = Math.max(maxSum, dp[i]);

            // Maintain decreasing deque
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        ConstrainedSubsequenceSum sol = new ConstrainedSubsequenceSum();
        int[] nums = {10, 2, -10, 5, 20};
        int k = 2;
        System.out.println(sol.constrainedSubsetSum(nums, k)); // Output: 37
    }
}
