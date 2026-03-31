package com.practice.algo.graph;

import java.util.*;


public class BestFXRate1 {

    public static class Result {
        double rate;
        List<String> path;
        Result(double r, List<String> p) { rate = r; path = p; }
    }

    public static Result bestRate(Map<String, List<Edge1>> graph, String src, String dst) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        int n = graph.size();

        for(String node : graph.keySet()) dist.put(node, Double.POSITIVE_INFINITY);
        dist.put(src, 0.0);

        for(int i=0;i<n-1;i++){
            for(String node : graph.keySet()){
                for(Edge1 edge : graph.get(node)){
                    if(dist.get(node)+ edge.weight < dist.get(edge.to)){
                        dist.put(edge.to,dist.get(node)+ edge.weight );
                        parent.put(node, edge.to);
                    }
                }
            }
        }

        List<String> path = new ArrayList<>();
        String curr = dst;
        for(int i=0; i<n-1 && curr != null ; i++){
            curr = parent.get(curr);
        }
        Collections.reverse(path);

        return new Result(Math.exp(-dist.get(dst)), path);
    }

    public static void main(String[] args) {

        Map<String, List<Edge1>> graph = new HashMap<>();
        graph.put("USD", List.of(new Edge1("USD", "EUR", -Math.log(0.9)), new Edge1("USD", "GBP", -Math.log(0.7)), new Edge1("USD", "JPY", -Math.log(110.0))));
        graph.put("EUR", List.of(new Edge1("EUR", "GBP", -Math.log(0.8)), new Edge1("EUR", "JPY", -Math.log(130.0))));
        graph.put("GBP", List.of()); // no outgoing edges
        graph.put("JPY", List.of());

        Result res = bestRate(graph, "USD", "JPY");
        System.out.println("Best Rate USD→JPY: " + res.rate);
        System.out.println("Path: " + res.path);
    }
}