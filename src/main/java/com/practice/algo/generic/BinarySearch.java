package com.practice.algo.generic;

public class BinarySearch {


    public static void main(String... args){
        int[] arr = {1,2,3,4,5,6,7,8};

        System.out.println(findElement(arr, 4));
    }

    private static int findElement(int[] arr, int k) {
        int low = 0;
        int high = arr.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(arr[mid]==k){ return mid;}
            else if(k<arr[mid]){
                high = mid-1;
            } else if(k>arr[mid]){
                low = mid+1;
            }
        }
        return -1;
    }
}
