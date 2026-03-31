package com.practice.algo.generic;

import java.util.*;

/**
 * LeetCode 881 — Boats to Save People.
 * Boats to Save People: You have a
 * weight limit per boat and an array
 * of people's weights. Each boat carries
 * at most two people. Find the minimum boats.
 * Can carry at most 2 people
 */
public class BoatsToSavePeople {

    public static int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int boats = 0;

        while (left <= right) {

            if (people[left] + people[right] <= limit) {
                left++;
            }

            right--;
            boats++;
        }

        return boats;
    }

    public static void main(String[] args) {
        int[] people = {3,2,2,1};
        System.out.println(numRescueBoats(people, 3)); // 3
        int[] people1 = {3,2,2,1, 1,1};
        System.out.println(numRescueBoats(people1, 3)); // 3
    }
}
