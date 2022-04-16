package problem.lcode;

import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaximizeSumOfArrayAfterKNegationsProblemTest extends ProblemBase {

    private MaximizeSumOfArrayAfterKNegationsProblem instance = new MaximizeSumOfArrayAfterKNegationsProblem();
    @Test
    void largestSum() {

        int[] input1 = {4,2,3}; int k1 = 1; int want1 = 5;
        int[] input2 = {3,-1,0,2}; int k2 = 3; int want2 = 6;
        int[] input3 = {2,-3,-1,5,-4}; int k3 = 2; int want3 = 13;

        assertAll("base",
                ()->assertEquals(instance.largestSum(input1,k1),want1),
                ()->assertEquals(instance.largestSum(input2,k2),want2),
                ()->assertEquals(instance.largestSum(input3,k3),want3)
        );
    }
}