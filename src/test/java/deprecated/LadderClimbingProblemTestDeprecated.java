package deprecated;

import lib.model.ProblemBase;
import lib.util.ALogger;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class LadderClimbingProblemTestDeprecated extends ProblemBase {

    private LadderClimbingProblemDeprecated instance = new LadderClimbingProblemDeprecated();
    static ALogger<LadderClimbingProblemTestDeprecated> LOGGER = new ALogger<>(LadderClimbingProblemDeprecated.class);

    @Test
    void testGetDifferentWaysV1() {
        assertAll("testGetDifferentWaysV1",
                () -> assertEquals(instance.getDifferentWaysV1(0), 1),
                () -> assertEquals(instance.getDifferentWaysV1(1), 1),
                () -> assertEquals(instance.getDifferentWaysV1(2), 2),
                () -> assertEquals(instance.getDifferentWaysV1(3), 3),
                () -> assertEquals(instance.getDifferentWaysV1(5), 8)
        );
    }

    @Test
    void testGetDifferentWaysV2() {
        assertAll("V2",
                () -> assertEquals(instance.getDifferentWaysV2(0), 1),
                () -> assertEquals(instance.getDifferentWaysV2(1), 1),
                () -> assertEquals(instance.getDifferentWaysV2(2), 2),
                () -> assertEquals(instance.getDifferentWaysV2(3), 4),
                () -> assertEquals(instance.getDifferentWaysV2(5), 13)
        );
    }

    @Test
    void testGetDifferentWaysV3(){

        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.getDifferentWaysV3(3,2), 3),
                () -> assertEquals(instance.getDifferentWaysV3(5,2), 8),
                () -> assertEquals(instance.getDifferentWaysV3(3,3), 4),
                () -> assertEquals(instance.getDifferentWaysV3(5,3), 13)
        );
    }

    @Test
    void testGetDifferentWaysV4(){
        boolean[] forbiddens = {false,true,false,true,true,false,false};
        int n = 7;  int k = 3;


        assertAll("arbitrary",
                ()->assertEquals(instance.getDifferentWaysV4(n,k,forbiddens),2));
    }

    @Test
    void getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed() {

        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(0), 1),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(1), 1),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(2), 2),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(3), 3),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(5), 8)
        );
    }

    @Test
    void getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed() {
        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed(0), 1),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed(1), 1),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed(2), 2),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed(3), 4),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed(5), 13)
        );
    }

    @Test
    void getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowed(){
        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowed(3,2), 3),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowed(5,2), 8),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowed(3,3), 4),
                () -> assertEquals(instance.getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowed(5,3), 13)
        );
    }

    @Test
    void testGetNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowedForbiddenStairs(){
        boolean[] forbiddens = {false,true,false,true,true,false,false};
        int n = 7; int k = 3;

        assertAll("arbitrary",
                ()->assertEquals(
                        instance.getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowedForbiddenStairs(n,k,forbiddens),
                        2));
    }

    @Test
    void testGetNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowedForbiddenStairs2(){
        boolean[] forbiddens = {false,true,false,true,true,false,false};
        int n = 7; int k = 3;

        assertAll("arbitrary",
                ()->assertEquals(
                        instance.getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowedForbiddenStairs2(n,k,forbiddens),
                        2));
    }
    //
    // Optimisation probs
    //
    @Test
    void testMinimumCostToGetTop2steps(){
        int nofleadder = 3; int[] cost = {0,3,2,4}; int want = 6;
        assertAll("Arbitrary",
                ()->assertEquals(instance.minimumCostToGetTop2steps(nofleadder,cost),want));
    }

    @Test
    void testMinimumCostToGetTop2(){
        int nofleadder = 3; int[] cost = {0,3,2,4}; int want = 6;int maxJump = 2;
        assertAll("Arbitrary",
                ()->assertEquals(instance.minimumCostToGetTop_2(nofleadder,maxJump,cost),want));
    }


    @Test
    void testMinimumCostToGetTop2stepsPath(){
        int n = 8; int[]cost = {0,3,2,4,6,1,1,5,3};
        int [] want = {0,2,3,5,6,8};
        assertAll("arbitrary",
                ()->assertArrayEquals(instance.minimumCostToGetTop2stepsPath(n,cost),
                        want));
    }



}