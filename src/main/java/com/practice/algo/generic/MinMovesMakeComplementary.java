package com.practice.algo.generic;

import java.util.*;

public class MinMovesMakeComplementary {

    public static int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] change = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = nums[i], b = nums[n - 1 - i];
            int low = Math.min(a, b) + 1;
            int high = Math.max(a, b) + limit;

            // Default 2 moves for all sums
            change[2] += 2;
            change[low] -= 1;       // 1 move range start
            change[a + b] -= 1;     // exact sum 0 move
            change[a + b + 1] += 1; // after exact sum -> restore
            change[high + 1] += 1;  // after high -> restore
        }

        int res = Integer.MAX_VALUE;
        int curr = 0;

        for (int i = 2; i <= 2 * limit; i++) {
            curr += change[i];
            res = Math.min(res, curr);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,4,3};
        int limit1 = 4;
        System.out.println("Test 1 Expected: 1");
        System.out.println("Output: " + minMoves(nums1, limit1));

        int[] nums2 = {1,2,2,1};
        int limit2 = 2;
        System.out.println("Test 2 Expected: 2");
        System.out.println("Output: " + minMoves(nums2, limit2));

        int[] nums3 = {1,2,1,2};
        int limit3 = 2;
        System.out.println("Test 3 Expected: 0");
        System.out.println("Output: " + minMoves(nums3, limit3));
    }
}
