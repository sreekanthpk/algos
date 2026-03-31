package com.practice.algo.generic;

/**
 * Candy Distribution Problem (Hard):
 * There are N children standing in a line.
 * Each child has a rating.
 * You must give at least 1 candy to each child,
 * and children with higher ratings than their
 * neighbors must get more candies. Minimize total candies. - Done
 */
public class CandyDistribution {

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Step 1: Give each child one candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }

        // Step 2: Left to right pass
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Step 3: Right to left pass
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Step 4: Sum all candies
        int totalCandies = 0;
        for (int i = 0; i < n; i++) {
            totalCandies += candies[i];
        }

        return totalCandies;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        int result = candy(ratings);
        System.out.println("Minimum candies: " + result); // Expected: 5
    }
}

