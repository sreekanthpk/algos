package com.practice.algo.generic;

/**
 * Jump Game: Can you jump to the other side
 */
public class JumpGame {

    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false; // Cannot reach this index
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true; // Can reach the last index
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Test Case 1: " + canJump(nums1)); // Expected: true

        // Test Case 2
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Test Case 2: " + canJump(nums2)); // Expected: false

        // Additional Test Case 3
        int[] nums3 = {0};
        System.out.println("Test Case 3: " + canJump(nums3)); // Expected: true (single element)

        // Additional Test Case 4
        int[] nums4 = {2, 0, 0};
        System.out.println("Test Case 4: " + canJump(nums4)); // Expected: true

        // Additional Test Case 5
        int[] nums5 = {1, 0, 1, 0};
        System.out.println("Test Case 5: " + canJump(nums5)); // Expected: false
    }
}