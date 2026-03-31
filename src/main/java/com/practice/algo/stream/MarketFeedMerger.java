package com.practice.algo.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarketFeedMerger {

    public static Iterator<Long> mergeStreams(Iterator<Long> streamA, Iterator<Long> streamB) {
        return new Iterator<>() {

            Long nextA = streamA.hasNext() ? streamA.next() : null;
            Long nextB = streamB.hasNext() ? streamB.next() : null;

            @Override
            public boolean hasNext() {
                return nextA != null || nextB != null;
            }

            @Override
            public Long next() {
                if (nextA == null) {
                    Long result = nextB;
                    nextB = streamB.hasNext() ? streamB.next() : null;
                    return result;
                } else if (nextB == null) {
                    Long result = nextA;
                    nextA = streamA.hasNext() ? streamA.next() : null;
                    return result;
                } else {
                    if (nextA <= nextB) {
                        Long result = nextA;
                        nextA = streamA.hasNext() ? streamA.next() : null;
                        return result;
                    } else {
                        Long result = nextB;
                        nextB = streamB.hasNext() ? streamB.next() : null;
                        return result;
                    }
                }
            }
        };
    }


    public static void main(String[] args) {

        // Test 1: Normal sorted streams
        testCase(
                List.of(1L, 3L, 5L, 7L),
                List.of(2L, 4L, 6L, 8L),
                "Test 1: Normal Merge"
        );

        // Test 2: One stream empty
        testCase(
                List.of(),
                List.of(10L, 20L, 30L),
                "Test 2: One Empty Stream"
        );

        // Test 3: Both streams empty
        testCase(
                List.of(),
                List.of(),
                "Test 3: Both Empty"
        );

        // Test 4: Duplicates
        testCase(
                List.of(1L, 2L, 2L, 5L),
                List.of(2L, 3L, 5L),
                "Test 4: With Duplicates"
        );

        // Test 5: Uneven sizes
        testCase(
                List.of(1L),
                List.of(2L, 3L, 4L, 5L, 6L),
                "Test 5: Uneven Sizes"
        );

        // Test 6: Large gaps
        testCase(
                List.of(1L, 100L, 1000L),
                List.of(50L, 200L, 2000L),
                "Test 6: Large Gaps"
        );
    }

    private static void testCase(List<Long> listA, List<Long> listB, String testName) {
        System.out.println("---- " + testName + " ----");

        Iterator<Long> merged = MarketFeedMerger.mergeStreams(
                listA.iterator(),
                listB.iterator()
        );

        List<Long> result = new ArrayList<>();
        while (merged.hasNext()) {
            result.add(merged.next());
        }

        System.out.println("Stream A: " + listA);
        System.out.println("Stream B: " + listB);
        System.out.println("Merged  : " + result);
        System.out.println();
    }
}
