package com.practice.algo.generic;

import java.util.Arrays;

public class MoveZerosToLeft {
    public static void main(String... args) {
        int[] a  = new int[]{1,3, 0, 4,0, 5};
        int writeIndex  = a.length - 1;
        for(int i = a.length-1; i>=0 ; i--) {
            if(a[i] != 0){
                a[writeIndex] = a[i];
                writeIndex --;
            }
        }
        for(int i=0 ; i<= writeIndex ; i++) {
            a[i] = 0;
        }

        System.out.println(Arrays.toString(a));
    }
}
