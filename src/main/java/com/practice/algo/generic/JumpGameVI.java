package com.practice.algo.generic;

import java.util.*;
/**
Jump Game VI: Similar to above—find
 the max score you can get by jumping at most $k$ steps
**/
public class JumpGameVI {

    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);

        for (int i = 1; i < n; i++) {
            // Remove indices out of window [i-k, i-1]
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Max dp in window
            dp[i] = nums[i] + dp[deque.peekFirst()];

            // Maintain decreasing deque
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        JumpGameVI sol = new JumpGameVI();
        int[] nums = {1,-1,-2,4,-7,3};
        int k = 2;
        System.out.println(sol.maxResult(nums, k)); // Output: 7
    }
}
