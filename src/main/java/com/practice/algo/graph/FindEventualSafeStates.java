package com.practice.algo.graph;

import java.util.*;

/**
 * Find Eventual Safe States:
 * Identifying nodes in a
 * graph that do not lead to a cycle.
 */
public class FindEventualSafeStates {

    // 0 = unvisited, 1 = visiting, 2 = safe
    private int[] state;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        state = new int[n];
        List<Integer> safeNodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (isSafe(i, graph)) {
                safeNodes.add(i);
            }
        }

        return safeNodes; // already in ascending order by construction
    }

    // Returns true if node 'u' is safe (does not lead to a cycle)
    private boolean isSafe(int u, int[][] graph) {
        if (state[u] != 0) {
            // If already marked safe (2), safe; otherwise visiting (1) means cycle
            return state[u] == 2;
        }

        // Mark as visiting
        state[u] = 1;

        for (int v : graph[u]) {
            // If neighbor is not safe, this node is not safe
            if (!isSafe(v, graph)) {
                return false;
            }
        }

        // Mark this node as safe
        state[u] = 2;
        return true;
    }

    public static void main(String[] args) {
        FindEventualSafeStates sol = new FindEventualSafeStates();
        int[][] graph = {
                {1,2},
                {2,3},
                {5},
                {0},
                {5},
                {},
                {}
        };
        System.out.println(sol.eventualSafeNodes(graph)); // Output: [2, 4, 5, 6]
    }
}
