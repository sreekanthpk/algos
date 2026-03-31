package com.practice.algo.generic;

import java.util.*;

public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) return 0;

        // Map stop -> list of buses
        Map<Integer, List<Integer>> stopToBusMap = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToBusMap
                        .computeIfAbsent(stop, k -> new ArrayList<>())
                        .add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedBuses = new HashSet<>();

        // Add all buses available at source
        if (!stopToBusMap.containsKey(source)) return -1;

        for (int bus : stopToBusMap.get(source)) {
            queue.offer(bus);
            visitedBuses.add(bus);
        }

        int busesTaken = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currentBus = queue.poll();

                for (int stop : routes[currentBus]) {
                    if (stop == target) return busesTaken;

                    for (int nextBus : stopToBusMap.get(stop)) {
                        if (!visitedBuses.contains(nextBus)) {
                            visitedBuses.add(nextBus);
                            queue.offer(nextBus);
                        }
                    }
                }
            }

            busesTaken++;
        }

        return -1;
    }
}