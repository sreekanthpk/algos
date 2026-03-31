package com.practice.algo.generic;

import java.util.ArrayList;
import java.util.List;

public class NearestPaliandrome {
    public String nearestPalindromic(String n) {
        int length = n.length();
        int i = length % 2 == 0 ? length / 2 - 1 : length / 2;
        long firstHalf = Long.parseLong(n.substring(0, i + 1));
        List<Long> possibilities = new ArrayList();
        possibilities.add(halfToPalindrome(firstHalf, length % 2 == 0));
        possibilities.add(halfToPalindrome(firstHalf + 1, length % 2 == 0));
        possibilities.add(halfToPalindrome(firstHalf - 1, length % 2 == 0));
        possibilities.add((long) Math.pow(10, length - 1) - 1);
        possibilities.add((long) Math.pow(10, length) + 1);

        long diff = Long.MAX_VALUE, res = 0, nl = Long.parseLong(n);
        for (long cand : possibilities) {
            if (cand == nl) continue;
            if (Math.abs(cand - nl) < diff) {
                diff = Math.abs(cand - nl);
                res = cand;
            } else if (Math.abs(cand - nl) == diff) {
                res = Math.min(res, cand);
            }
        }
        return String.valueOf(res);
    }

    private long halfToPalindrome(long left, boolean even) {
        // Convert the given half to palindrome.
        long res = left;
        if (!even) left = left / 10;
        while (left > 0) {
            res = res * 10 + (left % 10);
            left /= 10;
        }
        return res;
    }

}
