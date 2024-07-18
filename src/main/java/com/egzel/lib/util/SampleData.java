package com.egzel.lib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.IntToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.egzel.lib.model.abstraction.IBinaryTreeNode;

/**
 * TODO: Accept only valid edgelist, adjmtx and adjlist
 */
public class SampleData {

    private static ALogger<SampleData> LOGGER = new ALogger<>(SampleData.class);

    public static class AdjLists {
        /**
         * Directed
         * Nonweighted
         * No cyles (tree)
         * 8 vertices
         */
        public static final int[][] DNW_TREE_8_1 = {
                {1,2},
                {3},
                {4,5},
                {},
                {},
                {6,7},
                {},
                {}
        };
    }

    //
    // TODO: Handle isolated edges
    public static class EdgeLists {

        public static final int[][] UNDIRECTED_9_WEIGHTED_NEGATIVE_CYCLE_NEGATIVECYCLE_001 = {
                {0,  1,    1},
                {0,  3,    4},
                {2,  3,   14},
                {2,  5,    4},
                {6,  2,   -1},
                {1,  5,   -2},
                {1,  3,    3},
                {3,  6,    3},
                {5,  8,    2},
                {8,  6,    1},
                {3,  4,   -4},
                {4,  0,    2},
                {4,  6,  -10},
                {0,  7,  -12},
                {7,  5,    9},
        };

        public static final int[][] UNDIRECTED_9_WEIGHTED_NEGATIVE_CYCLE_001 = {
                {0,  1,    1},
                {0,  3,    4},
                {2,  3,   14},
                {2,  5,    4},
                {6,  2,   -1},
                {1,  5,   -2},
                {1,  3,    3},
                {3,  6,    3},
                {5,  8,    2},
                {8,  6,    1},
                {3,  4,   -4},
                {4,  0,    2},
                {4,  6,   10},
                {0,  7,  -12},
                {7,  5,    9},
        };


