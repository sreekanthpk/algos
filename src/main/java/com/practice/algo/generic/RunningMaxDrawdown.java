package com.practice.algo.generic;

/**
 * This is the progrm where you realize how much away your stock price is from the peak
 */
public class RunningMaxDrawdown {

    private double peak;
    private double maxDrawdown;
    private boolean firstPrice = true;

    // Call this for each new price
    public void addPrice(double price) {
        if (firstPrice) {
            peak = price;
            maxDrawdown = 0.0;
            firstPrice = false;
            return;
        }

        if (price > peak) {
            peak = price;
        }

        double drawdown = peak - price;
        if (drawdown > maxDrawdown) {
            maxDrawdown = drawdown;
        }
    }

    public double getMaxDrawdown() {
        return maxDrawdown;
    }

    public static void main(String[] args) {
        RunningMaxDrawdown rmd = new RunningMaxDrawdown();

        double[] prices = {100, 120, 110, 150, 130, 80, 90};
        for (double price : prices) {
            rmd.addPrice(price);
            System.out.println("Current Max Drawdown: " + rmd.getMaxDrawdown());
        }
    }
}