package com.practice.algo.generic;

import java.util.*;

/**
 * Video Stitching / Tap Water Supply: Given a
 * set of intervals (clips/taps), what is the
 * minimum number of intervals needed to cover
 * the entire range $[0, T]$?
 */
public class MinimumIntervalCover {

    public static int minIntervals(int[][] intervals, int T) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int count = 0;
        int currentEnd = 0;
        int i = 0;
        int n = intervals.length;

        while (currentEnd < T) {

            int farthest = currentEnd;

            // Expand as far as possible
            while (i < n && intervals[i][0] <= currentEnd) {
                farthest = Math.max(farthest, intervals[i][1]);
                i++;
            }

            if (farthest == currentEnd) {
                return -1;  // cannot expand further
            }

            currentEnd = farthest;
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {0,2},{4,6},{2,5},{1,3},{5,7}
        };

        System.out.println(minIntervals(intervals, 7)); // 3
    }
}
