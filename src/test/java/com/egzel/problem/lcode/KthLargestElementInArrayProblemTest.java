package com.egzel.problem.lcode;

import org.junit.jupiter.api.Test;

import com.egzel.problem.lcode.KthLargestElementInArrayProblem;

import static org.junit.jupiter.api.Assertions.*;

class KthLargestElementInArrayProblemTest {

    public KthLargestElementInArrayProblem instance = new KthLargestElementInArrayProblem();
    @Test
    void testFindKthLargest() {

        int[] input1={3,2,1,5,6,4}; int k1 = 2; int want1 = 5;
        int[] input2={3,2,3,1,2,4,5,5,6}; int k2 = 4; int want2 = 4;

        assertAll("base",
                ()->assertEquals(instance.findKthLargest(input1,k1),want1),
                ()->assertEquals(instance.findKthLargest(input2,k2),want2)
        );

    }
}