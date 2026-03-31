package com.practice.algo.generic;

import java.util.*;

/**
 * Find maximized capital
 * k → max number of projects you can pick
 * w → initial capital
 * profits[i] → profit of project i
 * capital[i] → minimum capital required for project i
 */
public class IPO {

    static class Project {
        int capital;
        int profit;

        Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        int n = profits.length;

        Project[] projects = new Project[n];
        for (int i = 0; i < n; i++) {
            projects[i] = new Project(capital[i], profits[i]);
        }

        // Sort by required capital
        Arrays.sort(projects, Comparator.comparingInt(p -> p.capital));

        // Max heap for profits
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int index = 0;

        for (int i = 0; i < k; i++) {

            // Add all affordable projects
            while (index < n && projects[index].capital <= w) {
                maxHeap.add(projects[index].profit);
                index++;
            }

            // If no project available
            if (maxHeap.isEmpty())
                break;

            // Pick most profitable
            w += maxHeap.poll();
        }

        return w;
    }

    public static void main(String[] args) {

        int k = 2;
        int w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};

        System.out.println(findMaximizedCapital(k, w, profits, capital));
        // Expected output: 4
    }
}
