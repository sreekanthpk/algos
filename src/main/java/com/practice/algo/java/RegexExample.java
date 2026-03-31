package com.practice.algo.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        String input = "123.1.1.2";

        // Compile the regex
        Pattern pattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b"); // one or more digits

        // Create a matcher
        Matcher matcher = pattern.matcher(input);

        // Perform matching
        if (matcher.find()) {
            System.out.println("Match found: " + matcher.group());
        } else {
            System.out.println("No match found");
        }
    }
}