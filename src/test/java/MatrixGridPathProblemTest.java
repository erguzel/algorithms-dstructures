import lib.model.ProblemBase;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MatrixGridPathProblemTest extends ProblemBase {

    private MatrixGridPathProblem instance = new MatrixGridPathProblem();

    @Test
    void testMaxNumberOfGetingToTheBottomRightGrid(){
        int m_s = 1; int n_s = 1; int want_s = 1;
        int m_n = 3; int n_n = 4; int want_n = 10;

        assertAll("smallAndSimpleMatrix",
                ()->assertEquals(instance.maxNumberOfGetingToTheBottomRightGrid(m_s,n_s),want_s),
                ()->assertEquals(instance.maxNumberOfGetingToTheBottomRightGrid(m_n,n_n),want_n)
        );
    }

    @Test
    void testMaxNumberOfGetingToTheBottomRightGridWithObstacles(){
        int[][] small = {
                {0,0}
        }; int want_small = 1;

        int [][] medium = {
                {0,0,0,0},
                {0,0,1,1},
                {0,0,0,0},
        }; int want_medium = 3;

        assertAll("simpleMatcices",
                ()->assertEquals(instance.maxNumberOfGetingToTheBottomRightGridWithObstacles(small),want_small),
                ()->assertEquals(instance.maxNumberOfGetingToTheBottomRightGridWithObstacles(medium),want_medium)
        );
    }

    @Test
    void testMaximumGridPointOneCanCollectTillReachingBottomRightCorner(){
        int [][] medium = {
                {0,2,2,1},
                {3,1,1,1},
                {4,4,2,0},
        }; int want_medium = 13;
        int [][] medium2 = {
                {0,2,2,50},
                {3,1,1,100},
                {4,4,2,0},
        }; int want_medium2 = 154;

        assertAll("medium",
                ()->assertEquals(instance.maximumGridPointOneCanCollectTillReachingBottomRightCorner(medium),want_medium),
                ()->assertEquals(instance.maximumGridPointOneCanCollectTillReachingBottomRightCorner(medium2),want_medium2)
        );
    }

    @Test
    void testMaximumGridPointOneCanCollectTillReachingBottomRightCornerPath(){
        int [][] medium = {
                {0,2,2,1},
                {3,1,1,1},
                {4,4,2,0},
        };int[][]want= {
            {0,0},{1,0},{2,0},{2,1},{2,2},{2,3}
        };

        int [][] medium1 = {
                {0,2,2,50},
                {3,1,1,100},
                {4,4,2,0},
        };int[][]want1= {
                {0,0},{0,1},{0,2},{0,3},{1,3},{2,3}
        };



        assertAll("medium-1",
                ()->assertArrayEquals(instance.maximumGridPointOneCanCollectTillReachingBottomRightCornerPath(medium),want),
                ()->assertArrayEquals(instance.maximumGridPointOneCanCollectTillReachingBottomRightCornerPath(medium1),want1)

        );
    }
}