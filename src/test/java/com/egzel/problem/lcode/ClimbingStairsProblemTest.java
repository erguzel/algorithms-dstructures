package com.egzel.problem.lcode;

import org.junit.jupiter.api.Test;

import com.egzel.lib.model.ProblemBase;
import com.egzel.problem.lcode.ClimbingStairsProblem;

import static org.junit.jupiter.api.Assertions.*;

class ClimbingStairsProblemTest extends ProblemBase {

    public ClimbingStairsProblem instance = new ClimbingStairsProblem();
    @Test
    void climbStairs() {
        int in1 = 2, w1 = 2;
        int in2 = 3, w2 = 3;

        assertAll("base",
                ()->assertEquals(instance.climbStairs(in1),w1),
                ()->assertEquals(instance.climbStairs(in2),w2)
        );

    }
}