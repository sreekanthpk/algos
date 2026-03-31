package com.practice.algo.generic;

public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Work with long to handle overflow
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);

        int quotient = 0;

        while (dvd >= dvs) {
            long temp = dvs;
            int multiple = 1;
            // Increase temp by powers of 2
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }

    public static void main(String[] args) {
        System.out.println(divide(10, 3));  // Output: 3
        System.out.println(divide(7, -3));  // Output: -2
        System.out.println(divide(-2147483648, -1));  // Output: 2147483647 (overflow handled)
    }
}