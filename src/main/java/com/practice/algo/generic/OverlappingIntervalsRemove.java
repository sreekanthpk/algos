package com.practice.algo.generic;

import java.util.Arrays;

public class OverlappingIntervalsRemove {

        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) return 0;

            Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

            int removeCount = 0;
            int prevEnd = intervals[0][1];

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < prevEnd) {
                    removeCount++;   // overlap → remove
                } else {
                    prevEnd = intervals[i][1]; // keep interval
                }
            }

            return removeCount;
        }
}

