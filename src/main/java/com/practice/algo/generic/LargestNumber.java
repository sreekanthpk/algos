package com.practice.algo.generic;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 */
import java.util.*;

class Solution {
    public String largestNumber(int[] nums) {

        String[] arr = new String[nums.length];

        for(int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));

        // Edge case: if highest number is 0
        if(arr[0].equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for(String s : arr) {
            result.append(s);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] tests = {
                {10,2},
                {3,30,34,5,9},
                {3,30},
                {12,121},
                {0,0},
                {0,1},
                {432,43243}
        };

        for(int[] test : tests) {
            System.out.println(sol.largestNumber(test));
        }
    }
}


