package com.practice.algo.matrix;

import java.util.Scanner;

public class MatrixMultiplication
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter The Number Of Rows In First Matrix");

        int rowsInFirst = sc.nextInt();

        System.out.println("Enter The Number Of Columns In First Matrix / Rows In Second Matrix");

        int colsInFirstRowsInSecond = sc.nextInt();

        System.out.println("Enter The Number Of Columns In Second Matrix");

        int colsInSecond = sc.nextInt();

        int[][] matrix1 = new int[rowsInFirst][colsInFirstRowsInSecond];

        int[][] matrix2 = new int[colsInFirstRowsInSecond][colsInSecond];

        int[][] product = new int[rowsInFirst][colsInSecond];

        System.out.println("Enter The Data For First Matrix :");

        for (int i = 0; i < rowsInFirst; i++)
        {
            for (int j = 0; j < colsInFirstRowsInSecond; j++)
            {
                matrix1[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter The Data For Second Matrix :");

        for (int i = 0; i < colsInFirstRowsInSecond; i++)
        {
            for (int j = 0; j < colsInSecond; j++)
            {
                matrix2[i][j] = sc.nextInt();
            }
        }

        System.out.println("First Matrix = ");

        for (int i = 0; i < rowsInFirst; i++)
        {
            for (int j = 0; j < colsInFirstRowsInSecond; j++)
            {
                System.out.print(matrix1[i][j]+"\t");
            }

            System.out.println();
        }

        System.out.println("Second Matrix = ");

        for (int i = 0; i < colsInFirstRowsInSecond; i++)
        {
            for (int j = 0; j < colsInSecond; j++)
            {
                System.out.print(matrix2[i][j]+"\t");
            }

            System.out.println();
        }

        System.out.println("Product = ");

        for (int i = 0; i < rowsInFirst; i++)
        {
            for (int j = 0; j < colsInSecond; j++)
            {
                for (int k = 0; k < colsInFirstRowsInSecond; k++)
                {
                    product[i][j] +=  matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        for (int i = 0; i < rowsInFirst; i++)
        {
            for (int j = 0; j < colsInSecond; j++)
            {
                System.out.print(product[i][j]+"\t");
            }

            System.out.println();
        }
    }
}