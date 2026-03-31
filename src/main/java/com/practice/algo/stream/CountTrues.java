package com.practice.algo.stream;

import java.util.Arrays;
import java.util.List;

public class CountTrues {
    public static void main(String[] args) {
        List<Boolean> booleanList = Arrays.asList(true, false, true, true, false);

        long trueCount = booleanList.stream()
                .filter(Boolean::booleanValue) // or .filter(b -> b) or .filter(Boolean.TRUE::equals)
                .count();

        System.out.println("Number of true values: " + trueCount); // Output: Number of true values: 3
    }
}