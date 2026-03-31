package com.practice.algo.generic;

import java.util.PriorityQueue;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // PriorityQueue with comparator for ListNode based on node value
        PriorityQueue<ListNode> heap = new PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        // Step 1: Add head of each list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }

        // Dummy node to simplify merging
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Step 2: Pop smallest, push next node if exists
        while (!heap.isEmpty()) {
            ListNode node = heap.poll(); // smallest node
            current.next = node;
            current = current.next;

            if (node.next != null) {
                heap.offer(node.next);
            }
        }

        return dummy.next;
    }

    // Optional helper to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    // Example usage
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = new ListNode[]{l1, l2, l3};
        MergeKSortedLists solution = new MergeKSortedLists();
        ListNode merged = solution.mergeKLists(lists);
        printList(merged);
    }
}
