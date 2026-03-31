package com.practice.algo.generic;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                left++;
                set.remove(s.charAt(left));

            }
            set.add(s.charAt(right));
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String... args) {
        System.out.println(lengthOfLongestSubstring("abcdabcbb"));
    }
}
