package com.practice.algo.generic;

/**
 * Take a positive number.
 * Replace the number with the sum of the squares of its digits.
 * Repeat this process.
 * If you eventually reach 1, the number is called a happy number.
 * If you fall into a loop that never reaches 1, it’s not a happy number.
 */
public class HappyNumber {

    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = sumOfSquares(slow);                  // move 1 step
            fast = sumOfSquares(sumOfSquares(fast));    // move 2 steps
        } while (slow != fast);

        return slow == 1;
    }

    private static int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19)); // true
        System.out.println(isHappy(2));  // false
    }
}