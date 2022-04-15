package lib.util;

import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleDataTest extends ProblemBase {

    @Test
    void testConvertAdjMatrixToEdgeList(){
        int[][] input = {
                {0,0,-12,0,1,1},
                {0,0,0,0,0,1},
                {0,0,0,0,1,1},
                {0,0,0,0,1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][] wanteddirected = {
                {0,2,-12},
                {0, 4,1},
                {0, 5,1},
                {1, 5,1},
                {2, 4,1},
                {2, 5,1},
                {3, 4,1},
                //{2, 0,24},
        };

        int[][] wantedindirected = {
                {0,2,-12},
                {2,0,-12},
                {0, 4,1},
                {4, 0,1},
                {0, 5,1},
                {5, 0,1},
                {1, 5,1},
                {5, 1,1},
                {2, 4,1},
                {4, 2,1},
                {2, 5,1},
                {5, 2,1},
                {3, 4,1},
                {4, 3,1},
                //{2, 0,24},
        };

        assertArrayEquals(SampleData.Convertors.convertAdjMatrixToEdgeList(input,true),wanteddirected);
        assertArrayEquals(SampleData.Convertors.convertAdjMatrixToEdgeList(input,false),wantedindirected);

    }

    @Test
    void testConvertEdgeListToAdjMatrix(){

        int[][] input = {
                {0,2,-12},
                {0, 4},
                {0, 5},
                {3, 4},
                {1, 5},
                {2, 4},
                //{2, 0,24},
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