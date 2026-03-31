package com.practice.algo.generic;

import java.util.*;

/**
 * Given a string, partition it into as many parts
 * as possible so that each letter appears in at most one part.
 */
public class PartitionLabels {

    public static List<Integer> partitionLabels(String s) {
        // Step 1: Record the last occurrence of each character
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }

        // Step 2: Iterate and determine partitions
        List<Integer> partitions = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            end = Math.max(end, lastOccurrence.get(c));
            // When we reach the end of a partition
            if (i == end) {
                // Add partition length
                partitions.add(end - start + 1);
                start = i + 1; // Move to the next partition
            }
        }

        return partitions;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> result = partitionLabels(s);
        System.out.println("Partition sizes: " + result);
    }
}
