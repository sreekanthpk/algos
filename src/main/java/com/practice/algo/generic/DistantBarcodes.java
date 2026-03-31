package com.practice.algo.generic;

import java.util.*;

/**
 * Distant Barcodes:
 * Given a set of barcodes (integers),
 * rearrange them so that no two adjacent barcodes are the same.
 */
public class DistantBarcodes {

    public static int[] rearrangeBarcodes(int[] barcodes) {
        // Step 1: Count frequency
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int code : barcodes) {
            freqMap.put(code, freqMap.getOrDefault(code, 0) + 1);
        }

        // Step 2: Sort barcodes by frequency
        Integer[] codes = freqMap.keySet().toArray(new Integer[0]);
        Arrays.sort(codes, (a, b) -> freqMap.get(b) - freqMap.get(a));

        int n = barcodes.length;
        int[] res = new int[n];
        int index = 0;

        // Step 3: Place elements
        for (int code : codes) {
            int count = freqMap.get(code);
            for (int i = 0; i < count; i++) {
                res[index] = code;
                index += 2;
                if (index >= n) index = 1; // switch to odd indices
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] barcodes1 = {1,1,1,2,2,3};
        System.out.println(Arrays.toString(rearrangeBarcodes(barcodes1)));
        // Example output: [1,2,1,2,1,3]

        int[] barcodes2 = {1,1,1,1,2,2,3,3};
        System.out.println(Arrays.toString(rearrangeBarcodes(barcodes2)));

        int[] barcodes3 = {7,7,7,8,5,5,5,5};
        System.out.println(Arrays.toString(rearrangeBarcodes(barcodes3)));

        int[] barcodes4 = {1};
        System.out.println(Arrays.toString(rearrangeBarcodes(barcodes4)));
    }
}
