package com.practice.algo.generic;

public class MinimumNumberOfDaysToMakeBouquets {

    public int minDays(int[] bloomDay, int m, int k) {
        long n = bloomDay.length;
        if (m * (long)k > n) return -1; // impossible

        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canMake(bloomDay, m, k, mid)) {
                right = mid; // try smaller day
            } else {
                left = mid + 1; // need more days
            }
        }

        return left;
    }

    private boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0, flowers = 0;
        for (int bloom : bloomDay) {
            if (bloom <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0; // reset consecutive count
            }
        }
        return bouquets >= m;
    }

    public static void main(String[] args) {
        MinimumNumberOfDaysToMakeBouquets sol = new MinimumNumberOfDaysToMakeBouquets();
        int[] bloomDay = {1, 10, 3, 10, 2};
        int m = 3, k = 1;
        System.out.println(sol.minDays(bloomDay, m, k)); // Output: 3
    }
}
