package com.practice.algo.generic;


public class MatrixDiagonalSum {

    public static int diagonalSum(int[][] mat) {

        int sum = 0;
        for(int i = 0; i < mat.length; i++){
            if(i == mat.length - 1-i){
                sum += mat[i][i];
            }else {
                sum += mat[i][i] + mat[i][mat.length-1 - i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        // Test Case 1: Odd-sized matrix (3x3)
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Diagonal Sum (3x3): " + diagonalSum(matrix1));
        // Expected: 1 + 5 + 9 + 3 + 7 = 25


        // Test Case 2: Even-sized matrix (4x4)
        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        System.out.println("Diagonal Sum (4x4): " + diagonalSum(matrix2));
        // Expected: (1+6+11+16) + (4+7+10+13) = 68


        // Test Case 3: Single element matrix
        int[][] matrix3 = {
                {42}
        };

        System.out.println("Diagonal Sum (1x1): " + diagonalSum(matrix3));
        // Expected: 42
    }
}