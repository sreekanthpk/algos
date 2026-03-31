package com.practice.algo.tree;

/**
 * Binary Tree traversal
 */

class Node
{
    int data;
    Node left, right;

    public Node(int data)
    {
        this.data = data;
        left = right = null;
    }
}
public class BinaryTree {
    private Node root;
    private Node head;
    static Node prev = null;

    void inOrderTraversal(Node root){
        if(root == null ) return;

        inOrderTraversal(root.left);
        System.out.println(root.data);
        inOrderTraversal(root.right);
    }

    void binaryTree2DoubleLinkedList(Node root)
    {
        if (root == null)
            return;

        binaryTree2DoubleLinkedList(root.left);

        if (prev == null)
            head = root;
        else
        {
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        binaryTree2DoubleLinkedList(root.right);
    }

    void printList(Node node)
    {
        while (node != null)
        {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }
    public static void main(String[] args)
    {
        // Let us create the tree as shown in above diagram
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);
        tree.inOrderTraversal(tree.root);
        // convert to DLL
        tree.binaryTree2DoubleLinkedList(tree.root);

        // Print the converted List
        tree.printList(tree.head);

    }


}
