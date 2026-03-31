package com.practice.algo.generic;

public class ShortestWayToFormString {

    public static int shortestWay(String source, String target) {
        int count = 0;   // number of subsequences used
        int i = 0;       // pointer for target

        // Build a set of characters in source for quick lookup
        boolean[] exist = new boolean[26];
        for (char c : source.toCharArray()) {
            exist[c - 'a'] = true;
        }

        while (i < target.length()) {
            int start = i;

            // Scan source once
            for (char c : source.toCharArray()) {
                if (i < target.length() && target.charAt(i) == c) {
                    i++;
                }
            }

            // If no progress, character in target not in source
            if (i == start) return -1;

            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String source1 = "abc", target1 = "abcbc";
        System.out.println("Test 1 Expected: 2");
        System.out.println("Output: " + shortestWay(source1, target1));

        String source2 = "abc", target2 = "acdbc";
        System.out.println("Test 2 Expected: -1");
        System.out.println("Output: " + shortestWay(source2, target2));

        String source3 = "xyz", target3 = "xzyxz";
        System.out.println("Test 3 Expected: 3");
        System.out.println("Output: " + shortestWay(source3, target3));
    }
}

