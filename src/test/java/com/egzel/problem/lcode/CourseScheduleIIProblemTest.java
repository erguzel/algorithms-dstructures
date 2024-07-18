package com.egzel.problem.lcode;

import org.junit.jupiter.api.Test;

import com.egzel.problem.lcode.CourseScheduleIIProblem;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleIIProblemTest {

    public CourseScheduleIIProblem instance = new CourseScheduleIIProblem();
    @Test
    void testFindOrder() {
        int [][] input1= {
                {1,0}
        }; int [] want1 = {0,1}; int numberofvertex1 = 2;

        int[][] input2 = {
                {1,0},
                {2,0},
                {3,1},
                {3,2}
        }; int[] want2 = {0,1,2,3}; int numberofvertex2  = 4;

        assertAll("base",
                ()->assertArrayEquals(instance.findOrder(numberofvertex1,input1),want1),
                ()->assertArrayEquals(instance.findOrder(numberofvertex2,input2),want2)
        );
    }
}