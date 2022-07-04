import graph.CycleDetectionDirected;
import lib.util.SampleData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CycleDetectionDirectedTest {

    //
    //https://csacademy.com/app/graph_editor/
    //
    public CycleDetectionDirected instance = new CycleDetectionDirected();
    @Test
    void testHasCycle() {
        int [][] input1 = {
                {0, 1, 10},
                {2, 0,  1},
                {1, 4,  0},
                {3, 2,  2},
                {5, 2,  8},
                {3, 5,  2},
                {3, 6,  7},
                {4, 5,  1},
                {4, 7,  8},
                {5, 6,  6},
                {5, 7,  9},
                {6, 7, 12},
        };
        int numberofvertices1 = 8; boolean want1 = true;
        //
        //
        int[][] input2 = SampleData.Csacademy.DIRECTED_CYCLED_7_WEIGHTEDNEG;
        int numberofvertices2 = 7; boolean want2 = true;
        //
        //
        int[][] input3 = {
                {0, 1},
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {3, 4},
                {3, 6},
                {4, 5},
                {4, 6},
        };
        int numberofvertices3 = 7; boolean want3= false;
        //
        //
        int[][] input4 = SampleData.Csacademy.DIRECTED_NONCYCLED_NONWEIGHTED_1_6;
        int numberofvertices4 = 7; boolean want4= false;
        //
        //
        assertAll("base",
                ()->assertEquals(instance.hasCycle(input1,numberofvertices1),want1),
                ()->assertEquals(instance.hasCycle(input2,numberofvertices2),want2),
                ()->assertEquals(instance.hasCycle(input3,numberofvertices3),want3),
                ()->assertEquals(instance.hasCycle(input4,numberofvertices4),want4)
        );
    }
}