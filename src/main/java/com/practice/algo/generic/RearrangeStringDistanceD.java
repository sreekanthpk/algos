package com.practice.algo.generic;

import java.util.*;

public class RearrangeStringDistanceD {

    static class Pair {
        char ch;
        int count;

        Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String rearrangeString(String s, int d) {
        if (d <= 1) return s;

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                (a, b) -> b.count - a.count
        );

        for (char c : freq.keySet()) {
            maxHeap.offer(new Pair(c, freq.get(c)));
        }

        Queue<Pair> cooldown = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            Pair current = maxHeap.poll();
            result.append(current.ch);
            current.count--;

            cooldown.offer(current);

            if (cooldown.size() >= d) {
                Pair front = cooldown.poll();
                if (front.count > 0) {
                    maxHeap.offer(front);
                }
            }
        }

        return result.length() == s.length() ? result.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println(rearrangeString("aaabc", 2));
    }
}