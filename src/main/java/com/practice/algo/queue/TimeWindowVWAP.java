package com.practice.algo.queue;

import java.util.ArrayDeque;
import java.util.Deque;

class Trade {
    double price;
    long volume;
    long timestamp;

    Trade(double price, long volume, long timestamp) {
        this.price = price;
        this.volume = volume;
        this.timestamp = timestamp;
    }
}

public class TimeWindowVWAP {

    private final long windowMillis;
    private final Deque<Trade> trades = new ArrayDeque<>();
    private double totalPV = 0.0;
    private long totalVolume = 0;

    public TimeWindowVWAP(long windowMillis) {
        this.windowMillis = windowMillis;
    }

    public double add(double price, long volume, long timestamp) {
        Trade trade = new Trade(price, volume, timestamp);
        trades.addLast(trade);
        totalPV += price * volume;
        totalVolume += volume;

        // Evict old trades
        while (!trades.isEmpty() &&
                trades.peekFirst().timestamp < timestamp - windowMillis) {

            Trade old = trades.removeFirst();
            totalPV -= old.price * old.volume;
            totalVolume -= old.volume;
        }

        return totalPV / totalVolume;
    }
}
