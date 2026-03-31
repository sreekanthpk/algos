package com.practice.algo.practice;

import java.util.*;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> set = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.containsKey(target - nums[i])) {
                return new int[]{i, set.get(target - nums[i])};
            }
            set.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 6;
        Arrays.stream(twoSum(arr, target)).forEach(System.out::println);
    }
}
