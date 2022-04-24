package lib.util;

import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests static methods
 * <p>
 * Graphs can be visualized at;
 * (no negative edges, only matrices)
 * https://graphonline.ru/en/
 * <p>
 * <p>
 * (negative edges accepted, only edgelist)
 * https://csacademy.com/app/graph_editor/
 */
class DataUtilTest extends ProblemBase {
    @Test
    public void testConvertAdjListToAdjMatrix() {
        //TEST 1
        //
        // test1
        //
        //params

        int[][] input1 = {
                {2, 4, 5},
                {4, 5},
                {0, 3, 4},
                {2},
                {0, 1, 2, 5},
                {0, 1, 4},
        };
        boolean isDirected1 = false;
        //output
        int[][] want1 = {
                {0, 0, 1, 0, 1, 1},
                {0, 0, 0, 0, 1, 1},
                {1, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
        };
        int[][] output1 = DataUtil.Convertors.convertAdjListToAdjMatrix(input1, isDirected1);
        //TESt2
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
                {0, 1},
                {0, 0}
        };
        int[][] output2 = DataUtil.Convertors.convertAdjListToAdjMatrix(input2, isDirected2);
        //TEST3
        //
        // test3
        //
        //params
        int[][] input3 = {
                {0},
                {0, 1},
                {0, 1, 2},
                {0, 1, 2, 3},
        };
        boolean isDirected3 = true;
        //output
        int[][] want3 = {
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 1},
        };
        int[][] output3 = DataUtil.Convertors.convertAdjListToAdjMatrix(input3, isDirected3);
        //TEST4
        //
        // test4
        //
        //params
        int[][] input4 = {
                {1}, {}
        };
        boolean isDirected4 = true;
        //output
        int[][] want4 = {
                {0, 1},
                {0, 0}
        };
        int[][] output4 = DataUtil.Convertors.convertAdjListToAdjMatrix(input4, isDirected4, false);


        //TEST5
        //
        // test5
        //
        //params
        int[][] input5 = {
                {1, 2},
                {3, 4},
                {6},
        };
        boolean isDirected5 = true;
        //output
        int[][] want5 = {
                {0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},

        };
        int[][] output5 = DataUtil.Convertors.convertAdjListToAdjMatrix(input5, isDirected5, false);
        //TESt6
        //
        // test6
        //
        //params
        int[][] input6 = {
                {1, 2},
                {3, 4},
                {6},
        };
        boolean isDirected6 = false;
        //output
        int[][] want6 = {
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},

        };
        int[][] output6 = DataUtil.Convertors.convertAdjListToAdjMatrix(input6, isDirected6, false);
        //TEST7
        //
        // test7
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
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},


        };
        int[][] output7 = DataUtil.Convertors.convertAdjListToAdjMatrix(input7, isDirected7, false);


        //
        // assertion
        //
        assertAll("basic",
                () -> assertArrayEquals(output1, want1,"testConvertAdjListToAdjMatrix_test1"),
                () -> assertArrayEquals(output2, want2,"testConvertAdjListToAdjMatrix_test2"),
                () -> assertArrayEquals(output3, want3,"testConvertAdjListToAdjMatrix_test3"),
                () -> assertArrayEquals(output4, want4,"testConvertAdjListToAdjMatrix_test4"),
                () -> assertArrayEquals(output5, want5,"testConvertAdjListToAdjMatrix_test5"),
                () -> assertArrayEquals(output6, want6,"testConvertAdjListToAdjMatrix_test6"),
                () -> assertArrayEquals(output7, want7,"testConvertAdjListToAdjMatrix_test7")
        );

    }

    @Test
    public void testConvertAdjListToAdjMatrixWeighted() {
        //TEST1
        //
        // test1
        //
        //params
        int[][][] input1 = {
                {{2, 1}, {4, -2}, {5, 1}},
                {{4, 3}, {5, 11}},
                {{0, 3}, {3, -3}, {4, 1}},
                {{2}},
                {{0, 6}, {1, 3}, {2, 2}, {5, 11}},
                {{0, -1}, {1, 1}, {4, 21}},
        };
        boolean isDirected1 = true;
        //output
        int[][] want1 = {
                {0, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, -2, 1},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 11},
                {3, Integer.MAX_VALUE, 0, -3, 1, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {6, 3, 2, Integer.MAX_VALUE, 0, 11},
                {-1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 21, 0},
        };
        int[][] output1 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input1, isDirected1, false);

        //TEST2
        int[][][] input2 = {
                {{1, 2}},
                {}
        };
        boolean isDirected2 = true;
        //output
        int[][] want2 = {
                {0, 2},
                {Integer.MAX_VALUE, 0}
        };
        int[][] output2 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input2, isDirected2);

//TEST3

        int[][][] input3 = {
                {{0, 0}},
                {{0, 1}, {1, 2}},
                {{0, 5}, {1, 9}, {2, 2}},
                {{0, 6}, {1, 4}, {2, 4}, {3, 1}},
        };
        boolean isDirected3 = true;
        //output
        int[][] want3 = {
                {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {5, 9, 2, Integer.MAX_VALUE},
                {6, 4, 4, 1},
        };
        int[][] output3 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input3, isDirected3);

