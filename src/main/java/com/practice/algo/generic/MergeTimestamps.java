package com.practice.algo.generic;

import java.util.ArrayList;
import java.util.List;

public class MergeTimestamps {

    public static List<Long> mergeSortedTimestamps(List<Long> A, List<Long> B) {
        int i = 0, j = 0;
        List<Long> C = new ArrayList<>();

        // Merge until one list is exhausted
        while (i < A.size() && j < B.size()) {
            if (A.get(i) <= B.get(j)) {
                C.add(A.get(i));
                i++;
            } else {
                C.add(B.get(j));
                j++;
            }
        }

        // Append remaining elements from A
        while (i < A.size()) {
            C.add(A.get(i));
            i++;
        }

        // Append remaining elements from B
        while (j < B.size()) {
            C.add(B.get(j));
            j++;
        }

        return C;
    }

    // Example usage
    public static void main(String[] args) {
        List<Long> A = List.of(1L, 4L, 6L);
        List<Long> B = List.of(2L, 3L, 5L, 7L);

        List<Long> merged = mergeSortedTimestamps(A, B);
        System.out.println(merged);  // Output: [1, 2, 3, 4, 5, 6, 7]
    }
}
