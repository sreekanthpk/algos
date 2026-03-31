package com.practice.algo.java;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * Single-Producer, Multiple-Consumer Ring Buffer
 * Each consumer sees every event independently
 */
public final class RingBuffer<T> {

    private final Object[] buffer;
    private final int size;
    private final int mask;

    // Padding to avoid false sharing with producerSeq
    @SuppressWarnings("unused")
    private long p1, p2, p3, p4, p5, p6, p7;

    private volatile long producerSeq = -1;

    @SuppressWarnings("unused")
    private long p8, p9, p10, p11, p12, p13, p14;

    // Each consumer has its own cursor, padded to avoid false sharing
    private final AtomicLongArray consumerSeqs;

    private long cachedConsumerSeq = -1L;

    public RingBuffer(int size, int numConsumers) {
        if (Integer.bitCount(size) != 1) {
            throw new IllegalArgumentException("Size must be a power of 2");
        }

        this.size = size;
        this.mask = size - 1;
        this.buffer = new Object[size];
        this.consumerSeqs = new AtomicLongArray(numConsumers);

        for (int i = 0; i < numConsumers; i++) {
            consumerSeqs.set(i, -1);
        }
    }

    /**
     * Publish a single event to the ring buffer
     */
    public void publish(T event) {
        long nextSeq = producerSeq + 1;

        long wrapPoint = nextSeq - buffer.length;

        // Optimized Gating: Only scan consumers if our cached min is too old
        if (wrapPoint > cachedConsumerSeq) {
            while (wrapPoint > (cachedConsumerSeq = getMinConsumerSeq())) {
                Thread.onSpinWait();
            }
        }

        buffer[(int) (nextSeq & mask)] = event;

        // Publish the sequence after writing
        producerSeq = nextSeq;
    }

    /**
     * Each consumer calls this to consume next available event
     * @param consumerId index of the consumer (0..numConsumers-1)
     */
    public T consume(int consumerId) {
        long nextSeq = consumerSeqs.get(consumerId) + 1;

        // Wait until producer has published this sequence
        while (producerSeq < nextSeq) {
            Thread.onSpinWait();
        }

        @SuppressWarnings("unchecked")
        T event = (T) buffer[(int) (nextSeq & mask)];

        // Update this consumer's sequence
        consumerSeqs.set(consumerId, nextSeq);
        return event;
    }

    /**
     * Get the minimum consumer sequence to apply backpressure
     */
    private long getMinConsumerSeq() {
        long min = Long.MAX_VALUE;
        for (int i = 0; i < consumerSeqs.length(); i++) {
            long seq = consumerSeqs.get(i);
            min = Math.min(min, seq);
        }
        return min;
    }

    /**
     * Helper to get current producer sequence
     */
    public long getProducerSeq() {
        return producerSeq;
    }

    /**
     * Helper to get current sequence of a consumer
     */
    public long getConsumerSeq(int consumerId) {
        return consumerSeqs.get(consumerId);
    }
}
