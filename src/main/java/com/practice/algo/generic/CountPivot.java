package com.practice.algo.generic;

import java.util.Arrays;

public class CountPivot {


    public static void main(String... args){
        Integer[] arr = {5, 1, 4, 3, 6, 8, 10, 7, 9};

        System.out.println(countPivots(arr));
    }

    private static int countPivots(Integer[] arr) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean[] result = new boolean[arr.length];
        boolean[] result1 = new boolean[arr.length];

        for(int i=0; i< arr.length; i++){
            if(arr[i] > max){
                max = Math.max(arr[i],max);
                result[i] = true;
            }else {
                result[i] = false;
            }
        }

        System.out.println(Arrays.toString(result));

        for(int j= arr.length-1; j>=0; j--){

            if(arr[j] < min ){
                min = Math.min(arr[j],min);
                result1[j] = true;
            } else {
                result1[j] = false;
            }
        }

        System.out.println(Arrays.toString(result1));

        int count = 0;
        for(int i=0; i< arr.length; i++){
            if(result[i] && result1[i]){count++;}
        }
        return count;
    }
}
