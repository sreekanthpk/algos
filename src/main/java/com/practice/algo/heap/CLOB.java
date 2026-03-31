package com.practice.algo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

enum Side{
    BUY,
    SELL;
}

record Order(int orderId, Side side , int quantity, double price){ }

public class CLOB {

    PriorityQueue<Order> buyOrder = new PriorityQueue<>(Comparator.comparingDouble(Order::price));
    PriorityQueue<Order> sellOrder = new PriorityQueue<>(Comparator.comparingDouble(o -> -o.price()));

    public void placeOrder(Order order){
        if(order.side()== Side.BUY){
            matchBuyOrder(order);
        }else {
            matchSellOrder(order);
        }
    }

    public void matchBuyOrder(Order order){
        while(sellOrder.peek()!=null && sellOrder.peek().price()<=order.price()){
            Order bestSell = sellOrder.poll();
            int matchQuanity = Math.min(bestSell.quantity(),order.quantity());
            System.out.println("Matched quantity: " + matchQuanity + " for price " + bestSell.price() +" buy "+ order.orderId() + " Sell "+ bestSell.orderId());
            if(bestSell.quantity()-matchQuanity>0){
                bestSell = new Order(bestSell.orderId(), Side.SELL,  bestSell.quantity()-matchQuanity, bestSell.price());
                sellOrder.add(bestSell);
            }
            if(order.quantity()-matchQuanity == 0) return;
            if(order.quantity()-matchQuanity>0){
                order = new Order(order.orderId(), Side.BUY,  order.quantity()-matchQuanity, order.price());
                buyOrder.add(order);
            }
        }

        buyOrder.offer(order);

    }

    public void matchSellOrder(Order order){
        while(buyOrder.peek()!=null && buyOrder.peek().price()>=order.price()){
            Order bestBuy = buyOrder.poll();
            int matchQuanity = Math.min(bestBuy.quantity(),order.quantity());
            System.out.println("Matched quantity: " + matchQuanity + " for price " + bestBuy.price() +" buy "+ bestBuy.orderId() + " Sell "+ order.orderId());
            if(bestBuy.quantity()-matchQuanity>0){
                bestBuy = new Order(bestBuy.orderId(), Side.SELL,  bestBuy.quantity()-matchQuanity, bestBuy.price());
                buyOrder.add(bestBuy);
            }
            if(order.quantity()-matchQuanity == 0) return;
            if(order.quantity()-matchQuanity>0){
                order = new Order(order.orderId(), Side.BUY,  order.quantity()-matchQuanity, order.price());
                sellOrder.add(order);
            }
        }
        sellOrder.offer(order);
    }

    public static void main(String[] args) {

        CLOB c = new CLOB();
        c.placeOrder(new Order(0,Side.BUY,100,100));
        c.placeOrder(new Order(1,Side.SELL,50,110));
        c.placeOrder(new Order(2,Side.BUY,100,110));

    }



}
