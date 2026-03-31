package com.practice.algo.generic;

import java.util.*;

/**
 * Non-overlapping Intervals: What is the minimum number of
 * intervals you need to remove to make the rest non-overlapping?
 */
public class MinRemovalsIntervals {

    public static int findMinRemovals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;

        // Step 1: Sort intervals by end times
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]); // Compare by end time
            }
        });

        int removals = 0;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] < lastEnd) {
                // Overlap detected, we need to remove this interval
                removals++;
            } else {
                // No overlap, move the last end to the current interval's end
                lastEnd = intervals[i][1];
            }
        }

        return removals;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},  // Interval 1
                {2, 4},  // Interval 2 (overlaps with 1)
                {3, 5},  // Interval 3 (overlaps with 2)
                {6, 8},  // Interval 4 (no overlap)
                {7, 9}   // Interval 5 (no overlap)
        };

        int result = findMinRemovals(intervals);
        System.out.println("Minimum number of intervals to remove: " + result);
    }
}
