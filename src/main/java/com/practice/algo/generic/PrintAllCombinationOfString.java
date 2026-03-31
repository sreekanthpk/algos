package com.practice.algo.generic;

import java.util.Scanner;

public class PrintAllCombinationOfString
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        sc.close();  // Good practice to close scanner

        permute(input.toCharArray(), 0);
    }

    public static void permute(char[] chars, int index) {
        if (index == chars.length) {
            System.out.println(new String(chars));
            return;  // Removed extra println()
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permute(chars, index + 1);
            swap(chars, index, i);  // Backtrack
        }
    }

    public static void swap(char[] arr, int i, int j ){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
}