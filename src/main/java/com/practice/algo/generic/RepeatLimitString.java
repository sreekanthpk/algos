package com.practice.algo.generic;

import java.util.*;

/**
 * Construct String with Repeat Limit:
 * Similar to the above, but you can use a character up to repeatLimit times.
 */
public class RepeatLimitString {
    public static String repeatLimitedString(String s, int repeatLimit) {
        // Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Max-heap: store characters by their value (lexicographically largest first)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(new int[]{i, freq[i]}); // [charIndex, count]
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            int use = Math.min(first[1], repeatLimit); // use at most repeatLimit
            for (int i = 0; i < use; i++) {
                sb.append((char) (first[0] + 'a'));
            }
            first[1] -= use;

            if (first[1] > 0) {
                if (pq.isEmpty()) break; // cannot place remaining first[0] without exceeding limit

                int[] second = pq.poll();
                sb.append((char) (second[0] + 'a'));
                second[1]--;
                if (second[1] > 0) pq.offer(second);

                pq.offer(first); // push back first after inserting second
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(repeatLimitedString("cczazcc", 2)); // Example: "zzcccca"
        System.out.println(repeatLimitedString("aabbcc", 2));  // Example: "ccbbaa"
        System.out.println(repeatLimitedString("aaaaa", 2));   // Example: "aa"
    }
}
