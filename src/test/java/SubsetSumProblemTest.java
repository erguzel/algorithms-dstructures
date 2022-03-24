import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SubsetSumProblemTest extends ProblemBase {

    private SubsetSumProblem instance = new SubsetSumProblem();
    @Test
    void subsetSumElements() {
        int [] set = {2,8,4,12};
        int target = 12; boolean want = true;

        assertAll("Test",
                ()->assertEquals(instance.subsetSumElements(set,target,new HashMap<Integer,Boolean>()),want));

        instance.LOGGER.info("cc:"+instance.counter);
    }
}