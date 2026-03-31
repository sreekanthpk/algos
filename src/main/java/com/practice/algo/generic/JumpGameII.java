package com.practice.algo.generic;

/**
 * Jump Game II: Given an array where each
 * element is your max jump distance,
 * find the minimum jumps to reach the end.
 */
public class JumpGameII {

    public static int jump(int[] nums) {
        int jumps = 0;      // number of jumps
        int currentEnd = 0; // end of current jump
        int farthest = 0;   // farthest reachable index in next jump

        for (int i = 0; i < nums.length - 1; i++) {
            // update the farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);

            // when we reach the end of current jump, make the jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4};
        System.out.println("Test 1 Expected: 2");
        System.out.println("Output: " + jump(nums1));

        int[] nums2 = {2,3,0,1,4};
        System.out.println("Test 2 Expected: 2");
        System.out.println("Output: " + jump(nums2));

        int[] nums3 = {1,1,1,1};
        System.out.println("Test 3 Expected: 3");
        System.out.println("Output: " + jump(nums3));

        int[] nums4 = {5,9,3,2,1,0,2,3,3,1,0,0};
        System.out.println("Test 4 Expected: 2");
        System.out.println("Output: " + jump(nums4));
    }
}
