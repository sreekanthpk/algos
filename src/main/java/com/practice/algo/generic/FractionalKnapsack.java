package com.practice.algo.generic;

import java.util.*;

class Item {
    int value;
    int weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class FractionalKnapsack {

    public static double getMaxValue(int capacity, Item[] items) {

        // Sort items by value/weight ratio descending
        Arrays.sort(items, (a, b) -> {
            double r1 = (double) a.value / a.weight;
            double r2 = (double) b.value / b.weight;
            return Double.compare(r2, r1);
        });

        double totalValue = 0.0;
        int remainingCapacity = capacity;

        for (Item item : items) {

            if (remainingCapacity == 0)
                break;

            if (item.weight <= remainingCapacity) {
                // Take full item
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                // Take fraction
                double fraction = (double) remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                remainingCapacity = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {

        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };

        int capacity = 50;

        double maxValue = getMaxValue(capacity, items);

        System.out.println("Maximum value: " + maxValue);
    }
}
