package com.practice.algo.generic;

import java.util.*;

public class RandomizedSet {
    private List<Integer> list;            // to store elements
    private Map<Integer, Integer> map;     // value -> index in list
    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int idx = map.get(val);              // index of element to remove
        int lastElement = list.get(list.size() - 1);

        // Move last element to the idx of element to remove
        list.set(idx, lastElement);
        map.put(lastElement, idx);

        // Remove last element
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomIndex = rand.nextInt(list.size());
        return list.get(randomIndex);
    }
}