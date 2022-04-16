import org.junit.jupiter.api.Test;
import problem.LadderClimbingProblem;

import static org.junit.jupiter.api.Assertions.*;

class LadderClimbingProblemTest {

    private LadderClimbingProblem instance = new LadderClimbingProblem();
    @Test
    void numberOfWaysToReachTop() {
        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.numberOfWaysToReachTop(0), 1),
                () -> assertEquals(instance.numberOfWaysToReachTop(1), 1),
                () -> assertEquals(instance.numberOfWaysToReachTop(2), 2),
                () -> assertEquals(instance.numberOfWaysToReachTop(3), 3),
                () -> assertEquals(instance.numberOfWaysToReachTop(5), 8)
        );
    }

    @Test
    void testNumberOfWaysToReachTop() {

        int n  = 3; int[] j  = {1,2}; int  w =3;
        int n1 = 5; int[] j1 = {1,2}; int  w1 = 8;
        int n2 = 3; int[] j2 = {1,2,3}; int w2 = 4;
        int n3 = 5; int[] j3 = {1,2,3}; int w3 = 13;

        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.numberOfWaysToReachTop(n,j), w),
                () -> assertEquals(instance.numberOfWaysToReachTop(n1,j1), w1),
                () -> assertEquals(instance.numberOfWaysToReachTop(n2,j2), w2),
                () -> assertEquals(instance.numberOfWaysToReachTop(n3,j3), w3)

        );
    }

    @Test
    void minimumNumberOfJumpsToTheTop() {
        int n  = 4; int[] j  = {1,3}; int  w =2;
        int n1  = 5; int[] j1  = {1,3}; int  w1 =3;
        int n2  = 6; int[] j2  = {1,3}; int  w2 =2;
        int n3  = 7; int[] j3  = {1,3}; int  w3 =3;
        int n4  = 8; int[] j4  = {1,3}; int  w4 =4;
        int n5  = 9; int[] j5  = {1,3}; int  w5 =3;
        int n6  = 3; int[] j6  = {8}; int  w6 =-1;
        assertAll("baseAndARbitraryCases",
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n,j), w),
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n1,j1), w1),
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n2,j2), w2),
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n3,j3), w3),
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n4,j4), w4),
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n5,j5), w5),
                () -> assertEquals(instance.minimumNumberOfJumpsToTheTop(n6,j6), w6));
    }


    @Test
    void testNumberOfWaysToReachTopForbiddens() {
        boolean[] forbiddens = {false,true,false,true,true,false,false};
        int n = 7; int[] k = {1,2,3};

        assertAll("arbitrary",
                ()->assertEquals(
                        instance.numberOfWaysToReachTop(n,k,forbiddens),
                        2));
    }

    @Test
    void minimumCostToReachTop() {
        int nofleadder = 3; int[] cost = {0,3,2,4}; int want = 6;int[] maxJump = {1,2};
        assertAll("Arbitrary",
                ()->assertEquals(instance.minimumCostToReachTop(nofleadder,maxJump,cost),want));
    }


    @Test
    void minimumNumberOfJumpsPath() {
        int n   = 4; int[] j  = {1,3}; int []  w = new int[]{0, 1, 4};
        int n1  = 5; int[] j1  = {1,3}; int[] w1 =new int[]{0, 1, 2, 5};
        int n2  = 6; int[] j2  = {1,3}; int[] w2 =new int[]{0, 3, 6};
        int n3  = 7; int[] j3  = {1,3}; int[] w3 =new int[]{0, 1, 4, 7};
        int n4  = 8; int[] j4  = {1,3}; int[] w4 =new int[]{0, 1, 2, 5, 8};
        int n5  = 9; int[] j5  = {1,3}; int[] w5 =new int[]{0, 3, 6, 9};
        assertAll("baseAndARbitraryCases",
                () -> assertArrayEquals(instance.minimumNumberOfJumpsPath(n,j), w),
                () -> assertArrayEquals(instance.minimumNumberOfJumpsPath(n1,j1), w1),
                () -> assertArrayEquals(instance.minimumNumberOfJumpsPath(n2,j2), w2),
                () -> assertArrayEquals(instance.minimumNumberOfJumpsPath(n3,j3), w3),
                () -> assertArrayEquals(instance.minimumNumberOfJumpsPath(n4,j4), w4),
                () -> assertArrayEquals(instance.minimumNumberOfJumpsPath(n5,j5), w5)
        );
    }
}