package com.practice.algo.generic;



public class NumberSwapper {

    static boolean flag;
    static int x;

    public static void main(String[] args) {
        int k = 100;
        int l = 120;
        if (flag) System.out.println(x);
        k=k+l;
        l=k-l;
        k=k-l;
        System.out.println(k);
        System.out.println(l);
    }
}
