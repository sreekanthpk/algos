package com.practice.algo.generic;

/** problem is self exaplanatory
 * Want to find column with most area
 */

public class ContainerWithMostWater1 {
    public static int maxArea(int[] height) {
       int left = 0;
       int right = height.length - 1;
       int maxArea = 0;

       while (left <= right) {
           int diff = right - left;
           int minHeight = Math.min(height[left], height[right]);
           int area = minHeight*diff;
           maxArea = Math.max(area, maxArea);
           if(height[left] < height[right]) {
               left++;
           }else{
               right--;
           }

       }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max area: " + maxArea(height)); // Output: 49
    }
}