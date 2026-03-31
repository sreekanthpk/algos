package com.practice.algo.generic;

public class MinimumExpenditure {

    public static long calcMinimumExpenditure(int x, int y, int costX, int costY, int bundle) {
        // 1. Is the bundle actually a good deal?
        long bestBundle = Math.min((long)bundle, (long)costX + costY);

        // 2. Is the bundle cheaper than buying just ONE single item?
        long bestX = Math.min((long)costX, bestBundle);
        long bestY = Math.min((long)costY, bestBundle);

        long minTotal = 0;
        int overlap = Math.min(x, y);

        // Pay for the overlap using the best "pair" price
        minTotal += (long)overlap * bestBundle;

        // Pay for the remaining units using the best "single" price
        if (x > y) {
            minTotal += (long)(x - y) * bestX;
        } else {
            minTotal += (long)(y - x) * bestY;
        }

        return minTotal;
    }

    public static void main(String[] args) {
        // 🔥 Test Case
        int x = 4;
        int y = 3;
        int costX = 8;
        int costY = 7;
        int bundle = 5;

        long result = calcMinimumExpenditure(x, y, costX, costY, bundle);

        System.out.println("Minimum Expenditure: " + result);
    }
}
