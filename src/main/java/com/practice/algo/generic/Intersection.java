package com.practice.algo.generic;

import java.util.Arrays;
import java.util.HashSet;

public class Intersection {

        static void intersection(Integer[] ... inputArrays)
        {
            //Printing input arrays

            System.out.println("Input Arrays :");

            System.out.println("======================");

            for (Integer[] inputArray : inputArrays)
            {
                System.out.println(Arrays.toString(inputArray));
            }

            //Creating HashSet object for first input array

            HashSet<Integer> intersectionSet = new HashSet<>(Arrays.asList(inputArrays[0]));

            //Calling retainAll() method of first object by passing 2nd, 3rd, 4th... objects

            for (int i = 1; i < inputArrays.length; i++)
            {
                HashSet<Integer> set = new HashSet<>(Arrays.asList(inputArrays[i]));

                intersectionSet.retainAll(set);
            }

            System.out.println("===========================");

            System.out.println("Intersection Of All Input Arrays :");

            System.out.println("===========================");

            System.out.println(intersectionSet);
        }

        public static void main(String[] args)
        {
            Integer[] inputArray1 = {2, 3, 4, 7, 1};

            Integer[] inputArray2 = {4, 1, 3, 5};

            Integer[] inputArray3 = {8, 4, 6, 2, 1};

            Integer[] inputArray4 = {7, 9, 4, 1};

            intersection(inputArray1, inputArray2, inputArray3, inputArray4);
        }

}
