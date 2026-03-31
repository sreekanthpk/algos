package com.practice.algo.generic;

import java.util.concurrent.atomic.AtomicLong;

public class LockFreeTokenBucket {

    private final long capacity;
    private final long nanosPerToken;

    private final AtomicLong state = new AtomicLong(0L);

    public LockFreeTokenBucket(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.nanosPerToken = 1_000_000_000L / refillRatePerSecond;

        long now = System.nanoTime();
        state.set(pack(now, capacity));
    }

    public boolean tryAcquire() {
        while (true) {
            long now = System.nanoTime();
            long current = state.get();

            long lastRefill = extractLastRefill(current);
            long tokens = extractTokens(current);

            long newTokens = tokens + (now - lastRefill) / nanosPerToken;
            if (newTokens > capacity) {
                newTokens = capacity;
            }

            if (newTokens == 0) {
                return false;
            }

            long newLastRefill =
                    lastRefill + ((newTokens - tokens) * nanosPerToken);

            long newState = pack(newLastRefill, newTokens - 1);

            if (state.compareAndSet(current, newState)) {
                return true;
            }
        }
    }

    private long pack(long lastRefillTime, long tokens) {
        return (lastRefillTime << 32) | tokens;
    }

    private long extractLastRefill(long state) {
        return state >>> 32;
    }

    private long extractTokens(long state) {
        return state & 0xFFFFFFFFL;
    }
}
