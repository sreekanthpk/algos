package com.practice.algo.generic;

import java.util.*;

/**
 * Substrings with out any duplicates
 */
public class UniqueSubstrings {

    public static int countUniqueSubstrings(String s) {
        int count = 0;
        int start = 0;
        Set<Character> seen = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (seen.contains(ch)) {
                if (start < i) {
                    count++;
                }
                start = i;
                seen.clear();
                seen.add(ch);
            } else {
                seen.add(ch);
            }
        }

        if (start < s.length()) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("Count: " + countUniqueSubstrings("world"));
        // Output: Substrings: [world], Count: 1

        System.out.println("Count: " + countUniqueSubstrings("hello"));
        // Output: Substrings: [hel, lo], Count: 2

        System.out.println("Count: " + countUniqueSubstrings("abcabc"));
        // Output: Substrings: [abc, abc], Count: 2

        System.out.println("Count: " + countUniqueSubstrings("aabbcc"));
        // Output: Substrings: [a, ab, bc, c], Count: 4
    }
}
