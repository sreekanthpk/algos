package com.practice.algo.heap;

import java.util.*;

class Node {
    char ch;       // Character (for leaves)
    int freq;      // Frequency of the character
    Node left, right; // Left and right children

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }
}

// Comparator to order the min-heap by frequency
class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return a.freq - b.freq;
    }
}

public class HuffmanCoding {

    // Build the Huffman tree
    public static Node buildHuffmanTree(char[] chars, int[] freqs) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        // Step 1: Create leaf nodes for each character and add to the priority queue
        for (int i = 0; i < chars.length; i++) {
            pq.add(new Node(chars[i], freqs[i]));
        }

        // Step 2: Combine nodes until only one tree remains
        while (pq.size() > 1) {
            Node left = pq.poll();  // Smallest frequency
            Node right = pq.poll(); // Next smallest frequency
            Node parent = new Node('*', left.freq + right.freq); // Internal node
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        // The remaining node is the root of the Huffman tree
        return pq.poll();
    }

    // Recursively generate codes
    public static void generateCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) return;

        // If it's a leaf node, store the code
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
        }

        // Traverse left and right
        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }

    public static void main(String[] args) {
        // Example characters and their frequencies
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freqs = {5, 9, 12, 13, 16, 45};

        // Build Huffman Tree
        Node root = buildHuffmanTree(chars, freqs);

        // Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        // Print the codes
        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
