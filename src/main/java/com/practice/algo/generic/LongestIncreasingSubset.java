package com.practice.algo.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Size of longest increasing subset
 */
public class LongestIncreasingSubset {

        public int lengthOfLIS(int[] nums) {

            List<Integer> sub = new ArrayList<>();

            for (int num : nums) {
                int i = Collections.binarySearch(sub, num);
                if (i < 0) i = -(i + 1); // insertion point
                if (i < sub.size()) {
                    sub.set(i, num);
                } else {
                    sub.add(num);
                }
            }

            return sub.size();

        }
}
