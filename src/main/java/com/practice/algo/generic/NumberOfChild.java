package com.practice.algo.generic;

public class NumberOfChild {
    public int numberOfChild(int n, int k) {
        int round = k / (n - 1);
        int pos = k % (n - 1);

        if (round % 2 == 0) {
            return pos;
        } else {
            return (n - 1) - pos;
        }

    }
}
