package com.practice.algo.heap;

import java.util.List;
import java.util.ArrayList;
public class PriorityQueue2 {

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
        int size = heap.size();

        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < size && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }



    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;

            if (heap.get(parent) <= heap.get(index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

}
