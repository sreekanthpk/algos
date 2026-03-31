package com.practice.algo.heap;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue1 {

    private List<Integer> heap = new ArrayList<>();


    public void add(int val) {
        heap.add(val);
        heapifyUp(heap.size()-1);
    }

    // Remove min
    public int poll() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Empty queue");
        }

        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return root;
    }

    // Peek
    public int peek() {
        if (heap.isEmpty()) {
            throw new RuntimeException("Empty queue");
        }
        return heap.get(0);
    }

    private void heapifyDown(int index) {

    }



    private void heapifyUp(int index) {

    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

}
