package com.practice.algo.graph;

import java.util.*;
import static java.lang.Math.*;

/**
 * Find best FX rate
 */
public class FXBestRate {

    public record Edge(String to, double logWeight) {}
    private record Node(String symbol, double currentLogSum) {}
    public record Result(double bestRate, List<String> path) {}

    private final Map<String, List<Edge>> graph = new HashMap<>();

    public FXBestRate(Map<String, Double> exchangeRates) {
        for (var entry : exchangeRates.entrySet()) {
            var pair = entry.getKey().split("_");
            if (pair.length != 2) continue;

            // To maximize product, we minimize the sum of -log(rate)
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>())
                    .add(new Edge(pair[1], -log(entry.getValue())));        }
    }

    public Result bestRate(String src, String dst) {
        if (src.equals(dst)) return new Result(1.0, List.of(src));

        Map<String, Double> bestLogSum = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        // PriorityQueue must prioritize the SMALLEST log sum (which is the LARGEST rate)
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(Node::currentLogSum));

        bestLogSum.put(src, 0.0);
        pq.offer(new Node(src, 0.0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            String u = current.symbol();
            double d = current.currentLogSum();

            // Skip if we already found a path that results in a better rate
            if (d > bestLogSum.getOrDefault(u, Double.MAX_VALUE)) continue;

            for (Edge edge : graph.getOrDefault(u, Collections.emptyList())) {
                double newLogSum = d + edge.logWeight();

                if (newLogSum < bestLogSum.getOrDefault(edge.to(), Double.MAX_VALUE)) {
                    bestLogSum.put(edge.to(), newLogSum);
                    parent.put(edge.to(), u);
                    pq.offer(new Node(edge.to(), newLogSum));
                }
            }
        }

        if (!bestLogSum.containsKey(dst)) return null;

        // Reconstruct path
        LinkedList<String> path = new LinkedList<>();
        for (String at = dst; at != null; at = parent.get(at)) {
            path.addFirst(at);
        }

        return new Result(exp(-bestLogSum.get(dst)), path);
    }

    public static void main(String[] args) {
        // EXACT ORIGINAL INPUT
        var rates = Map.of(
                "USD_EUR", 0.9,
                "EUR_GBP", 0.8,
                "USD_GBP", 0.7,
                "EUR_JPY", 130.0,
                "USD_JPY", 110.0
        );

        var fx = new FXBestRate(rates);
        var result = fx.bestRate("USD", "JPY");

        if (result != null) {
            System.out.println("Best FX Path: " + String.join(" -> ", result.path()));
            System.out.printf("Best Rate: %.4f%n", result.bestRate());
        } else {
            System.out.println("No path found");
        }
    }
}