package com.practice.algo.sort;

public class MergeSort {

    // Main function to sort an array
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left >= right) return; // Base case: 1 element

        int mid = left + (right - left) / 2;

        // Step 1: Sort left half
        mergeSortHelper(arr, left, mid);

        // Step 2: Sort right half
        mergeSortHelper(arr, mid + 1, right);

        // Step 3: Merge sorted halves
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++) rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Merge the temp arrays back into arr[left..right]
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements if any
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    // Optional: Test the merge sort
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Before sorting:");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();

        mergeSort(arr);

        System.out.println("After sorting:");
        for (int num : arr) System.out.print(num + " ");
    }
}
