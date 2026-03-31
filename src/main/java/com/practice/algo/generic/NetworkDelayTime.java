package com.practice.algo.generic;

import java.util.*;

public class NetworkDelayTime {

    static class Edge {
        int target;
        int weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        // Build graph: adjacency list
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { // 1-based indexing
            graph.add(new ArrayList<>());
        }
        for (int[] t : times) {
            int u = t[0], v = t[1], w = t[2];
            graph.get(u).add(new Edge(v, w));
        }

        // Dijkstra's algorithm using PriorityQueue
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k}); // {time, node}
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0];
            int node = curr[1];

            if (time > dist[node]) continue; // already found a better path

            for (Edge edge : graph.get(node)) {
                int next = edge.target;
                int newDist = time + edge.weight;
                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    pq.offer(new int[]{newDist, next});
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1; // unreachable
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }

    public static void main(String[] args) {
        NetworkDelayTime solver = new NetworkDelayTime();
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4, k = 2;
        System.out.println(solver.networkDelayTime(times, n, k)); // Output: 2
    }
}
