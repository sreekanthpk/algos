package com.practice.algo.hash;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache1<K,V> {

    Map<K,V> cache;

    public LRUCache1(int capacity) {
        cache = new LinkedHashMap<K,V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) { return capacity < this.size(); }
        };
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

}
