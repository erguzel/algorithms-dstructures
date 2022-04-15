package lib.util;

import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleDataTest extends ProblemBase {

    @Test
    void testConvertEdgeListToAdjMatrix(){

        int[][] input = {
                {0,2,-12},
                {0, 4},
                {0, 5},
                {3, 4},
                {1, 5},
                {2, 4},
        };

        int[][] wantDirected = {
                {0,0,-12,0,1,1},
                {0,0,0,0,0,1},
                {0,0,0,0,1,0},
                {0,0,0,0,1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][] wantindirected = {
                {0,0,-12,0,1,1},
                {0,0,0,0,0,1},
                {-12,0,0,0,1,0},
                {0,0,0,0,1,0},
                {1,0,1,1,0,0},
                {1,1,0,0,0,0}
        };

        assertArrayEquals(SampleData.Convertors.convertEdgeListToAdjMatrix(input,true),wantDirected);
        assertArrayEquals(SampleData.Convertors.convertEdgeListToAdjMatrix(input,false),wantindirected);
    }

}