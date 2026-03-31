package com.practice.algo.generic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class OverlappingIntervals {

    public static void main(String[] args){

        Interval i = new Interval(1,5);
        Interval j = new Interval(3,7);
        Interval k = new Interval(4,6);
        Interval l = new Interval(6,8);

        Interval[] a = new Interval[]{i, j, k, l};

        Stack<Interval> stack=new Stack<>();

        Arrays.sort(a,new Comparator<Interval>(){
            public int compare(Interval i1,Interval i2)
            {
                return i1.x-i2.x;
            }
        });

        stack.push(a[0]);

        for (int u = 1 ; u < a.length; u++)
        {
            Interval top = stack.peek();
            if (top.y < a[u].x)
                stack.push(a[u]);
            else if (top.y < a[u].y)
            {
                top.y = a[u].y;
                stack.pop();
                stack.push(top);
            }
        }

        System.out.print("The Merged Intervals are: ");
        while (!stack.isEmpty())
        {
            Interval t = stack.pop();
            System.out.print("["+t.x+","+t.y+"] ");
        }
    }


    static class Interval {
        int x;
        int y;
        public Interval(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
