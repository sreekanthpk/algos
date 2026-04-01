package com.practice.algo.graph;

public class NumberOfIslands {

    // Main method to count islands
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int count = 0; // Number of islands

        // Traverse each cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {  // Found land
                    count++;
                    dfs(grid, i, j);     // Mark all connected land
                }
            }
        }

        return count;
    }

    // Depth-first search to mark connected land as visited
    private void dfs(char[][] grid, int i, int j) {
        // Boundary check
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length
                || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // Mark visited

        // Explore neighbors (up, down, left, right)
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    // Example usage
    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        System.out.println("Number of islands: " + solver.numIslands(grid));
        // Output: 3
    }
}