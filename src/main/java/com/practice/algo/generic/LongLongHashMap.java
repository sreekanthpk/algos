package com.practice.algo.generic;

public class LongLongHashMap {

    private static final float LOAD_FACTOR = 0.6f;

    private long[] keys;
    private long[] values;
    private boolean[] used;

    private int size;
    private int capacity;
    private int threshold;

    public LongLongHashMap(int initialCapacity) {
        capacity = nextPowerOfTwo(initialCapacity);
        threshold = (int) (capacity * LOAD_FACTOR);

        keys = new long[capacity];
        values = new long[capacity];
        used = new boolean[capacity];
    }

    public LongLongHashMap() {
        this(16);
    }

    /* ---------------- public API ---------------- */

    public void put(long key, long value) {
        if (size >= threshold) {
            rehash(capacity << 1);
        }

        int idx = findSlot(key);
        if (!used[idx]) {
            used[idx] = true;
            keys[idx] = key;
            size++;
        }
        values[idx] = value;
    }

    public long get(long key) {
        int idx = findSlot(key);
        return used[idx] ? values[idx] : -1L;
    }

    public boolean containsKey(long key) {
        int idx = findSlot(key);
        return used[idx];
    }

    public int size() {
        return size;
    }

    /* ---------------- internals ---------------- */

    private int findSlot(long key) {
        int mask = capacity - 1;
        int idx = hash(key) & mask;

        while (used[idx]) {
            if (keys[idx] == key) {
                return idx;
            }
            idx = (idx + 1) & mask; // linear probe
        }
        return idx;
    }

    private void rehash(int newCapacity) {
        long[] oldKeys = keys;
        long[] oldValues = values;
        boolean[] oldUsed = used;

        capacity = newCapacity;
        threshold = (int) (capacity * LOAD_FACTOR);

        keys = new long[capacity];
        values = new long[capacity];
        used = new boolean[capacity];

        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldUsed[i]) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    private static int hash(long key) {
        key ^= (key >>> 33);
        key *= 0xff51afd7ed558ccdL;
        key ^= (key >>> 33);
        return (int) key;
    }

    private static int nextPowerOfTwo(int n) {
        return 1 << (32 - Integer.numberOfLeadingZeros(n - 1));
    }
}
