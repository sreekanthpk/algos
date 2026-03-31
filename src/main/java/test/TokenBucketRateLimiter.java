package test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Lock-free Token Bucket Rate Limiter.
 *
 * Tokens refill continuously at {@code refillRatePerSec} per second,
 * up to {@code capacity}. Each request consumes one token.
 *
 * Uses two AtomicLongs — one for the token count, one for the last
 * refill timestamp. Refill is computed lazily on each call.
 */
public class TokenBucketRateLimiter {

    private final long capacity;          // max tokens
    private final long refillRatePerSec;  // tokens added per second

    private final AtomicLong tokens;      // current token count
    private final AtomicLong lastRefill;  // last refill time (epoch ms)

    public TokenBucketRateLimiter(long capacity, long refillRatePerSec) {
        if (capacity <= 0)         throw new IllegalArgumentException("capacity must be > 0");
        if (refillRatePerSec <= 0) throw new IllegalArgumentException("refillRatePerSec must be > 0");

        this.capacity         = capacity;
        this.refillRatePerSec = refillRatePerSec;
        this.tokens           = new AtomicLong(capacity);  // start full
        this.lastRefill       = new AtomicLong(System.currentTimeMillis());
    }

    /**
     * Returns {@code true} if a token was consumed, {@code false} if the bucket is empty.
     */
    public boolean check() {
        refill();

        // CAS loop: decrement token count only if > 0
        while (true) {
            long current = tokens.get();
            if (current <= 0) {
                return false;
            }
            if (tokens.compareAndSet(current, current - 1)) {
                return true;
            }
        }
    }

    /**
     * Current token count. May be slightly stale.
     */
    public long availableTokens() {
        refill();
        return tokens.get();
    }

    /**
     * Lazily adds tokens based on elapsed time since last refill.
     */
    private void refill() {
        long now  = System.currentTimeMillis();
        long last = lastRefill.get();
        long elapsedMs = now - last;

        if (elapsedMs <= 0) return;

        long newTokens = elapsedMs * refillRatePerSec / 1_000;
        if (newTokens <= 0) return;

        // Only one thread should do the refill for this interval
        if (lastRefill.compareAndSet(last, now)) {
            long updated = Math.min(capacity, tokens.get() + newTokens);
            tokens.set(updated);
        }
    }

    // ── Example usage ─────────────────────────────────────────────────────

    public static void main(String[] args) throws InterruptedException {
        // Capacity of 5, refills at 2 tokens/sec
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, 2);

        System.out.println("── Burst test (drain full bucket) ──");
        for (int i = 1; i <= 7; i++) {
            boolean ok = limiter.check();
            System.out.printf("Request %d: %s | available=%d%n",
                    i, ok ? "allowed" : "limited", limiter.availableTokens());
        }

        System.out.println("\nWaiting 2 seconds (expect ~4 new tokens)...");
        Thread.sleep(2_000);

        System.out.println("\n── After refill ──");
        for (int i = 1; i <= 6; i++) {
            boolean ok = limiter.check();
            System.out.printf("Request %d: %s | available=%d%n",
                    i, ok ? "allowed" : "limited", limiter.availableTokens());
        }
    }
}