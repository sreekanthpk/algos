package com.practice.algo.generic;

import java.util.*;

public class QueueReconstruction {

    public static int[][] reconstructQueue(int[][] people) {

        // Step 1: Sort
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];      // k ascending
            return b[0] - a[0];          // height descending
        });

        List<int[]> result = new LinkedList<>();

        // Step 2: Insert at index k
        for (int[] person : people) {
            result.add(person[1], person);
        }

        return result.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        int[][] input = {
                {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
        };

        int[][] output = reconstructQueue(input);

        for (int[] p : output) {
            System.out.println(Arrays.toString(p));
        }
    }
}
