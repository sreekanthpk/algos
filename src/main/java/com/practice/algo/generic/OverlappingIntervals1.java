package com.practice.algo.generic;

import java.util.*;

public class OverlappingIntervals1 {

    public static void main(String[] args) {
        int[][] arr = {{2, 5}, {1, 3}, {4, 8}, {10, 11}};

        mergeIntervals(arr);

    }

    private static void mergeIntervals(int[][] arr) {

        Arrays.sort(arr, (a,b)->Integer.compare(a[0],b[0]));
        System.out.println(Arrays.deepToString(arr));

        int[] current = arr[0];
        List<int[]>  merged = new ArrayList<>();
        merged.add(current);

        for (int i = 1; i < arr.length; i++) {
            if(current[1] > arr[i][0]){
                current[1] = arr[i][1];
            }else {
                current = arr[i];
                merged.add(arr[i]);
            }

        }

        System.out.println(Arrays.deepToString(merged.toArray()));

    }
}