//Test4

        int[][][] input4 = {
                {{1}}
        };
        boolean isDirected4 = true;
        //output
        int[][] want4 = {
                {0, 0},
                {Integer.MAX_VALUE, 0}
        };
        int[][] output4 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input4, isDirected4);


//TEST5

        int[][][] input5 = {
                {{1, 4}, {2}},
                {{3, -1}, {4, 12}},
                {{6, 3}},
        };
        boolean isDirected5 = true;
        //output
        int[][] want5 = {
                {0, 4, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, -1, 12, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0},
        };
        int[][] output5 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input5, isDirected5, false);


//TEST6

        int[][][] input6 = {
                {{1, -2}, {2, 6}},
                {{3, 3}, {4, 8}},
                {{6, 12}},
        };
        boolean isDirected6 = false;
        //output
        int[][] want6 = {

                {0, -2, 6, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {-2, 0, Integer.MAX_VALUE, 3, 8, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {6, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 12},
                {Integer.MAX_VALUE, 3, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 12, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0},

        };
        int[][] output6 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input6, isDirected6);

//TEST7
        int[][][] input7 = {
                {},
                {},
                {},
        };
        boolean isDirected7 = false;
        //output
        int[][] want7 = {
                {0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0},


        };
        int[][] output7 = DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(input7, isDirected7, false);
        //
        // Assertion
        //
        assertArrayEquals(output1, want1, "testConvertAdjListToAdjMatrixWeighted_test1");
        assertArrayEquals(output2, want2, "testConvertAdjListToAdjMatrixWeighted_test2");
        assertArrayEquals(output3, want3, "testConvertAdjListToAdjMatrixWeighted_test3");
        assertArrayEquals(output4, want4, "testConvertAdjListToAdjMatrixWeighted_test4");
        assertArrayEquals(output5, want5, "testConvertAdjListToAdjMatrixWeighted_test5");
        assertArrayEquals(output6, want6, "testConvertAdjListToAdjMatrixWeighted_test6");
        assertArrayEquals(output7, want7, "testConvertAdjListToAdjMatrixWeighted_test7");

    }

    @Test
    public void testConvertAdjMatrixToAdjList() {
        //test1
        int[][] input1 = {
                {0, 0, 1, 0, 1, 1},
                {0, 0, 0, 0, 1, 1},
                {1, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
        };
        boolean isDirected1 = false;
        //output
        int[][] want1 = {
                {2, 4, 5},
                {4, 5},
                {0, 3, 4},
                {2},
                {0, 1, 2, 5},
                {0, 1, 4},
        };

        int[][] output1 = DataUtil.Convertors.convertAdjMatrixToAdjList(input1, isDirected1);

//test2

        int[][] input2 = {
                {0, 1},
                {0, 0}
        };

        boolean isDirected2 = true;

        int[][] want2 = {
                {1},
                {}
        };

        //output

        int[][] output2 = DataUtil.Convertors.convertAdjMatrixToAdjList(input2, isDirected2);

//test3

        int[][] input3 = {
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 1},
        };
        boolean isDirected3 = true;
        //output

        int[][] want3 = {
                {0},
                {0, 1},
                {0, 1, 2},
                {0, 1, 2, 3},
        };

        int[][] output3 = DataUtil.Convertors.convertAdjMatrixToAdjList(input3, isDirected3);

//test4

        int[][] input4 = {
                {0, 0,0},
                {0, 0,1},
                {0, 0,0}
        };
        boolean isDirected4 = true;

        //output
        int[][] want4 = {
                {},
                {2},
                {}
        };

        int[][] output4 = DataUtil.Convertors.convertAdjMatrixToAdjList(input4, isDirected4);

//test 5
        int[][] input5 = {
                {0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},

        };

        boolean isDirected5 = true;
        //output
        int[][] want5 = {
                {1, 2},
                {3, 4},
                {6},
                {},
                {},
                {},
                {}
        };
        int[][] output5 = DataUtil.Convertors.convertAdjMatrixToAdjList(input5, isDirected5);

//test6

        int[][] input6 = {
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},

        };
        boolean isDirected6 = false;
        //output

        int[][] want6 = {
                {1, 2},
                {0, 3, 4},
                {0, 6},
                {1},
                {1},
                {},
                {2}
        };
        int[][] output6 = DataUtil.Convertors.convertAdjMatrixToAdjList(input6, isDirected6);

