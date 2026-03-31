package com.practice.algo.generic;

public class FindGapInBinary {
    public int solution(int N) {
        int gap =0 ;
        int r;
        int between = 0 ;
        boolean isbetween =false;
        int maxGap =0;
        while(N > 0){
            r = N%2;
            N= N/2;

            if(r==1 && between == 1 && isbetween){
                maxGap=Math.max(maxGap,gap);
               gap=0;

                isbetween= false;
            }else if(r==1){
                between=1;
            }else if(between == 1 && r==0){
                gap++;
                isbetween=true;
            }

        }
        return maxGap;
    }

    public static void main(String[] args) {
        FindGapInBinary f = new FindGapInBinary();
        System.out.println(f.solution(1041));
    }
}