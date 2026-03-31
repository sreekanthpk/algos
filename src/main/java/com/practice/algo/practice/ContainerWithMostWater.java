package com.practice.algo.practice;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max area: " + maxArea(height)); // Output: 49
        }

    private static int  maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left<right){
            int diff = right-left;
            int area = Math.min(height[left],height[right]) * diff;
            maxArea = Math.max(maxArea, area);
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return maxArea;

    }
}
