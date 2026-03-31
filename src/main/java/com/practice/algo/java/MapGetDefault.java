package com.practice.algo.java;

import java.util.HashMap;
import java.util.Map;

public class MapGetDefault {

    public static void main(String... args){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(1, map.getOrDefault(1, 0) + 1);


    }
}
