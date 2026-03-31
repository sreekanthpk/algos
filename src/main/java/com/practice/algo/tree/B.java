package com.practice.algo.tree;

import java.util.ArrayList;
import java.util.List;

public class B {
    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int c : cards) nums.add((double) c);
        return dfs(nums);
    }

    private boolean dfs(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < 1e-6;
        }

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) next.add(nums.get(k));
                }

                double a = nums.get(i);
                double b = nums.get(j);

                double[] results = new double[] {
                        a + b,
                        a - b,
                        b - a,
                        a * b
                };

                for (double r : results) {
                    next.add(r);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }

                if (Math.abs(b) > 1e-6) {
                    next.add(a / b);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }

                if (Math.abs(a) > 1e-6) {
                    next.add(b / a);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }

        return false;
    }
}
