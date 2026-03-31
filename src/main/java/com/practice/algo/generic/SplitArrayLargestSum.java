package com.practice.algo.generic;

public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int m) {
        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        int left = max, right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canSplit(nums, m, mid)) {
                right = mid; // try smaller max sum
            } else {
                left = mid + 1; // need larger max sum
            }
        }

        return left;
    }

    // Check if we can split nums into <= m subarrays with max sum <= target
    private boolean canSplit(int[] nums, int m, int target) {
        int count = 1; // start with 1 subarray
        int currSum = 0;
        for (int num : nums) {
            if (currSum + num > target) {
                // Start a new subarray
                currSum = num;
                count++;
                if (count > m) return false;
            } else {
                currSum += num;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum sol = new SplitArrayLargestSum();
        int[] nums = {7,2,5,10,8};
        int m = 2;
        System.out.println(sol.splitArray(nums, m)); // Output: 18
    }
}
