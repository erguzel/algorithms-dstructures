package com.egzel.problem;

import org.junit.jupiter.api.Test;

import com.egzel.lib.model.ProblemBase;
import com.egzel.problem.FencePaintingProblem;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FencePaintingProblemTest extends ProblemBase {

    public FencePaintingProblem instance = new FencePaintingProblem();

    @Test
    void testNumberOfFencePaintingWays(){
        int n = 3; int want = 6;
        int n1 = 4; int want1 = 10;
        int n2 = 5; int want2 = 16;

        assertAll("regular",
                ()->assertEquals(instance.numberOfFencePaintingWays(n),want),
                ()->assertEquals(instance.numberOfFencePaintingWays(n1),want1),
                ()->assertEquals(instance.numberOfFencePaintingWays(n2),want2));
    }

    @Test
    void testNumberOfFencePaintingWaysRecursive() {
        int n = 3; int want = 6;
        int n1 = 4; int want1 = 10;
        int n2 = 5; int want2 = 16;

        assertAll("recursive",
                ()->assertEquals(instance.numberOfFencePaintingWaysRecursive(n ),want),
                ()->assertEquals(instance.numberOfFencePaintingWaysRecursive(n1),want1),
                ()->assertEquals(instance.numberOfFencePaintingWaysRecursive(n2),want2));
        System.out.println(counter);
    }

    @Test
    void testNumberOfFencePaintingWaysRecursiveMem() {
        int n = 3; int want = 6;
        int n1 = 4; int want1 = 10;
        int n2 = 5; int want2 = 16;

        assertAll("recursiveMem",
                ()->assertEquals(instance.numberOfFencePaintingWaysRecursiveMem(n,new HashMap<>()),want),
                ()->assertEquals(instance.numberOfFencePaintingWaysRecursiveMem(n1,new HashMap<>()),want1),
                ()->assertEquals(instance.numberOfFencePaintingWaysRecursiveMem(n2,new HashMap<>()),want2));
        System.out.println(counter);
    }

    @Test
    void testNumberOfFencePaintingWays2PartProblem() {
        int n = 3; int want = 6;
        int n1 = 4; int want1 = 10;
        int n2 = 5; int want2 = 16;
        int n3 = 6; int want3 = 26;

        assertAll("recursiveMem",
                ()->assertEquals(instance.numberOfFencePaintingWays2PartProblem(n),want),
                ()->assertEquals(instance.numberOfFencePaintingWays2PartProblem(n1),want1),
                ()->assertEquals(instance.numberOfFencePaintingWays2PartProblem(n2),want2),
                ()->assertEquals(instance.numberOfFencePaintingWays2PartProblem(n3),want3)
        );
        System.out.println(counter);
    }
}