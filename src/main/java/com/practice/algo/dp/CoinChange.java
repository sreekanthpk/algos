package com.practice.algo.dp;
//How many different combinations of coins can make up a given amount
public class CoinChange {

        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1; // base case

            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    dp[i] += dp[i - coin];
                }
            }

            return dp[amount];
        }

        public static void main(String... args){
            int amount = 5;
            int[] coins = {1, 2, 5};

            CoinChange cc = new CoinChange();
            System.out.println(cc.change(amount, coins));
        }
}
