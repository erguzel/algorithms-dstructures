import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SubsetSumProblemTest extends ProblemBase {

    private SubsetSumProblem instance = new SubsetSumProblem();
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
                ()->assertEquals(instance.subsetSumElements(set,target),want));

        instance.LOGGER.info("cc:"+instance.counter);
    }
}