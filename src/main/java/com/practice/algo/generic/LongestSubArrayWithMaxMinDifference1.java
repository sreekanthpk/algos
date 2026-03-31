package com.practice.algo.generic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
longest subarray where the difference between the max and min is ≤ limit
 */
public class LongestSubArrayWithMaxMinDifference1 {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        int result = 0;

        for(int right=0;right<nums.length;right++){
            while(!maxDeque.isEmpty() && nums[maxDeque.peekLast()]<=nums[right]){
                maxDeque.pollLast();
            }
            while(!minDeque.isEmpty() && nums[minDeque.peekLast()]>=nums[right]){
                minDeque.pollLast();
            }

            maxDeque.addLast(right);
            minDeque.addLast(right);

            while(nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit){

                if(maxDeque.peekFirst() == left){
                    maxDeque.pollFirst();
                }
                if(minDeque.peekFirst() == left){
                    minDeque.pollFirst();
                }
                left++;
            }
            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        LongestSubArrayWithMaxMinDifference1 solver = new LongestSubArrayWithMaxMinDifference1();

        int[][] testArrays = {
                {8, 2, 4, 7},
                {1, 2, 3, 4},
                {5},
                {10, 1, 12, 3},
                {4, 4, 4, 4, 4},
                {1, 3, 6, 2, 4, 5}
        };

        int[] limits = {4, 10, 0, 0, 0, 3};

        for (int i = 0; i < testArrays.length; i++) {
            int result = solver.longestSubarray(testArrays[i], limits[i]);
            System.out.println("Test Case " + (i + 1) + ": " + result);
        }
    }
}
