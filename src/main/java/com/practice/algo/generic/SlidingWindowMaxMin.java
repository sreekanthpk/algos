package com.practice.algo.generic;

import java.util.Deque;
import java.util.LinkedList;

/**
Disk Space Analysis – Maximum of Minimums

You are given an array diskSpace of length n, where each element represents available disk space on a server at a specific time.

You are also given an integer k, representing the size of a monitoring window.
**/
 public class SlidingWindowMaxMin {


     public static void main(String[] args) {
         int[] diskSpace = {10, 20, 30, 50, 10, 70, 30};
         int k = 3;

         System.out.println(findMaxInMin(diskSpace, k));

     }

    private static int findMaxInMin(int[] diskSpace, int k) {
        int max = Integer.MIN_VALUE;
        int[] result =  new int[diskSpace.length-k+1];
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < diskSpace.length; i++) {
            if (!q.isEmpty() && q.peekFirst() <= i - k) {
                q.pollFirst();
            }

            while (!q.isEmpty() && diskSpace[q.peekLast()] > diskSpace[i]) {
                q.pollLast();
            }

            q.offerLast(i);

            if (i >= k - 1) {
                max = Math.max(max, diskSpace[q.peekFirst()]);
            }
        }

        return max;
    }
}
