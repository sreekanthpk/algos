package com.practice.algo.generic;


import java.util.HashSet;
import java.util.Set;


/**
 * Counting unique fractions
 */
public class UniqueFractions {

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int countUniqueFractions(int[] arr1, int[] arr2) {
        Set<String> set = new HashSet<>();

        for (int a : arr1) {
            for (int b : arr2) {

                if (b == 0) continue;  // avoid division by zero

                int g = gcd(Math.abs(a), Math.abs(b));

                int num = a / g;
                int den = b / g;

                // normalize sign (keep denominator positive)
                if (den < 0) {
                    num = -num;
                    den = -den;
                }

                set.add(num + "/" + den);
            }
        }

        return set.size();
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {2, 4};

        System.out.println(countUniqueFractions(arr1, arr2));
    }
}