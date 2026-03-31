package com.practice.algo.tree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
public class MaxDepthOfBinaryTree {

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    // Test cases
    public static void main(String[] args) {
        /*
              3
             / \
            9  20
               / \
              15  7
        */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxDepth(root)); // Output: 3

        TreeNode singleNode = new TreeNode(1);
        System.out.println(maxDepth(singleNode)); // Output: 1

        System.out.println(maxDepth(null)); // Output: 0
    }
}
