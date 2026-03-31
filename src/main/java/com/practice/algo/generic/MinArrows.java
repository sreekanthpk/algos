package com.practice.algo.generic;
import java.util.Arrays;

public class MinArrows {
    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;

        // Sort intervals by their end coordinate
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;               // At least one arrow
        int currentEnd = points[0][1]; // Shoot at end of first interval

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > currentEnd) {
                // Current balloon starts after the last arrow → need new arrow
                arrows++;
                currentEnd = points[i][1];
            }
            // else: balloon overlaps with current arrow → burst together
        }

        return arrows;
    }

    public static void main(String[] args) {
        int[][] points1 = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points1)); // Output: 2

        int[][] points2 = {{1,2},{3,4},{5,6},{7,8}};
        System.out.println(findMinArrowShots(points2)); // Output: 4

        int[][] points3 = {{1,2},{2,3},{3,4},{4,5}};
        System.out.println(findMinArrowShots(points3)); // Output: 2
    }
}
