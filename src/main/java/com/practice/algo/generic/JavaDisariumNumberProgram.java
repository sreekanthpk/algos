package com.practice.algo.generic;

import java.util.Scanner;

public class JavaDisariumNumberProgram
{
    private static boolean isItDisariumNumber(int inputNumber)
    {
        int noOfDigits = Integer.toString(inputNumber).length();

        int copyOfInputNumber = inputNumber;

        int sum = 0;

        while (inputNumber > 0)
        {
            int lastDigit = inputNumber % 10;

            sum = sum + (int) Math.pow(lastDigit, noOfDigits);

            inputNumber = inputNumber / 10;

            noOfDigits--;
        }

        if (sum == copyOfInputNumber)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number :");

        int inputNumber = sc.nextInt();

        if (isItDisariumNumber(inputNumber))
        {
            System.out.println(inputNumber+" is a Disarium number");
        }
        else
        {
            System.out.println(inputNumber+" is not a Disarium number");
        }

        sc.close();
    }
}