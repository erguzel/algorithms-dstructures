package lib.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests static methods
 *
 * Graphs can be visualized at;
 *
 * https://graphonline.ru/en/
 * https://csacademy.com/app/graph_editor/
 */
class DataUtilTest {
    @Test
    public void testConvertAdjListToAdjMatrix(){

        //
        // test1
        //
        //params
        int[][] input1 = {
                {2,4,5},
                {4,5},
                {0,3,4},
                {2},
                {0,1,2,5},
                {0,1,4},
        };
        boolean isDirected1 = false;
        //output
        int[][] want1 = {
                {0,0,1,0,1,1},
                {0,0,0,0,1,1},
                {1,0,0,1,1,0},
                {0,0,1,0,0,0},
                {1,1,1,0,0,1},
                {1,1,0,0,1,0},
        };
        int[][] output1 = DataUtil.Convertors.convertAdjListToAdjMatrix(input1,isDirected1);

        //
        // test2
        //
        //params
        int[][] input2 = {
                {1},
                {}
        };
        boolean isDirected2 = true;
        //output
        int[][] want2 = {
                {0,1},
                {0,0}
        };
        int[][] output2 = DataUtil.Convertors.convertAdjListToAdjMatrix(input2,isDirected2);


        //
        // test3
        //
        //params
        int[][] input3 = {
                {0},
                {0,1},
                {0,1,2},
                {0,1,2,3},
        };
        boolean isDirected3 = true;
        //output
        int[][] want3 = {
                {1,0,0,0},
                {1,1,0,0},
                {1,1,1,0},
                {1,1,1,1},
        };
        int[][] output3 = DataUtil.Convertors.convertAdjListToAdjMatrix(input3,isDirected3);

        //
        // test4
        //
        //params
        int[][] input4 = {
                {1}
        };
        boolean isDirected4 = true;
        //output
        int[][] want4 = {
                {0,1},
                {0,0}
        };
        int[][] output4 = DataUtil.Convertors.convertAdjListToAdjMatrix(input4,isDirected4,false);

        //
        // test5
        //
        //params
        int[][] input5 = {
                {1,2},
                {3,4},
                {6},
        };
        boolean isDirected5 = true;
        //output
        int[][] want5 = {
                {0,1,1,0,0,0,0},
                {0,0,0,1,1,0,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},

        };
        int[][] output5 = DataUtil.Convertors.convertAdjListToAdjMatrix(input5,isDirected5,false);

        //
        // test6
        //
        //params
        int[][] input6 = {
                {1,2},
                {3,4},
                {6},
        };
        boolean isDirected6 = false;
        //output
        int[][] want6 = {
                {0,1,1,0,0,0,0},
                {1,0,0,1,1,0,0},
                {1,0,0,0,0,0,1},
                {0,1,0,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0},

        };
        int[][] output6 = DataUtil.Convertors.convertAdjListToAdjMatrix(input6,isDirected6,false);


        //
        // test6
        //
        //params
        int[][] input7 = {
                {},
                {},
                {},
        };
        boolean isDirected7 = true;
        //output
        int[][] want7 = {
                {0,0,0},
                {0,0,0},
                {0,0,0},


        };
        int[][] output7 = DataUtil.Convertors.convertAdjListToAdjMatrix(input7,isDirected7,false);


        //
        // assertion
        //
        assertAll("basic",
                ()->assertArrayEquals(output1,want1),
                ()->assertArrayEquals(output2,want2),
                ()->assertArrayEquals(output3,want3),
                ()->assertArrayEquals(output4,want4),
                ()->assertArrayEquals(output5,want5),
                ()->assertArrayEquals(output6,want6),
                ()->assertArrayEquals(output7,want7)
        );

    }

}