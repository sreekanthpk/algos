package com.practice.algo.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * It counts how many contiguous subarrays have
 * sum exactly = k
 * maximum element ≤ M
 *
 * The array can contain negative numbers.
 */
public class SubArrayWithNegativeNumbers {

    public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
        long count = 0;
        long currentSum = 0;
        // Maps prefix sum -> frequency
        Map<Long, Integer> prefixSums = new HashMap<>();

        // Base case: a prefix sum of 0 has been seen once
        prefixSums.put(0L, 1);

        for (int n : nums) {
            // If an element is > M, any subarray containing it is invalid.
            // We reset everything as if starting a new array.
            if (n > M) {
                currentSum = 0;
                prefixSums.clear();
                prefixSums.put(0L, 1);
                continue;
            }

            currentSum += n;

            // Check if there is a prefix sum such that currentSum - oldSum = k
            long target = currentSum - k;
            if (prefixSums.containsKey(target)) {
                count += prefixSums.get(target);
            }

            // Update the frequency of the current prefix sum
            prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }



        public static void main(String[] args) {
            // Test Case 1: From the problem description
            List<Integer> nums1 = Arrays.asList(2, -1, 2, 1, -2, 3);
            long k1 = 3;
            long M1 = 2;
            System.out.println("Test Case 1 Output: " + countSubarraysWithSumAndMaxAtMost(nums1, k1, M1));
            // Expected: 2 (Subarrays: [2, -1, 2] and [2, 1])

            // Test Case 2: Array where all elements satisfy M, but only some satisfy k
            List<Integer> nums2 = Arrays.asList(1, 2, 3, 4, 5);
            long k2 = 3;
            long M2 = 5;
            System.out.println("Test Case 2 Output: " + countSubarraysWithSumAndMaxAtMost(nums2, k2, M2));
            // Expected: 2 (Subarrays: [1, 2] and [3])
        }

}
