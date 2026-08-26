package com.practice.algo.multithreading;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class LRUCache<K, V> {
    private final int capacity;
    private final ConcurrentHashMap<K, V> cache;
    private final ConcurrentLinkedDeque<K> accessOrder;
    private final ReentrantLock lock = new ReentrantLock();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new ConcurrentHashMap<>();
        this.accessOrder = new ConcurrentLinkedDeque<>();
    }

    public V get(K key) {
        lock.lock();
        try {
            V value = cache.get(key);
            if (value != null) {
                // Move key to the front (most recently used)
                accessOrder.remove(key);
                accessOrder.addFirst(key);
            }
            return value;
        } finally {
            lock.unlock();
        }
    }


    public void put(K key, V value) {
        lock.lock();
        try {
            if (cache.containsKey(key)) {
                cache.put(key, value); // Update the value
                accessOrder.remove(key);
                accessOrder.addFirst(key); // Update access order
            } else {
                if (cache.size() >= capacity) {
                    K lruKey = accessOrder.pollLast(); // Remove least recently used
                    if (lruKey != null) {
                        cache.remove(lruKey);
                    }
                }
                cache.put(key, value); // Add new key-value pair
                accessOrder.addFirst(key); // Mark as most recently used
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LRUCache<String, String> cache = new LRUCache<>(3);
        try (ExecutorService executor = newFixedThreadPool(5)) {

            // Simulate concurrent access
            for (int i = 1; i <= 5; i++) {
                int threadId = i;
                executor.submit(() -> {
                    String key = "key" + threadId;
                    cache.put(key, "value" + threadId);
                    System.out.println("Thread-" + threadId + " put " + key);

                    // Access cache
                    for (int j = 1; j <= 5; j++) {
                        String k = "key" + j;
                        String value = cache.get(k);
                        System.out.println("Thread-" + threadId + " got " + k + ": " + value);
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }
    }
}