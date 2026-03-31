package com.practice.algo.generic;

public class MaximumXOR {

    static class TrieNode {
        TrieNode[] children = new TrieNode[2]; // 0 and 1
    }

    public static int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        int max = 0;

        // Build the Trie
        for (int num : nums) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new TrieNode();
                }
                node = node.children[bit];
            }
        }

        // Find max XOR
        for (int num : nums) {
            TrieNode node = root;
            int xor = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                // Prefer opposite bit
                if (node.children[1 - bit] != null) {
                    xor |= (1 << i);
                    node = node.children[1 - bit];
                } else {
                    node = node.children[bit];
                }
            }
            max = Math.max(max, xor);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 10, 5, 25, 2, 8};
        System.out.println(findMaximumXOR(nums1)); // Output: 28

        int[] nums2 = {0, 2};
        System.out.println(findMaximumXOR(nums2)); // Output: 2
    }
}
