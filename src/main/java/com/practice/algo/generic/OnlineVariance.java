package com.practice.algo.generic;

public class OnlineVariance {
    private long count = 0;
    private double mean = 0.0;
    private double m2 = 0.0; // sum of squares of differences from the mean

    // Call this for each new number from the stream
    public void add(double x) {
        count++;
        double delta = x - mean;
        mean += delta / count;
        double delta2 = x - mean;
        m2 += delta * delta2;
    }

    // Returns the sample variance
    public double getVariance() {
        if (count < 2) {
            return 0.0; // not enough data
        }
        return m2 / (count - 1);
    }

    // Returns the current mean
    public double getMean() {
        return mean;
    }

    public static void main(String[] args) {
        OnlineVariance ov = new OnlineVariance();
        double[] stream = {4, 7, 13, 16}; // example stream

        for (double num : stream) {
            ov.add(num);
            System.out.println("Added: " + num + ", Mean: " + ov.getMean() + ", Variance: " + ov.getVariance());
        }
    }
}