package com.practice.algo.generic;

/**
 * maximum sum of k sub array
 */
public class SlidingWinowsMaxSum {

     public static int maxSum(int[] num, int k){
         int windowsSum = 0;

         for(int i=0;i<k;i++){
             windowsSum+=num[i];
         }
         int maxSum = windowsSum;

         for(int right = k ; right<num.length; right++){
             windowsSum+=num[right]-num[right-k];
             if(windowsSum>maxSum){
                 maxSum=windowsSum;
             }
         }
         return maxSum;
     }

     public static void main(String... args){
            System.out.println(maxSum(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
     }

}
