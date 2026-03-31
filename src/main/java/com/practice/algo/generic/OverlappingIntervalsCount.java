package com.practice.algo.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverlappingIntervalsCount {

    public static void main(String[] args) {
        int[][] arr = {{2, 5}, {1, 3}, {4, 8}, {10, 11}};

        mergeIntervals(arr);

    }

    private static void mergeIntervals(int[][] arr) {

        Arrays.sort(arr, (a,b)->Integer.compare(a[1],b[1]));
        System.out.println(Arrays.deepToString(arr));

        int lastEndTime = arr[0][1];
        int count = 0;

        for (int i = 1; i < arr.length; i++) {
            if( arr[i][0] > lastEndTime ){
               count++;
               lastEndTime = arr[i][1];
            }

        }

        System.out.println(count);

    }
}
