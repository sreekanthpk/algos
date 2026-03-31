package com.practice.algo.hash;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache <K, V>{


    final Map<K,V> cache;
    private final int capacity;

    public LRUCache(int capacity){

        this.cache = new LinkedHashMap<K, V>(capacity, 0.75f, true)
        {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
        this.capacity = capacity;
    }

    public V get(K key){
        return cache.get(key);
    }


    public void put(K key, V value){
        cache.put(key,value);

    }


    public static void main(String[] args) {
        LRUCache<Integer,Integer> lruCache = new LRUCache<>(4);

        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        lruCache.put(5, 5);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        lruCache.put(6, 6);
        System.out.println(lruCache.get(3));
    }

}