//test7
        int[][] input7 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},


        };

        boolean isDirected7 = true;
        //output

        int[][] want7 = {
                {},
                {},
                {},
        };

        int[][] output7 = DataUtil.Convertors.convertAdjMatrixToAdjList(input7, isDirected7);

        //assertion
        assertArrayEquals(output1, want1, "testConvertAdjMatrixToAdjList_test1");
        assertArrayEquals(output2, want2, "testConvertAdjMatrixToAdjList_test2");
        assertArrayEquals(output3, want3, "testConvertAdjMatrixToAdjList_test3");
        assertArrayEquals(output4, want4, "testConvertAdjMatrixToAdjList_test4");
        assertArrayEquals(output5, want5, "testConvertAdjMatrixToAdjList_test5");
        assertArrayEquals(output6, want6, "testConvertAdjMatrixToAdjList_test6");
        assertArrayEquals(output7, want7, "testConvertAdjMatrixToAdjList_test7");
    }

    @Test
    public void testConvertAdjMatrixToAdjListWeighted(){
        //
//test1
        String testName1 = "convertAdjMatrixToAdjListWeighted_test1";
        int[][] input1 = {
                {0, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, -2, 1},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 3, 11},
                {3, Integer.MAX_VALUE, 0, -3, 1, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {6, 3, 2, Integer.MAX_VALUE, 0, 11},
                {-1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 21, 0},
        };
        boolean isDirected1 = true;
        //output

        int[][][] want1 = {
                {{2, 1}, {4, -2}, {5, 1}},
                {{4, 3}, {5, 11}},
                {{0, 3}, {3, -3}, {4, 1}},
                {{2,0}},
                {{0, 6}, {1, 3}, {2, 2}, {5, 11}},
                {{0, -1}, {1, 1}, {4, 21}},
        };
       // LOGGER.info(testName1 + " output prepared to execute");
        int[][][] output1 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input1, isDirected1);
// TEST2
        String testName2 = "convertAdjMatrixToAdjListWeighted_test2";
        int[][] input2 = {
                {0, 2},
                {Integer.MAX_VALUE, 0}
        };
        boolean isDirected2 = true;
        //output
        int[][][] want2 = {
                {{1, 2}},
                {}
        };
       // LOGGER.info(testName2 + " output prepared to execute");
        int[][][] output2 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input2, isDirected2);

//TEST3
        String testName3 = "convertAdjMatrixToAdjListWeighted_test3";
        int[][] input3 = {
                {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {5, 9, 2, Integer.MAX_VALUE},
                {6, 4, 4, 1},
        };
        boolean isDirected3 = true;
        //output

        int[][][] want3 = {
                {},
                {{0, 1}, {1, 2}},
                {{0, 5}, {1, 9}, {2, 2}},
                {{0, 6}, {1, 4}, {2, 4}, {3, 1}},
        };
        //LOGGER.info(testName3 + " output prepared to execute");
        int[][][] output3 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input3, isDirected3);
//TEST4
        String testName4 = "convertAdjMatrixToAdjListWeighted_test4";
        int[][] input4 = {
                {0, 0},
                {Integer.MAX_VALUE, 0}
        };
        boolean isDirected4 = true;
        //output

        int[][][] want4 = {
                {{1,0}},
                {}
        };
        //LOGGER.info(testName4 + " output prepared to execute");
        int[][][] output4 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input4, isDirected4);
//TEST5
        String testName5 = "convertAdjMatrixToAdjListWeighted_test5";
        int[][] input5 = {
                {0, 4, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, -1, 12, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0},
        };
        boolean isDirected5 = true;
        //output

        int[][][] want5 = {
                {{1, 4}, {2,0}},
                {{3, -1}, {4, 12}},
                {{6, 3}},
                {},
                {},
                {},
                {},
        };
        //LOGGER.info(testName5 + " output prepared to execute");
        int[][][] output5 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input5, isDirected5);
//TEST6
        String testName6 = "convertAdjMatrixToAdjListWeighted_test6";
        int[][] input6 = {

                {0, -2, 6, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {-2, 0, Integer.MAX_VALUE, 3, 8, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {6, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 12},
                {Integer.MAX_VALUE, 3, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 12, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0},

        };
        boolean isDirected6 = false;
        //output

        int[][][] want6 = {
                {{1, -2}, {2, 6}},
                {{0,-2},{3, 3}, {4, 8}},
                {{0,6},{6, 12}},
                {{1,3}},
                {{1,8}},
                {},
                {{2,12}},
        };
        //LOGGER.info(testName6 + " output prepared to execute");
        int[][][] output6 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input6, isDirected6);
//TEST7
        String testName7 = "convertAdjMatrixToAdjListWeighted_test7";

        int[][] input7 = {
                {0, 0, 0},
                {0, 0, Integer.MAX_VALUE},
                {0, Integer.MAX_VALUE, 0},


        };
        boolean isDirected7 = false;
        //output
        int[][][] want7 = {
                {{1,0},{2,0}},
                {{0,0}},
                {{0,0}},
        };
        //LOGGER.info(testName7 + " output prepared to execute");
        int[][][] output7 = DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input7, isDirected7);


        //Assertions
        assertArrayEquals(output1,want1,testName1);
        assertArrayEquals(output2,want2,testName2);
        assertArrayEquals(output3,want3,testName3);
        assertArrayEquals(output4,want4,testName4);
        assertArrayEquals(output5,want5,testName5);
        assertArrayEquals(output6,want6,testName6);
        assertArrayEquals(output7,want7,testName7);

    }
}