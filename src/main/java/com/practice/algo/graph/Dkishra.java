package com.practice.algo.graph;

import java.util.*;

class Graph {
    private int numVertices;
    private List<List<Edge>> adjList;

    // Inner class to represent an edge
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int target, int weight) {
        adjList.get(source).add(new Edge(target, weight));
    }

    public int dijkstra(int start, int end) {
        int[] dist = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Initialize distances to infinity
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // Add the start node to the priority queue (node, distance)
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int currentDist = current[1];

            if (visited[node]) continue;

            visited[node] = true;

            // Explore neighbors
            for (Edge edge : adjList.get(node)) {
                int nextNode = edge.target;
                int newDist = currentDist + edge.weight;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }

        return dist[end];
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);  // 6 intersections: A=0, B=1, C=2, D=3, E=4, F=5

        // Adding roads (edges) with weights (travel times)
        graph.addEdge(0, 1, 4);  // A -> B
        graph.addEdge(0, 2, 2);  // A -> C
        graph.addEdge(1, 2, 5);  // B -> C
        graph.addEdge(1, 3, 10); // B -> D
        graph.addEdge(2, 3, 3);  // C -> D
        graph.addEdge(3, 4, 4);  // D -> E
        graph.addEdge(4, 5, 2);  // E -> F
        graph.addEdge(2, 5, 6);  // C -> F

        int shortestTime = graph.dijkstra(0, 5);  // From A to F
        System.out.println("Shortest time from A to F: " + shortestTime);
    }
}