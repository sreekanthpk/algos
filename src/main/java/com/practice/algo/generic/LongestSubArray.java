package com.practice.algo.generic;

import java.util.*;

public class LongestSubArray {
    public static int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0, result = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain decreasing maxDeque
            while (!maxDeque.isEmpty() && nums[right] > maxDeque.peekLast()) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(nums[right]);

            // Maintain increasing minDeque
            while (!minDeque.isEmpty() && nums[right] < minDeque.peekLast()) {
                minDeque.pollLast();
            }
            minDeque.addLast(nums[right]);

            // Shrink window if condition is violated
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (nums[left] == maxDeque.peekFirst()) {
                    maxDeque.pollFirst();
                }
                if (nums[left] == minDeque.peekFirst()) {
                    minDeque.pollFirst();
                }
                left++;
            }

            // Update result
            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {8, 2, 4, 7};
        int limit = 4;
        System.out.println(longestSubarray(nums, limit)); // Output: 2
    }
}