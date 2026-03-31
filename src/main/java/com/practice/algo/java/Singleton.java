package com.practice.algo.java;

public class Singleton {
    // Private constructor to prevent instantiation
    private Singleton() {}

    // Inner static class - the holder
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
