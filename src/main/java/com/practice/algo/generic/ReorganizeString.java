package com.practice.algo.generic;

import java.util.*;

/**
 * Reorganize String: Rearrange a string so no two characters are adjacent.
 */
public class ReorganizeString {
    public static String reorganizeString(String s) {
        // Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Max-heap based on frequency
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(new int[]{i, freq[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] prev = {-1, 0}; // previous character (index, freq)

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // highest frequency
            sb.append((char) (curr[0] + 'a'));
            curr[1]--; // used one occurrence

            // put previous character back if it still has frequency
            if (prev[1] > 0) {
                pq.offer(prev);
            }

            prev = curr; // set current as previous for next iteration
        }

        // If the result length equals input length, it’s valid
        return sb.length() == s.length() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));   // "aba"
        System.out.println(reorganizeString("aaab"));  // ""
        System.out.println(reorganizeString("aaabc")); // "abaca" or similar
    }
}
