package com.practice.algo.list;

import java.util.*;

public class RemoveDuplicateFromLinkedList {



    public static void main(String[] args) {
        List<Integer> l   = new LinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(2);
        l.add(3);

        HashSet<Integer> h = new HashSet<>();
        List<Integer> l2   = new LinkedList<>();

        for (Integer i : l) {

            if(!h.contains(i)){
                l2.add(i);
                h.add(i);
            }
        }

        System.out.println(l2);
    }
}
