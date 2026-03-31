package com.practice.algo.generic;

public class HarshadNumberProgram {

    public static void main(String[] args) {

        // Test cases
        testHarshad(18);   // Harshad
        testHarshad(21);   // Harshad
        testHarshad(19);   // Not Harshad
        testHarshad(1729); // Harshad
    }

    private static void testHarshad(int number) {
        if (isHarshadNumber(number)) {
            System.out.println(number + " is a Harshad number");
        } else {
            System.out.println(number + " is not a Harshad number");
        }
    }

    /**
     * Returns true if the number is a Harshad number.
     */
    public static boolean isHarshadNumber(int number) {
        if (number <= 0) {
            return false;
        }

        int digitSum = getDigitSum(number);
        return number % digitSum == 0;
    }

    /**
     * Calculates the sum of digits.
     */
    private static int getDigitSum(int number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }
}