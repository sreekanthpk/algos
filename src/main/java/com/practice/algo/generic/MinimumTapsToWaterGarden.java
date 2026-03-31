package com.practice.algo.generic;

/**
 * Minimum Number of Taps to Water a Garden:
 * A harder version of Video Stitching.
 * You have taps with different ranges;
 * find the minimum to cover the garden.
 */
public class MinimumTapsToWaterGarden {

    public static int minTaps(int n, int[] ranges) {
        int[] maxReach = new int[n + 1];

        // Convert tap ranges into interval form
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            maxReach[left] = Math.max(maxReach[left], right);
        }

        int taps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i <= n; i++) {

            // If we can't reach this position → impossible
            if (i > farthest) {
                return -1;
            }

            farthest = Math.max(farthest, maxReach[i]);

            // When we reach boundary, open new tap
            if (i == currentEnd) {
                if (i != n) {
                    taps++;
                    currentEnd = farthest;
                }
            }
        }

        return taps;
    }

    public static void main(String[] args) {

        // Test Case 1
        int n1 = 5;
        int[] ranges1 = {3,4,1,1,0,0};
        System.out.println("Test 1 Expected: 1");
        System.out.println("Output: " + minTaps(n1, ranges1));
        System.out.println();

        // Test Case 2
        int n2 = 3;
        int[] ranges2 = {0,0,0,0};
        System.out.println("Test 2 Expected: -1");
        System.out.println("Output: " + minTaps(n2, ranges2));
        System.out.println();

        // Test Case 3
        int n3 = 7;
        int[] ranges3 = {1,2,1,0,2,1,0,1};
        System.out.println("Test 3 Expected: 3");
        System.out.println("Output: " + minTaps(n3, ranges3));
        System.out.println();

        // Test Case 4 (Edge Case: single tap covers all)
        int n4 = 8;
        int[] ranges4 = {4,0,0,0,0,0,0,0,4};
        System.out.println("Test 4 Expected: 2");
        System.out.println("Output: " + minTaps(n4, ranges4));
        System.out.println();

        // Test Case 5 (Already covered by small overlaps)
        int n5 = 9;
        int[] ranges5 = {0,5,0,3,3,3,1,4,0,4};
        System.out.println("Test 5 Expected: 2");
        System.out.println("Output: " + minTaps(n5, ranges5));
    }
}
