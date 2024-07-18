package com.egzel.problem;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.egzel.lib.model.ProblemBase;
import com.egzel.problem.SubsetSumProblem;

import static org.junit.jupiter.api.Assertions.*;

class SubsetSumProblemTest extends ProblemBase {

    private SubsetSumProblem instance = new SubsetSumProblem();

    @Disabled
    @Test
    void subsetSumElements() {
        int [] set = {6,9,3,2,11};
        int target = 11;
        int[][] want = {
                {9,2},
                {6,3,2},
                {11}
        };

        assertAll("Test",
                ()->assertEquals(instance.subsetSumElements(set,target),want)
        );


    }
}