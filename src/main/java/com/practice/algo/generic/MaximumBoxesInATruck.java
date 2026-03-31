package com.practice.algo.generic;

import java.util.Arrays;

/**
 * You are assigned to put some amount of boxes onto one truck.
 *
 * You are given a 2D array boxTypes, where:
 *
 * boxTypes[i][0] is the number of boxes of type i
 *
 * boxTypes[i][1] is the number of units per box of type i
 *
 * You are also given an integer truckSize, which is the maximum number of boxes the truck can carry.
 */
public class MaximumBoxesInATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {

        // Sort by units per box in descending order
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);

        int totalUnits = 0;

        for (int[] box : boxTypes) {
            int numberOfBoxes = box[0];
            int unitsPerBox = box[1];

            int boxesToTake = Math.min(numberOfBoxes, truckSize);

            totalUnits += boxesToTake * unitsPerBox;
            truckSize -= boxesToTake;

            if (truckSize == 0) break;
        }

        return totalUnits;
    }

    public static void main(String[] args) {
        MaximumBoxesInATruck solution = new MaximumBoxesInATruck();

        int[][] boxTypes = {{1,3},{2,2},{3,1}};
        int truckSize = 4;

        System.out.println(solution.maximumUnits(boxTypes, truckSize)); // 8
    }
}
