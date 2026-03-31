package com.practice.algo.order;

import java.util.*;

class Order{
    String side;
    int orderId;
    int price;
    long volume;

    public Order(int orderId, String side, int price, int volume) {
        this.orderId = orderId;
        this.side = side;
        this.price = price;
        this.volume = volume;
    }
}

class Trade{
    @Override
    public String toString() {
        return "Trade{" +
                "buyOrderId=" + buyOrderId +
                ", sellOrderId=" + sellOrderId +
                ", price=" + price +
                ", tradeVolume=" + tradeVolume +
                '}';
    }

    public int buyOrderId;
    public int sellOrderId;
    public int price;
    public long tradeVolume;
}

public class OrderBook {

    private final TreeMap<Integer, ArrayDeque<Order>> bids = new TreeMap<Integer, ArrayDeque<Order>>(Collections.reverseOrder());
    private final TreeMap<Integer, ArrayDeque<Order>> asks = new TreeMap<Integer, ArrayDeque<Order>>();

    List<Trade> trades = new ArrayList<Trade>();

    public void add(Order o){
        if(o.side.equalsIgnoreCase("BUY")){
            match(o, asks, bids);
        } else{
            match(o , bids, asks);
        }
    }

    private void match(Order o, TreeMap<Integer, ArrayDeque<Order>> book, TreeMap<Integer, ArrayDeque<Order>> otherSide) {
        while(!(o.volume == 0) && !book.isEmpty()){
            Map.Entry<Integer, ArrayDeque<Order>> best = book.firstEntry();
            int price = best.getKey();
            if (o.price < price && o.side.equalsIgnoreCase("BUY")) {
                break;
            }

            if(o.price > price && o.side.equalsIgnoreCase( "SELL")){
                break;
            }

            ArrayDeque<Order> ordersAtPrice = best.getValue();

            while (!(o.volume == 0) && !ordersAtPrice.isEmpty()) {
                Order order = ordersAtPrice.peekFirst();

                long tradeVolume =  Math.min(o.volume,
                        order.volume);

                Trade trade = new Trade();
                trade.buyOrderId = o.orderId;
                trade.sellOrderId = order.orderId;
                trade.price = order.price;
                trade.tradeVolume = tradeVolume;
                trades.add(trade);
                o.volume -= tradeVolume;
                order.volume -= tradeVolume;

                if (order.volume == 0) {
                    ordersAtPrice.pollFirst();
                }

            }

            if (ordersAtPrice.isEmpty()) {
                book.remove(price);
            }

        }

        if (!(o.volume == 0)) {
            otherSide.computeIfAbsent(o.price,
                    k -> new ArrayDeque<>()).addLast(o);
        }

    }

    public static void main(String[] args) {
        OrderBook ob = new OrderBook();
        ob.add(new Order(1,"BUY", 100,10));
        ob.add(new Order(2,"BUY",101,6));
        ob.add(new Order(3,"SELL", 100, 12));
        ob.trades.stream().forEach(System.out::println);

    }
}
