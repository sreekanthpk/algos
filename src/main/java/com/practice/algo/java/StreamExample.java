package com.practice.algo.java;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {

    public static void main(String[] args) {

        String[] s = {"apple", "banana", "cherry"};

        Arrays.stream(s).filter((k)-> k.startsWith("s")).toList();


        List<String> words = Arrays.asList("apple", "banana", "cherry");

        int totalLength = words.stream()
                .mapToInt(String::length) // map each word to its length
                .sum(); // reduce by summing all lengths

        //Intermediate operations
        // map, filter, sorted , distinct, flatmap, takewhile, stopwhile, dropfalse
        //terminal operation
        //collect, reduce, forEach, anyMatch,

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);

    }
}
