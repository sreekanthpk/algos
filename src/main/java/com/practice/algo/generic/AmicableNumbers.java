package com.practice.algo.generic;

/** This program finds all amicable numbers between 1 to 10000
 *  Amicable numbers are sum of divisors of the number and the
 *  sum of divisors of the sum are same
 */
public class AmicableNumbers {

    public static void main(String[] args) {
        findAmicableNumbers();
    }

    public static void findAmicableNumbers() {

        for (long i = 2; i < 10000; i++) {
            long sum = sumOfDivisors(i);
            long reverse = sumOfDivisors(sum);

            if (reverse == i && sum > i) {
                System.out.println(i + " is amicable with " + sum);
            }
        }
    }

    public static long sumOfDivisors(long n) {
        if (n <= 1) return 0;

        long sum = 1;
        long limit = (long) Math.sqrt(n);

        for (long i = 2; i <= limit; i++) {
            if (n % i == 0) {
                sum += i;
                long other = n / i;
                if (other != i) {
                    sum += other;
                }
            }
        }
        return sum;
    }
}
