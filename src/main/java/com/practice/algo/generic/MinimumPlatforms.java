package com.practice.algo.generic;

import java.util.*;

public class MinimumPlatforms {

    public static int findMinimumPlatforms(int[] arrivals, int[] departures) {
        int n = arrivals.length;

        // Sort both arrays
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int platformsNeeded = 0;
        int maxPlatforms = 0;

        int i = 0;
        int j = 0;

        while (i < n && j < n) {
            // If the next train arrives before or at the same time the previous one departs, we need another platform
            if (arrivals[i] <= departures[j]) {
                platformsNeeded++;
                i++; // Move to next arrival
            } else {
                platformsNeeded--;
                j++; // Move to next departure
            }

            // Track the maximum number of platforms needed at any time
            if (platformsNeeded > maxPlatforms) {
                maxPlatforms = platformsNeeded;
            }
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {
        int[] arrivals = {900, 940, 950, 1100, 1500, 1800};
        int[] departures = {910, 1200, 1120, 1130, 1900, 2000};

        int result = findMinimumPlatforms(arrivals, departures);
        System.out.println("Minimum number of platforms required: " + result);
    }
}
