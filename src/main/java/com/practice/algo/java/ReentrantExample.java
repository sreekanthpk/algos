package com.practice.algo.java;

public class ReentrantExample {
    public synchronized void outerMethod() {
        System.out.println("In outer method");
        innerMethod(); // Re-entering lock in the same thread
    }

    public synchronized void innerMethod() {
        System.out.println("In inner method");
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }
}