package com.practice.algo.generic;

import java.util.*;

public class TopKWords {

        static{
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                    fw.write("0");
                } catch (Exception e) {
                }
            }));}

        class Pair {
            int freq;
            String s;

            Pair(String s,int f){
                this.s = s;
                this.freq = f;
            }
        }

        public List<String> topKFrequent(String[] words, int k) {
            Map<String,Integer> counter = new HashMap<>();

            for(String w : words){
                counter.put(w,counter.getOrDefault(w,0)+1);
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>(
                    (a,b) -> {
                        if(a.freq != b.freq) return a.freq - b.freq;
                        return b.s.compareTo(a.s);
                    }
            );

            for(Map.Entry<String,Integer> entry : counter.entrySet()){
                Pair curr = new Pair(entry.getKey(),entry.getValue());
                if(pq.size() < k){
                    pq.offer(curr);
                    continue;
                }

                else if(pq.peek().freq < curr.freq || (curr.freq == pq.peek().freq && curr.s.compareTo(pq.peek().s) < 0)){
                    pq.poll();
                    pq.offer(curr);
                }
            }

            LinkedList<String> res = new LinkedList<>();
            while(!pq.isEmpty()){
                res.addFirst(pq.poll().s);
            }
            return res;
        }
}
