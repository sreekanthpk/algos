package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> {

    private LinkedHashMap<K, V> cache;


    public LRUCache(int cacheSize) {
        this.cache = new LinkedHashMap<K, V>(cacheSize, 0.75F, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> entry){
                return this.size() > cacheSize;
            }
        };
    }

    public void put(K k, V v){
        cache.put(k,v);
    };

    public V get(K k){
        return cache.get(k);
    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

}
