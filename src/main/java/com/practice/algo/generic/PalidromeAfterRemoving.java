package com.practice.algo.generic;

public class PalidromeAfterRemoving {

        public boolean validPalindrome(String s) {
            int left = 0, right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return check(s, left + 1, right) ||
                            check(s, left, right - 1);
                }
                left++;
                right--;
            }
            return true;
        }

        private boolean check(String s, int l, int r) {
            while (l < r) {
                if (s.charAt(l++) != s.charAt(r--))
                    return false;
            }
            return true;
        }
}
