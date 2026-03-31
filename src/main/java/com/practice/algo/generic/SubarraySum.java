package com.practice.algo.generic;

/**
 *  Sub array with sum k
 */
public class SubarraySum {

    int subArraySum(int arr[], int n, int sum)
    {
        int currentSum = arr[0];
        int start = 0;
        int i;

        // Pick a starting point
        for (i = 1; i <= n; i++) {
            // If currentSum exceeds the sum,
            // then remove the starting elementsstart
            while (currentSum > sum && start < i - 1) {
                currentSum = currentSum - arr[start];
                start++;
                System.out.println(" currentSum" + currentSum);
                System.out.println(" start" + start);
                System.out.println(" i" + i);
            }

            // If currentSum becomes equal to sum,
            // then return true
            if (currentSum == sum) {
                int p = i - 1;
                System.out.println(
                        "Sum found between indexes " + start
                                + " and " + p);
                return 1;
            }

            // Add this element to curr_sum
            if (i < n)
                currentSum = currentSum + arr[i];
        }

        System.out.println("No subarray found");
        return 0;
    }

    public static void main(String[] args)
    {
        SubarraySum arraysum = new SubarraySum();
        int arr[] = { 15, 2, 4, 8, 9, 5, 10, 23 };
        int n = arr.length;
        int sum = 23;
        arraysum.subArraySum(arr, n, sum);
    }
}
