package com.practice.algo.practice;

public class MaxOfKSubArray {

    public static void main(String... args){
        System.out.println(maxSum(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    private static int maxSum(int[] ints, int k) {
        int windowSum = 0;

        for(int i = 0;i<k;i++){
            windowSum += ints[i];
        }

        int max = windowSum;

        for(int i = k ; i< ints.length;i++){
            windowSum = windowSum+ints[i]-ints[i-k];
            max = Math.max(max, windowSum);
        }



        return max;
    }
}
