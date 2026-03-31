package com.practice.algo.hash;

class Entry<K,V>{
    K key;
    V value;
    Entry<K,V> next;

    public Entry(K k, V v) {
        this.key = k;
        this.value = v;
    }
}

public class MyHashMap <K,V>{
    Entry<K,V>[] table;
    private int capacity = 16; // default size
    private int size = 0;

    public MyHashMap(){
       table = new Entry[capacity];
    }

    public void put(K k, V v){
        int  index = getIndex(k);
        table[index] = new Entry<>(k, v);
        size++;
    }

    private int getIndex(K k) {
        return k.hashCode()&(capacity-1);
    }

    public V get(K key){
        return null;
    }

    public V remove(K key){
        return null;
    }


    public static void main(String... args){
        MyHashMap<Integer, String> map =  new MyHashMap();
        for(int i = 0; i < 17; i++){
            map.put(i,"world");
        }

    }
}
