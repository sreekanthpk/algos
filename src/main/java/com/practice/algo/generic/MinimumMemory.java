package com.practice.algo.generic;

import java.util.*;

public class MinimumMemory {

    public static int getMinMemory(List<Integer> memoryRequirement, List<Integer> memoryAvailable) {
        // Sort both lists to use a greedy approach
        Collections.sort(memoryRequirement);
        Collections.sort(memoryAvailable);

        int n = memoryRequirement.size();
        int m = memoryAvailable.size(); // This is n - 1

        int reqIdx = n - 1;   // Pointer for programs
        int availIdx = m - 1; // Pointer for existing servers

        int minNewServerMemory = -1;
        int programsSkipped = 0;

        // Try to match the largest programs to the largest existing servers
        while (reqIdx >= 0) {
            if (availIdx >= 0 && memoryAvailable.get(availIdx) >= memoryRequirement.get(reqIdx)) {
                // This program fits in an existing server
                availIdx--;
                reqIdx--;
            } else {
                // This program does NOT fit in any remaining existing server
                // It must be the one assigned to the new server
                minNewServerMemory = memoryRequirement.get(reqIdx);
                programsSkipped++;
                reqIdx--;

                // If more than one program needs the new server, allocation is impossible
                if (programsSkipped > 1) {
                    return -1;
                }
            }
        }

        return minNewServerMemory;
    }


    public static void main(String[] args) {

        runTest(Arrays.asList(2, 4, 6), Arrays.asList(3, 5), 6);
        runTest(Arrays.asList(1, 2, 10), Arrays.asList(2, 10), 1);
        runTest(Arrays.asList(5, 6, 7), Arrays.asList(1, 2), -1);
        runTest(Arrays.asList(3, 3, 3), Arrays.asList(3, 3), 3);
        runTest(Arrays.asList(8, 1, 4), Arrays.asList(2, 4), 8);
        runTest(Arrays.asList(1, 9, 10), Arrays.asList(2, 3), -1);
        runTest(Arrays.asList(5), Collections.emptyList(), 5);
        runTest(Arrays.asList(4, 5, 6), Arrays.asList(5, 6), 4);
    }

    private static void runTest(List<Integer> memoryRequirement,
                                List<Integer> memoryAvailable,
                                int expected) {

        int result = MinimumMemory.getMinMemory(
                new ArrayList<>(memoryRequirement),
                new ArrayList<>(memoryAvailable)
        );

        System.out.println("Requirements: " + memoryRequirement);
        System.out.println("Available   : " + memoryAvailable);
        System.out.println("Expected    : " + expected);
        System.out.println("Result      : " + result);
        System.out.println(result == expected ? "✅ PASS" : "❌ FAIL");
        System.out.println("-----------------------------------");
    }
}