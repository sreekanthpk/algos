package com.practice.algo.tree;

public class KthSmallestElement {

    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        count = 0;        // reset for safety
        result = -1;

        inorder(root, k);

        if (result == -1) {
            throw new IllegalArgumentException("k is larger than number of nodes");
        }

        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) return;

        // Go left
        inorder(node.left, k);

        // If already found, stop further work
        if (count >= k) return;

        // Visit current node
        count++;

        if (count == k) {
            result = node.val;
            return;
        }

        // Go right
        inorder(node.right, k);
    }


}
