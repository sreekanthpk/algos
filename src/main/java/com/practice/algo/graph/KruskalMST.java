package com.practice.algo.graph;

import java.util.*;

class Edge implements Comparable<Edge> {
    int source, target, weight;

    Edge(int source, int target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]); // Path compression
        }
        return parent[u];
    }

    void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}

public class KruskalMST {

    public static int kruskal(int numNodes, List<Edge> edges) {
        // Step 1: Sort edges by weight
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(numNodes);
        int mstWeight = 0;

        // Step 2: Go through edges and add to MST if no cycle is formed
        for (Edge edge : edges) {
            int rootU = ds.find(edge.source);
            int rootV = ds.find(edge.target);

            if (rootU != rootV) {
                mstWeight += edge.weight;
                ds.union(rootU, rootV);
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int numNodes = 6; // Let's say we have 6 branches (nodes: A=0, B=1, C=2, D=3, E=4, F=5)

        List<Edge> edges = new ArrayList<>();
        // Adding edges (source, target, weight)
        edges.add(new Edge(0, 1, 4));  // A - B (4)
        edges.add(new Edge(0, 2, 2));  // A - C (2)
        edges.add(new Edge(1, 2, 5));  // B - C (5)
        edges.add(new Edge(1, 3, 10)); // B - D (10)
        edges.add(new Edge(2, 3, 3));  // C - D (3)
        edges.add(new Edge(3, 4, 4));  // D - E (4)
        edges.add(new Edge(4, 5, 2));  // E - F (2)
        edges.add(new Edge(2, 5, 6));  // C - F (6)

        int totalWeight = kruskal(numNodes, edges);
        System.out.println("Minimum Spanning Tree total weight: " + totalWeight);
    }
}
