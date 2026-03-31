package test;


import java.util.concurrent.atomic.AtomicLong;

/**
 * 1. token bucket (this one)
 * 2. Fixed window
 * 3. Slinding window
 * 4. dynamic window
 * 5. leaky bucket
 */

public class FancyTokenBucketRateLimiter {

    private static final long SCALE = 1_000L;

    private static final int  TOKEN_BITS  = 32;
    private static final long TOKEN_MASK  = 0xFFFFFFFFL;
    private static final long TIME_SHIFT  = TOKEN_BITS;

    private static long pack(long timestampMs, long scaledTokens) {
        return ((timestampMs & TOKEN_MASK) << TIME_SHIFT) | (scaledTokens & TOKEN_MASK);
    }

    private static long unpackTime(long packed)   { return (packed >>> TIME_SHIFT) & TOKEN_MASK; }
    private static long unpackTokens(long packed) { return packed & TOKEN_MASK; }


    private final long capacityScaled;       // capacity × SCALE
    private final long refillPerMsScaled;    // tokens added per ms × SCALE

    private final AtomicLong state;

    public FancyTokenBucketRateLimiter(int capacity, double refillRatePerSec) {
        if (capacity <= 0)          throw new IllegalArgumentException("capacity must be > 0");
        if (refillRatePerSec <= 0)  throw new IllegalArgumentException("refillRatePerSec must be > 0");

        this.capacityScaled    = capacity * SCALE;
        this.refillPerMsScaled = (long) (refillRatePerSec * SCALE / 1_000.0);

        // Start with a full bucket
        this.state = new AtomicLong(pack(currentMs(), capacityScaled));
    }

    // ── Public API ────────────────────────────────────────────────────────

    /**
     * Tries to consume 1 token. Returns {@code true} if allowed.
     */
    public boolean allow() {
        return allow(1);
    }

    /**
     * Tries to consume {@code tokens} tokens atomically.
     * Returns {@code true} if allowed, {@code false} if insufficient tokens.
     *
     * Lock-free: CAS retry loop; typically resolves in one iteration.
     */
    public boolean allow(int tokens) {
        if (tokens <= 0) throw new IllegalArgumentException("tokens must be > 0");
        long cost = (long) tokens * SCALE;

        while (true) {
            long current       = state.get();
            long now           = currentMs();
            long lastRefillMs  = unpackTime(current);
            long scaledTokens  = unpackTokens(current);

            // Compute elapsed, handling the 32-bit timestamp wrap (~49 days)
            long elapsedMs = (now - lastRefillMs) & TOKEN_MASK;

            // Refill tokens based on elapsed time, capped at capacity
            long refilled = Math.min(capacityScaled, scaledTokens + elapsedMs * refillPerMsScaled);

            if (refilled < cost) {
                return false; // not enough tokens
            }

            long next = pack(now, refilled - cost);
            if (state.compareAndSet(current, next)) {
                return true;
            }
            // CAS failed — another thread updated state; retry
        }
    }

    /**
     * Current token count (approximate — may be stale by the time you act on it).
     */
    public double availableTokens() {
        long current      = state.get();
        long now          = currentMs();
        long lastRefillMs = unpackTime(current);
        long scaledTokens = unpackTokens(current);
        long elapsedMs    = (now - lastRefillMs) & TOKEN_MASK;
        long refilled     = Math.min(capacityScaled, scaledTokens + elapsedMs * refillPerMsScaled);
        return (double) refilled / SCALE;
    }

    /**
     * Approximate milliseconds to wait before {@code tokens} tokens are available.
     * Returns 0 if tokens are already available.
     */
    public long retryAfterMillis(int tokens) {
        long cost    = (long) tokens * SCALE;
        long current = state.get();
        long now     = currentMs();
        long elapsed = (now - unpackTime(current)) & TOKEN_MASK;
        long have    = Math.min(capacityScaled, unpackTokens(current) + elapsed * refillPerMsScaled);
        if (have >= cost) return 0L;
        long deficit = cost - have;
        return (refillPerMsScaled == 0) ? Long.MAX_VALUE : (deficit / refillPerMsScaled) + 1;
    }

    // ── Internal ──────────────────────────────────────────────────────────

    private static long currentMs() {
        return System.currentTimeMillis();
    }

    // ── Example / smoke test ──────────────────────────────────────────────

    public static void main(String[] args) throws InterruptedException {
        // Capacity of 5 tokens, refilling at 2 tokens/sec
        FancyTokenBucketRateLimiter limiter = new FancyTokenBucketRateLimiter(5, 2.0);

        System.out.println("── Burst test (drain full bucket) ──");
        for (int i = 1; i <= 7; i++) {
            boolean ok = limiter.allow();
            System.out.printf("Request %d: %s | available=%.2f | retryAfter=%dms%n",
                    i, ok ? "✅ allowed" : "❌ limited",
                    limiter.availableTokens(), limiter.retryAfterMillis(1));
        }

        System.out.println("\nWaiting 2 seconds for refill (~4 tokens)...");
        Thread.sleep(2_000);

        System.out.println("\n── After refill ──");
        for (int i = 1; i <= 5; i++) {
            boolean ok = limiter.allow();
            System.out.printf("Request %d: %s | available=%.2f%n",
                    i, ok ? "✅ allowed" : "❌ limited",
                    limiter.availableTokens());
        }

        System.out.println("\n── Concurrent test (20 threads, capacity=5) ──");
        // Drain first so we start fresh
        Thread.sleep(3_000);
        int[] results = {0, 0};
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                if (limiter.allow()) { synchronized (results) { results[0]++; } }
                else                 { synchronized (results) { results[1]++; } }
            });
        }
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        System.out.printf("Allowed: %d, Limited: %d%n", results[0], results[1]);
    }
}