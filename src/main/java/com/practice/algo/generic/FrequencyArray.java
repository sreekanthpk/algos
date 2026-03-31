package com.practice.algo.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrequencyArray {

    public int[] frequencySort(int[] nums) {
        Map<Integer,Integer> freq = new HashMap<>();

        for(int num:nums){
            freq.put(num, freq.getOrDefault(num,0)+1);
        }

        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arr, (a, b) -> {
            int fa = freq.get(a);
            int fb = freq.get(b);

            if (fa == fb) {
                return b - a;   // larger number first
            }
            return fa - fb;     // smaller frequency first
        });

        return Arrays.stream(arr).mapToInt(i -> i).toArray();
    }
}
