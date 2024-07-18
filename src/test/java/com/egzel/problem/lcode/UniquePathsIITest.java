package com.egzel.problem.lcode;

import org.junit.jupiter.api.Test;

import com.egzel.problem.lcode.UniquePathsII;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsIITest {
    public UniquePathsII instance = new UniquePathsII();

    @Test
    void testUniquePathsWithObstacles() {

        int [][] input1 = {
                {0,0,0},
                {0,1,0},
                {0,0,0},
        };int want1 = 2;

        int [][] input2 = {
                {0,1},
                {0,0},
        };int want2 = 1;

        int [][] input3 = {
                {0,0},
                {0,1},
        };int want3 = 0;

        assertAll("base",
                //()->assertEquals(instance.uniquePathsWithObstacles(input1),want1),
                //()->assertEquals(instance.uniquePathsWithObstacles(input2),want2),
                ()->assertEquals(instance.uniquePathsWithObstacles(input3),want3)
        );
    }
}