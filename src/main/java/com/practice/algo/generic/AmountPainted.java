package com.practice.algo.generic;

import java.util.*;

/**
 * Amount of New Area Painted Each Day:
 * You have a long wall and several
 * painters who paint specific intervals on different days.
 * leetcode 2158
 */
public class AmountPainted {
    public static int[] amountPainted(int[][] paint) {
        int n = paint.length;
        int[] result = new int[n];

        // Find maximum coordinate to size array
        int maxCoord = 0;
        for (int[] p : paint) maxCoord = Math.max(maxCoord, p[1]);

        boolean[] painted = new boolean[maxCoord + 1];

        for (int i = 0; i < n; i++) {
            int start = paint[i][0];
            int end = paint[i][1];
            int newPaint = 0;

            for (int j = start; j < end; j++) {
                if (!painted[j]) {
                    painted[j] = true;
                    newPaint++;
                }
            }

            result[i] = newPaint;
        }

        return result;
    }

    public static void main(String[] args) {
        //Already sorted
        int[][] paint = {{1,4},{2,5},{7,9}};
        System.out.println(Arrays.toString(amountPainted(paint))); // Output: [3,1,2]

        int[][] paint2 = {{1,7},{4,8},{10,15}};
        System.out.println(Arrays.toString(amountPainted(paint2))); // Output: [6,1,5]
    }
}
