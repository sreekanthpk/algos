package com.practice.algo.generic;

/**
 * Wiggle Subsequence:
 * Find the length of the
 * longest subsequence where the differences
 * between successive numbers alternate between
 * positive and negative.
 */
public class WiggleSubsequence {

    public static int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;

        int up = 1;   // longest subsequence ending with an up difference
        int down = 1; // longest subsequence ending with a down difference

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1; // current number continues a "down -> up" wiggle
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1; // current number continues an "up -> down" wiggle
            }
            // if equal, do nothing
        }

        return Math.max(up, down);
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println("Length of longest wiggle subsequence: " + wiggleMaxLength(nums));
    }
}
