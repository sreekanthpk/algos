package com.practice.algo.generic;

import java.util.*;

/**
 * Java program to print the K most frequent numbers in an array
 */
public class KFrequentNumbers {
    static void print_N_mostFrequentNumber(int[] arr, int N,
                                           int K)
    {

        Map<Integer, Integer> mp
                = new HashMap<Integer, Integer>();

        for (int i = 0; i < N; i++) {

            mp.put(arr[i], mp.getOrDefault(arr[i], 0) + 1);
        }

        List<Map.Entry<Integer, Integer> > list
                = new ArrayList<Map.Entry<Integer, Integer> >(
                mp.entrySet());

        Collections.sort(
                list,
                new Comparator<Map.Entry<Integer, Integer> >() {
                    public int compare(
                            Map.Entry<Integer, Integer> o1,
                            Map.Entry<Integer, Integer> o2)
                    {
                        if (o1.getValue() == o2.getValue())
                            return o2.getKey() - o1.getKey();
                        else
                            return o2.getValue()
                                    - o1.getValue();
                    }
                });

        for (int i = 0; i < K; i++)
            System.out.print(list.get(i).getKey() + " ");
    }

    public static void main(String[] args)
    {
        int arr[] = { 3, 1, 4, 4, 5, 2, 6, 1 };
        int N = arr.length;
        int K = 2;

        print_N_mostFrequentNumber(arr, N, K);
    }
}