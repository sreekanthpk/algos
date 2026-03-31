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
public class TokenBucketRateLimiter1 {
    private final long capacity;
    private final long tokensPerSecond;

    private AtomicLong lastRefill;
    private AtomicLong tokens;

    public TokenBucketRateLimiter1(long capacity, long tokensPerSecond) {
        this.capacity = capacity;
        this.tokensPerSecond = tokensPerSecond;

        this.lastRefill = new AtomicLong(System.currentTimeMillis());
        this.tokens = new AtomicLong(capacity);
    }

    // ── Example usage ─────────────────────────────────────────────────────

    public static void main(String[] args) throws InterruptedException {
        // Capacity of 5, refills at 2 tokens/sec
        TokenBucketRateLimiter1 limiter = new TokenBucketRateLimiter1(5, 2);

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

    private long availableTokens() {
        refill();
        return tokens.get();
    }

    private boolean check() {
        refill();

        while (true) {
            long current = tokens.get();
            if(current <= 0) return false;

            if(tokens.compareAndSet(current, current - 1)) return true;
        }
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long last = lastRefill.get();
        long timeLapse = now - last;
        if(timeLapse <= 0 ) return;
        long newTokens = (timeLapse* tokensPerSecond)/1_000;
        if(newTokens<=0) return ;

        if(lastRefill.compareAndSet(last, now)){
            long updated = Math.min(capacity, tokens.get() + newTokens);
            tokens.set(updated);return;
        }
    }
}