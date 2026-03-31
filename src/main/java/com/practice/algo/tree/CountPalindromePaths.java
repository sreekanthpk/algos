package com.practice.algo.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPalindromePaths {
    public long countPalindromePaths(int[] parent, String s) {
        int n = parent.length;

        List<int[]>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for(int i = 1; i < n; i++) {
            int p = parent[i];
            int bit = s.charAt(i) - 'a';
            graph[p].add(new int[]{i, bit});
        }

        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        return dfs(0, 0, graph, count);
    }

    private long dfs(int node, int mask, List<int[]>[] graph, Map<Integer,Integer> count) {
        long res = 0;

        for(int[] next : graph[node]) {
            int child = next[0];
            int bit = next[1];

            int newMask = mask ^ (1 << bit);

            res += count.getOrDefault(newMask, 0);

            for(int i = 0; i < 26; i++) {
                res += count.getOrDefault(newMask ^ (1 << i), 0);
            }

            count.put(newMask, count.getOrDefault(newMask,0) + 1);

            res += dfs(child, newMask, graph, count);

            count.put(newMask, count.get(newMask) - 1);
        }

        return res;
    }
}