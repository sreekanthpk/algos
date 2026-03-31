package com.practice.algo.tree;

class QNode {
    public boolean val;
    public boolean isLeaf;
    public QNode topLeft;
    public QNode topRight;
    public QNode bottomLeft;
    public QNode bottomRight;


    public QNode() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public QNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public QNode(boolean val, boolean isLeaf, QNode topLeft, QNode topRight, QNode bottomLeft, QNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}


public class QuadTree {

    public QNode construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    private QNode build(int[][] grid, int r, int c, int size) {
        // Base case: 1x1 cell
        if (size == 1)
            return new QNode(grid[r][c] == 1, true);

        int half = size / 2;

        QNode topLeft     = build(grid, r,        c,        half);
        QNode topRight    = build(grid, r,        c + half, half);
        QNode bottomLeft  = build(grid, r + half, c,        half);
        QNode bottomRight = build(grid, r + half, c + half, half);

        // Collapse if all four children are identical leaves
        if (topLeft.isLeaf && topRight.isLeaf &&
                bottomLeft.isLeaf && bottomRight.isLeaf &&
                topLeft.val == topRight.val &&
                topLeft.val == bottomLeft.val &&
                topLeft.val == bottomRight.val) {
            return new QNode(topLeft.val, true);
        }

        return new QNode(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
