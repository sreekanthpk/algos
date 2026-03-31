package com.practice.algo.generic;

import java.util.*;

public class CombinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start,
                                  List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            // Exceeded the target
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            // not i+1 because we can reuse the same number
            backtrack(candidates, target - candidates[i], i, path, result);
            path.remove(path.size() - 1); // backtrack
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = combinationSum(candidates, target);
        System.out.println(combinations);
    }
}