package com.practice.algo.generic;

import java.util.*;

public class CourseScheduleIII {
    public static int scheduleCourse(int[][] courses) {
        // Sort courses by their lastDay
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int totalTime = 0;

        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];

            totalTime += duration;
            maxHeap.offer(duration);

            // If total time exceeds current course's lastDay, drop the longest course
            if (totalTime > lastDay) {
                totalTime -= maxHeap.poll();
            }
        }

        return maxHeap.size(); // number of courses taken
    }

    public static void main(String[] args) {
        int[][] courses1 = {{100,200},{200,1300},{1000,1250},{2000,3200}};
        System.out.println(scheduleCourse(courses1)); // Output: 3
        /**
         * duration = 2
         * last = 2
         * total = 1
         *
         * duration = 2
         * last =3
         * total = 3
         *
         * duration 3
         * last = 4
         * total = 6
         * total = 3
         */
        int[][] courses2 = {{1,2},{2,3},{3,4}};
        System.out.println(scheduleCourse(courses2)); // Output: 2

        int[][] courses3 = {{5,5},{4,6},{2,6}};
        System.out.println(scheduleCourse(courses3)); // Output: 2
    }
}

