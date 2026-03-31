package com.practice.algo.generic;

import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class RPN {

    public String  evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) { return "Empty";}
        Set<String> ops = Set.of("*","/","+","-","%","^");
        Stack<String> p = new Stack<>();
        for(String t: tokens){
            if(ops.contains(t)){
                BigDecimal a = new BigDecimal(p.pop());
                BigDecimal b = new BigDecimal(p.pop());
                switch(t){
                    case "*":
                        p.add(a.multiply(b).toString());
                        break;
                    case "/":
                        p.add(b.divide(a , 10, RoundingMode.HALF_UP).toString());
                        break;
                    case "+":
                        p.add(b.add(a).toString());
                        break;
                    case "-":
                        p.add(b.subtract(a).toString());
                        break;
                    case "%":
                        p.add(b.remainder(a).toString());
                        break;
                    case "^":
                        if(a.scale() > 0) throw new IllegalArgumentException();
                        p.add(b.pow(a.intValue()).toString());
                        break;
                }

            } else {
                p.add(t);
            }
        }
        return p.pop();
    }

    public static void main(String[] args) {
        RPN p = new RPN();

        assertEquals("2", p.evalRPN(new String[]{"1","2","*"}));

        assertEquals("5", p.evalRPN(new String[] {"2", "3", "+"}));         // 2 + 3 =
        assertEquals("6", p.evalRPN(new  String[]  {"10", "4", "-"}));        // 10 - 4 = 6
        assertEquals("12", p.evalRPN(new String[]  {"3", "4", "*"}));         // 3 * 4 = 12
        assertEquals("5.0000000000", p.evalRPN(new String[]  {"10", "2", "/"}));        // 10 / 2 = 5

        assertEquals("4.0", p.evalRPN(new String[]  {"2.5", "1.5", "+"}));  // 2.5 + 1.5 = 4.0
        assertEquals("10.0", p.evalRPN(new String[]  {"2.5", "4", "*"}));    // 2.5 * 4 = 10.0
        assertEquals("5.2500000000", p.evalRPN(new String[]  {"10.5", "2", "/"}));   // 10.5 / 2 = 5.25

        assertEquals("-2", p.evalRPN(new String[]  {"-5", "3", "+"}));       // -5 + 3 = -2
        assertEquals("-8", p.evalRPN(new String[]  {"4", "-2", "*"}));       // 4 * -2 = -8
        assertEquals("2.0000000000", p.evalRPN(new String[] {"-10", "-5", "/"}));     // -10 / -5 = 2

        assertEquals("1", p.evalRPN(new String[] {"10", "3", "%"}));        // 10 % 3 = 1
        assertEquals("8", p.evalRPN(new String[] {"2", "3", "^"}));       // 2 ^ 3 = 8

        assertEquals("9", p.evalRPN(new String[]  {"2", "1", "+", "3", "*"}));      // (2 + 1) * 3 = 9
        assertEquals("14", p.evalRPN(new String[]  {"5", "1", "2", "+", "4", "*", "+", "3", "-"})); // 5 + ((1 + 2) * 4) - 3 = 14
        assertEquals("6.6000000000", p.evalRPN(new String[]  {"4", "13", "5", "/", "+"}));    // 4 + (13 / 5) = 6.6

        assertEquals("42", p.evalRPN(new String[]  {"42"}));

        assertEquals("Empty", p.evalRPN(new String[]  {}));

        assertEquals("0.3333333333", p.evalRPN(new  String[]  {"1", "3", "/"}));   // 1 / 3 ≈ 0.3333333333

        String[] mix = {"2.5","3.5","+","10","*","5","/"};
// ((2.5 + 3.5) * 10) / 5 = 12

    }
}
