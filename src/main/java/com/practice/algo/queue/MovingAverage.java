package com.practice.algo.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {
    private final int windowSize;
    private final Deque<Double> window;
    private double sum;

    public MovingAverage(int k) {
        this.windowSize = k;
        this.window = new ArrayDeque<>();
        this.sum = 0.0;
    }

   public double add(double x) {
        sum += x;
        window.addLast(x);
        if (window.size() > windowSize) {
            sum -= window.removeFirst();
        }
        if(window.size() < windowSize) { return 0.0;}
        return sum/windowSize;
   }

    public static void main(String[] args) {
        // Create a moving average with window size 3
        MovingAverage ma = new MovingAverage(3);

        // Test cases
        System.out.println("Adding 1: " + ma.add(1)); // 0.0, window not full
        System.out.println("Adding 10: " + ma.add(10)); // 0.0, window not full
        System.out.println("Adding 3: " + ma.add(3)); // (1+10+3)/3 = 4.666...
        System.out.println("Adding 5: " + ma.add(5)); // (10+3+5)/3 = 6.0
        System.out.println("Adding 8: " + ma.add(8)); // (3+5+8)/3 = 5.333...
        System.out.println("Adding 2: " + ma.add(2)); // (5+8+2)/3 = 5.0

        // Additional test: window size 1
        MovingAverage ma1 = new MovingAverage(1);
        System.out.println("\nWindow size 1:");
        System.out.println("Adding 7: " + ma1.add(7)); // 7.0
        System.out.println("Adding 9: " + ma1.add(9)); // 9.0

        // Additional test: window size 2
        MovingAverage ma2 = new MovingAverage(2);
        System.out.println("\nWindow size 2:");
        System.out.println("Adding 4: " + ma2.add(4)); // 0.0
        System.out.println("Adding 6: " + ma2.add(6)); // (4+6)/2 = 5.0
        System.out.println("Adding 8: " + ma2.add(8)); // (6+8)/2 = 7.0
    }
}
