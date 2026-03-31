package com.practice.algo.generic;

public class SmallestStringWithGivenValue {

    public static String getSmallestString(int n, int k) {
        char[] result = new char[n];

        // Initialize with 'a'
        for (int i = 0; i < n; i++) {
            result[i] = 'a';
        }

        // Remaining value after using 'a's
        int remaining = k - n;

        // Fill from the end
        for (int i = n - 1; i >= 0 && remaining > 0; i--) {
            int add = Math.min(25, remaining); // max increment for a letter
            result[i] += add;
            remaining -= add;
        }

        return new String(result);
    }

    public static void main(String[] args) {
        int n = 5, k = 73;
        System.out.println("Smallest string: " + getSmallestString(n, k));
    }
}
