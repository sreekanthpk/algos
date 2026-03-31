package com.practice.algo.matrix;

/**
 * Write an efficient algorithm that searches for a value in an
 * m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 *
 * Integers in each column are sorted in ascending from top to bottom.
 */
public class MatrixValueSearch {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0;
        int col = n - 1;  // start at top-right

        while (row < m && col >= 0) {
            int value = matrix[row][col];

            if (value == target) {
                return true;
            } else if (value > target) {
                col--;      // move left
            } else {
                row++;      // move down
            }
        }

        return false;
    }
}
