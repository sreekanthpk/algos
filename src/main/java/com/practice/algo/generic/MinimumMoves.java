package com.practice.algo.generic;

public class MinimumMoves {

    public static int minMoves(int target, int maxDoubles) {
        int moves = 0;

        while (target > 1) {
            if (maxDoubles > 0 && target % 2 == 0) {
                // Use a double operation (working backwards)
                target /= 2;
                maxDoubles--;
            } else {
                // Use an increment/decrement operation
                target -= 1;
            }
            moves++;
        }

        return moves;
    }

    public static void main(String[] args) {
        System.out.println(minMoves(10, 1)); // Output: 5
        System.out.println(minMoves(10, 2)); // Output: 4
        System.out.println(minMoves(1, 100)); // Output: 0
        System.out.println(minMoves(15, 3)); // Output: 5
    }
}

