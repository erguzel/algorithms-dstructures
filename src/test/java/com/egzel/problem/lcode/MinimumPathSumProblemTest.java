package com.egzel.problem.lcode;

import org.junit.jupiter.api.Test;

import com.egzel.lib.model.ProblemBase;
import com.egzel.problem.lcode.MinimumPathSumProblem;

import static org.junit.jupiter.api.Assertions.*;

class MinimumPathSumProblemTest extends ProblemBase {

    private MinimumPathSumProblem instance = new MinimumPathSumProblem();
    @Test
    void testMinimumPathSum() {

        int [][] input1 = {
                {1,3,1},{1,5,1},{4,2,1}
        }; int want1 = 7;

        int [][] input2 = {
                {1,2,3},{4,5,6}
        }; int want2 = 12;

        assertAll("base",
                ()->assertEquals(instance.minimumPathSum(input1),want1),
                ()->assertEquals(instance.minimumPathSum(input2),want2)

        );
    }
}