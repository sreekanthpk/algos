package com.practice.algo.generic;

public class MinimumTimeToCompleteTrips {
    public static long minimumTime(int[] time, int totalTrips) {
        long left = 1;
        long right = 0;
        for (int t : time) {
            right = Math.max(right, (long)t * totalTrips); // worst-case max time
        }

        while (left < right) {
            long mid = left + (right - left) / 2;
            long trips = 0;
            for (int t : time) {
                trips += mid / t; // number of trips this bus can make in 'mid' time
            }

            if (trips >= totalTrips) {
                right = mid; // try smaller time
            } else {
                left = mid + 1; // need more time
            }
        }

        return left; // minimum time required
    }

    // Example usage
    public static void main(String[] args) {
        int[] time = {1, 2, 3};
        int totalTrips = 5;
        System.out.println(minimumTime(time, totalTrips)); // Output: 3
    }
}
