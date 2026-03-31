package com.practice.algo.doubleone;


import java.math.BigDecimal;

public class DoubleCalc {
    public static void main(String[] args) {
        double a = 0.1;
        double b = 0.2;
        System.out.println(a*b);

        BigDecimal a1 = new BigDecimal("0.1");
        BigDecimal b1 = new BigDecimal("0.2");
        System.out.println(a1.multiply(b1));

        String a2 = "0.111";
        String b2 = "0.222";

        int precision = 3;

        System.out.println(Double.doubleToLongBits(2.03));


    }

}