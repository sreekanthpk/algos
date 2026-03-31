package com.practice.algo.generic;

import java.util.Arrays;

public class RotateArray {

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // handle cases where k > n
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    // Helper method to reverse elements in array
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    // Main method with test cases
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        rotate(test1, k1);
        System.out.println("Test 1: " + Arrays.toString(test1)); // [5,6,7,1,2,3,4]

        int[] test2 = {-1, -100, 3, 99};
        int k2 = 2;
        rotate(test2, k2);
        System.out.println("Test 2: " + Arrays.toString(test2)); // [3,99,-1,-100]

        int[] test3 = {1, 2, 3};
        int k3 = 4; // k > length
        rotate(test3, k3);
        System.out.println("Test 3: " + Arrays.toString(test3)); // [3,1,2]

        int[] test4 = {1};
        int k4 = 0; // no rotation
        rotate(test4, k4);
        System.out.println("Test 4: " + Arrays.toString(test4)); // [1]
    }
}