        public static final int[][] GRAPH01_NW_UNDCYX_DCY0_8 ={
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 2},
                {2, 3},
                {3, 4},
                {3, 5},
                {3, 6},
                {4, 6},
                {6, 7},
        };
    }

    /**
     * Adj martixes
     */
    public static class GraphOnlineRu {
        //https://graphonline.ru/en/?graph=zzCShjeZpDyKvysP
        public static final int[][] TOPSORT1 = {
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0}

        };
        //https://graphonline.ru/en/?graph=YMajQkoTRrBsYkOu
        public static final int[][] UNDIRECTED_CYCLED = {
                {0, 1, 0, 1, 1},
                {1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0}


        };

        //https://graphonline.ru/en/?graph=BDCoMquhTpQxGQUM
        public static final int[][] DIRECTED_CYCLED = {
                {0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0}

        };
        //https://graphonline.ru/en/?graph=lMNIweuFZCqKtVCM
        public static final int[][] DIRECTED_CYCLED1 = {
                {0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0}

        };

        // https://graphonline.ru/en/?graph=QVOSZByBcyggFfvj
        public static final int[][] DUCK_DIR_WEG = {
                {0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 2, 0, 0, 0},
                {0, 0, 0, 0, 11, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 11, 4, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 3, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0, 5},
                {0, 0, 1, 0, 0, 3, 0, 0, 3, 0},
                {0, 0, 0, 1, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 0, 5, 0}

        };

        // https://graphonline.ru/en/?graph=wzlBAENqKUvRjYLcZZcst
        public static final int[][] BROWN = {
                {0, 7, 5, 0, 0, 0},
                {7, 0, 0, 11, 0, 0},
                {5, 0, 0, 0, 0, 0},
                {0, 11, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 5, 0, 0}

        };

        // https://graphonline.ru/en/?graph=MXMQtkdsytbriBop
        public static final int[][] RS_0001 = {
                {0, 3, 5, 1, 0, 0, 0, 0, 0, 0},
                {3, 0, 1, 0, 0, 3, 0, 0, 0, 0},
                {5, 1, 0, 0, 6, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 4, 0, 0, 0, 0, 0},
                {0, 0, 6, 4, 0, 0, 0, 0, 0, 3},
                {0, 3, 1, 0, 0, 0, 2, 3, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 7, 0, 0},
                {0, 0, 0, 0, 0, 3, 7, 0, 2, 4},
                {0, 0, 0, 0, 0, 0, 0, 2, 0, 1},
                {0, 0, 0, 0, 3, 0, 0, 4, 1, 0}


        };
        // https://graphonline.ru/en/?graph=PCNQxNnEwXkqBAhd
        public static final int[][] UNWEIGHTED_UNDIRECTED_11_CIRCULAR = {

                {0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0}

        };

        // https://graphonline.ru/en/?graph=fYmuEGtCkJKZCwVXZZcst
        public static final int[][] SUMMER = {
                {0, 13, 6, 7, 0, 0},
                {13, 0, 0, 0, 0, 10},
                {6, 0, 0, 20, 1, 0},
                {7, 9, 20, 0, 13, 12},
                {0, 0, 1, 13, 0, 4},
                {0, 10, 0, 12, 4, 0}
        };

        //https://graphonline.ru/en/?graph=rqodCEvqMhnLAItU
        public static final int[][] WEIGHTED_GRAPH = {

                {0, 6, 10, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 12, 11, 14, 0, 0, 0, 0, 0},
                {10, 12, 0, 12, 0, 0, 8, 16, 0, 0},
                {0, 11, 12, 0, 0, 6, 3, 0, 0, 0},
                {0, 14, 0, 0, 0, 4, 0, 0, 6, 0},
                {0, 0, 0, 6, 4, 0, 0, 0, 12, 0},
                {0, 0, 8, 3, 0, 0, 0, 0, 16, 6},
                {0, 0, 16, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 0, 0, 6, 12, 16, 0, 0, 13},
                {0, 0, 0, 0, 0, 0, 6, 8, 13, 0}

        };

        public static final int[][] PLANAR_GRAPH_EDGELIST = {
                {0, 1, 3},
                {0, 2, -1},
                {0, 3, 1},
                {1, 2, 2},
                {1, 5, 4},
                {1, 7, 5},
                {2, 1, 6},
                {2, 3, 12},
                {2, 4, -4},
                {2, 7, 5},
                {3, 2, 3},
                {3, 4, 2},
                {4, 2, 2},
                {4, 3, 1},
                {4, 6, 1},
                {4, 7, 1},
                {5, 1, 1},
                {5, 6, 1},
                {5, 7, -23},
                {6, 4, 1},
                {6, 5, 1},
                {6, 7, 1},
                {7, 1, 1},
                {7, 2, 1},
                {7, 4, 1},
                {7, 5, 5},
                {7, 6, 1}
        };

        // https://graphonline.ru/en/?graph=uYWHxaDVSffcriuf
        public static final int[][] PLANAR_GRAPH = {

                {0, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 1, 0, 1},
                {0, 1, 1, 0, 1, 1, 1, 0}


        };


    }


    //https://csacademy.com/app/graph_editor/
    //edge lists
    public static class Csacademy {

        public static final int[][] NONDIRECTED_MULTICYCLED_WEIGHTED_12_15 = {
                {0  , 9  , 1  },
                {1  , 11 , 3  },
                {10 , 4  , 12 },
                {10 , 5  , 11 },
                {10 , 6  , 8  },
                {3  , 4  , 19 },
                {3  , 5  , 22 },
                {5  , 8  , -4 },
                {6  , 4  , 6  },
                {5  , 1  , 12 },
                {8  , 6  , 90 },
                {7  , 8  , 6  },
                {8  , 9  , 7  },
                {11 , 2  , 8  },
                {2  , 1  , 9  }
        };

        public static final int[][] DIRECTED_NONCYCLED_NONWEIGHTED_1_6 = {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {3, 4},
                {3, 6},
                {4, 5},
                {4, 6}

        };

        public static final int[][] UNDIRECTED_CYCLED_WEIGHTED_EDGELITST_7_8 = {
                {0, 1, 10},
                {0, 2, 1},
                {0, 3, 4},
                {1, 2, 3},
                {1, 4, 0},
                {2, 3, 2},
                {2, 5, 8},
                {3, 5, 2},
                {3, 6, 7},
                {4, 5, 1},
                {4, 7, 8},
                {5, 6, 6},
                {5, 7, 9},
                {6, 7, 12},
        };

        public static final int[][] NONDIRECTED_CYCLED_NONWEIGHTED_EDGELIST = {
                {0, 1},
                {1, 2},
                {3, 2},
                {4, 5},
                {4, 1},
                {6, 4},
                {7, 6},
                {8, 7},
                {9, 8},
                {10, 9},
                {5, 0},
        };

        public static final int[][] NONDIRECTED_NONCYCLED_EDGELIST_2 = {
                {0  ,1 , 1},
                {1  ,0 , 1},
                {1  ,2 , 1},
                {2  ,1 , 1},
                {3  ,2 , 1},
                {2  ,3 , 1},
                {4  ,5 , 1},
                {5  ,4 , 1},
                {4  ,1 , 1},
                {1  ,4 , 1},
                {6  ,4 , 1},
                {4  ,6 , 1},
                {7  ,6 , 1},
                {6  ,7 , 1},
                {8  ,7 , 1},
                {7  ,8 , 1},
                {9  ,8 , 1},
                {8  ,9 , 1},
                {10 ,9 , 1},
                {9  ,10, 1},
                {9 , 10 ,1 },
        } ;

        public static final int[][] NONDIRECTED_CYCLED_EDGELIST_3 = {
                {1, 2, 1},
                {2, 1, 1},
                {1, 3, 1},
                {3, 1, 1},
                {1, 4, 1},
                {4, 1, 1},
                {2, 3, 1},
                {3, 2, 1},
                {3, 4, 1},
                {4, 3, 1},
                {4, 5, 1},
                {5, 4, 1},
                {5, 0, 1},
                {0, 5, 1}
        };

        public static final int[][] NONDIRECTED_NONCYCLED_EDGELIST = {
                {3, 0, 1},
                {0, 3, 1},
                {1, 3, 1},
                {3, 1, 1},
                {2, 1, 1},
                {1, 2, 1},
                {4, 1, 1},
                {1, 4, 1},
                {5, 3, 1},
                {3, 5, 1}

        };
        public static final int[][] NONDIRECTED_CYCLED_EDGELIST_2 = {
                {0, 1, 1},
                {1, 0, 1},
                {2, 1, 1},
                {1, 2, 1},
                {3, 2, 1},
                {2, 3, 1},
                {3, 4, 1},
                {4, 3, 1},
                {4, 2, 1}
        };
        public static final int[][] NONDIRECTED_CYCLED_EDGELIST = {
                {3, 0, 1},
                {0, 3, 1},
                {1, 3, 1},
                {3, 1, 1},
                {2, 1, 1},
                {1, 2, 1},
                {4, 1, 1},
                {1, 4, 1},
                {4, 2, 1},
                {2, 4, 1},
                {5, 3, 1},
                {3, 5, 1},
                {5, 4, 1},
                {4, 5, 1}
        };

        public static final int[][] COURSE_SCHEDULE_1 = {
                {0, 1, 1},
                {3, 0, 1},
                // {1,3,1},
                {2, 1, 1},
                {4, 1, 1},
                {4, 2, 1},
                {5, 3, 1},
                {5, 4, 1}
        };

        public static final int[][] COURSE_SCHEDULE_2 = {
                {3, 0, 1},
                {1, 3, 1},
                {2, 1, 1},
                {4, 1, 1},
                {4, 2, 1},
                {5, 3, 1},
                {5, 4, 1},
                {0, 1, 1}

        };

        public static final int[][]
                COURSE_SCHEDULE_3 = {
                {0,  9,  1},
                {1,  11, 1},
                {10, 4,  1},
                {10, 5,  1},
                {10, 6,  1},
                {3,  4,  1},
                {3,  5,  1},
                {5,  8,  1},
                {8,  9,  1},
                {6,  4,  1},
                {5,  1,  1},
                {8,  6,  1},
                {7,  8,  1},
                {8,  9,  1},
                {11, 2,  1},
                //    {2 ,  1, 1}

        };


        public static final int[][] NONE = {
                {5, 4, 1},
                {1, 2, 1},
                {2, 3, 1},
                {1, 3, 1},
                {1, 5, 1},
                {0, 0, 1}
        };

        public static final int[][] CSA002 = {
                {0, 1,  1},
                {0, 2,  3},
                {2, 0,  3},
                {3, 0,  1},
                {0, 4,  2},
                {4, 0,  2},
                {3, 1, -4},
                {2, 4, -2},
                {6, 1, -5},
                {1, 6, -5},
                {6, 0,  2},
                {2, 0,  3},
                {5}

        };


        public static final int[][] DIRECTED_CYCLED_7_WEIGHTEDNEG = {

                {0, 1,  1 },
                {0, 2,  2 },
                {0, 4, -1},
                {2, 3, -3},
                {2, 5, -6},
                {1, 4,  2 },
                {4, 2,  9},
                {3, 4, -4},
                {5, 3, -8},
                {1, 3,  2},
                {2, 6,  4},
                {6, 0, -5}

        };
    }

    //graphs on disk, resources folder
    public static class ResourcesGraphs {

        /**
         * @return adj list
         */
        public static int[][][] getResourcesGraph_rs0001() {

            return new int[][][]{
                    {{1, 3}, {2, 5}, {3, 1}},
                    {{0, 3}, {2, 1}, {5, 3}},
                    {{4, 6}, {0, 5}, {1, 1}, {5, 1}},
                    {{0, 1}, {4, 4}},
                    {{3, 4}, {2, 6}},
                    {{2, 1}, {1, 3}, {6, 2}, {7, 3}},
                    {{5, 2}, {7, 7}},
                    {{6, 7}, {5, 3}, {9, 4}, {8, 2}},
                    {{7, 2}, {9, 1}},
                    {{8, 1}, {7, 4}}
            };

        }
    }



}
