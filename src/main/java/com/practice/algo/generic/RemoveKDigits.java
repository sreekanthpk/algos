package com.practice.algo.generic;

import java.util.*;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";

        Deque<Character> stack = new ArrayDeque<>();

        for (char digit : num.toCharArray()) {

            while (!stack.isEmpty() && k > 0 && stack.peekLast() > digit) {
                stack.pollLast();
                k--;
            }

            stack.offerLast(digit);
        }

        // If k still remains, remove from end
        while (k > 0) {
            stack.pollLast();
            k--;
        }

        // Build result
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pollFirst());
        }

        // Remove leading zeros
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.length() == 0 ? "0" : result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3)); // 1219
    }
}
