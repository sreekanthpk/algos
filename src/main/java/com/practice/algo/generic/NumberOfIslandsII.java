package com.practice.algo.generic;

import java.util.*;

public class NumberOfIslandsII {

    class UnionFind {
        int[] parent;
        int[] rank;
        int count; // number of islands

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            Arrays.fill(parent, -1); // -1 means water
            count = 0;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false; // already connected

            // Union by rank
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            count--; // two islands merged
            return true;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m <= 0 || n <= 0) return result;

        UnionFind uf = new UnionFind(m * n);

        int[][] grid = new int[m][n]; // 0 = water, 1 = land
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            if (grid[r][c] == 1) { // duplicate land, skip
                result.add(uf.count);
                continue;
            }

            grid[r][c] = 1;
            int index = r * n + c;
            uf.parent[index] = index; // set as its own parent
            uf.count++; // new island

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                    uf.union(index, nr * n + nc);
                }
            }

            result.add(uf.count);
        }

        return result;
    }

    // Example Usage
    public static void main(String[] args) {
        NumberOfIslandsII solver = new NumberOfIslandsII();
        int m = 3, n = 3;
        int[][] positions = {{0,0},{0,1},{1,2},{2,1},{1,1}};
        System.out.println(solver.numIslands2(m, n, positions));
    }
}