package lib.util;

import lib.model.abstraction.IBinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * TODO: Accept only valid edgelist, adjmtx and adjlist
 */
public class SampleData {

    private static ALogger<SampleData> LOGGER = new ALogger<>(SampleData.class);

    /**
     * Adj martixes
     */
    public static class GraphOnlineRu {
        //http://graphonline.ru/en/?graph=zzCShjeZpDyKvysP
        public static final int[][] TOPSORT1={
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0}

        };
        //http://graphonline.ru/en/?graph=YMajQkoTRrBsYkOu
        public static final int[][] UNDIRECTED_CYCLED = {
                {0, 1, 0, 1, 1},
                {1, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0}




        };

        //http://graphonline.ru/en/?graph=BDCoMquhTpQxGQUM
        public static final int[][] DIRECTED_CYCLED = {
                {0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0}

        };
        //http://graphonline.ru/en/?graph=lMNIweuFZCqKtVCM
        public static final int[][] DIRECTED_CYCLED1 = {
                {0, 1, 0, 0, 0, 0, 0, 0 },
                {0, 0, 0, 0, 0, 0, 0, 0 },
                {0, 0, 0, 0, 1, 0, 1, 0 },
                {0, 0, 1, 0, 0, 0, 1, 0 },
                {1, 0, 0, 0, 0, 0, 0, 1 },
                {0, 0, 0, 0, 1, 0, 0, 0 },
                {0, 0, 0, 0, 0, 0, 0, 0 },
                {1, 0, 0, 0, 0, 1, 0, 0 }

        };

        // http://graphonline.ru/en/?graph=QVOSZByBcyggFfvj
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

        // http://graphonline.ru/en/?graph=wzlBAENqKUvRjYLcZZcst
        public static final int[][] BROWN = {
                {0, 7, 5, 0, 0, 0},
                {7, 0, 0, 11, 0, 0},
                {5, 0, 0, 0, 0, 0},
                {0, 11, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 5, 0, 0}

        };

        // http://graphonline.ru/en/?graph=MXMQtkdsytbriBop
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
        // http://graphonline.ru/en/?graph=PCNQxNnEwXkqBAhd
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

        // http://graphonline.ru/en/?graph=fYmuEGtCkJKZCwVXZZcst
        public static final int[][] SUMMER = {
                {0, 13, 6, 7, 0, 0},
                {13, 0, 0, 0, 0, 10},
                {6, 0, 0, 20, 1, 0},
                {7, 9, 20, 0, 13, 12},
                {0, 0, 1, 13, 0, 4},
                {0, 10, 0, 12, 4, 0}
        };

        //http://graphonline.ru/en/?graph=rqodCEvqMhnLAItU
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
                {0,1,3},
                {0,2,-1},
                {0,3,1},
                {1,2,2},
                {1,5,4},
                {1,7,5},
                {2,1,6},
                {2,3,12},
                {2,4,-4},
                {2,7,5},
                {3,2,3},
                {3,4,2},
                {4,2,2},
                {4,3,1},
                {4,6,1},
                {4,7,1},
                {5,1,1},
                {5,6,1},
                {5,7,-23},
                {6,4,1},
                {6,5,1},
                {6,7,1},
                {7,1,1},
                {7,2,1},
                {7,4,1},
                {7,5,5},
                {7,6,1}
        };

        // http://graphonline.ru/en/?graph=uYWHxaDVSffcriuf
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

        public static int[][] NONDIRECTED_MULTICYCLED_WEIGHTED_12_15 = {
                {0 , 9,   1},
                {1 ,11,   3},
                {10, 4,  12},
                {10, 5,  11},
                {10, 6,   8},
                {3 , 4,  19},
                {3 , 5,  22},
                {5 , 8,  -4},
                {6 , 4,   6},
                {5 , 1,  12},
                {8 , 6,  90},
                {7 , 8,   6},
                {8 , 9,   7},
                {11, 2,   8},
                {2 , 1,   9}
        };

        public static int [][] DIRECTED_NONCYCLED_NONWEIGHTED_1_6 = {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {3, 4},
                {3, 6},
                {4, 5},
                {4, 6}

        };

        public static int[][] UNDIRECTED_CYCLED_WEIGHTED_EDGELITST_7_8 = {
                {0, 1, 10},
                {0, 2,  1},
                {0, 3,  4},
                {1, 2,  3},
                {1, 4,  0},
                {2, 3,  2},
                {2, 5,  8},
                {3, 5,  2},
                {3, 6,  7},
                {4, 5,  1},
                {4, 7,  8},
                {5, 6,  6},
                {5, 7,  9},
                {6, 7, 12},
        };

        public static int[][] NONDIRECTED_CYCLED_NONWEIGHTED_EDGELIST = {
                {0 ,  1},
                {1 ,  2},
                {3 ,  2},
                {4 ,  5},
                {4 ,  1},
                {6 ,  4},
                {7 ,  6},
                {8 ,  7},
                {9 ,  8},
                {10,  9},
                {5,  0},
        };

        public static int[][] NONDIRECTED_NONCYCLED_EDGELIST_2 ={
                {0 , 1 , 1},
                {1 , 0 , 1},
                {1 , 2 , 1},
                {2 , 1 , 1},
                {3 , 2 , 1},
                {2 , 3 , 1},
                {4 , 5 , 1},
                {5 , 4 , 1},
                {4 , 1 , 1},
                {1 , 4 , 1},
                {6 , 4 , 1},
                {4 , 6 , 1},
                {7 , 6 , 1},
                {6 , 7 , 1},
                {8 , 7 , 1},
                {7 , 8 , 1},
                {9 , 8 , 1},
                {8 , 9 , 1},
                {10, 9 , 1},
                {9 , 10, 1},
                {9 , 10, 1},
                {10 , 8, 1},
                {8 , 10, 1},
        };

        public static int[][] NONDIRECTED_CYCLED_EDGELIST_3 = {
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

        public static int[][] NONDIRECTED_NONCYCLED_EDGELIST = {
                {3,0,1},
                {0,3,1},
                {1,3,1},
                {3,1,1},
                {2,1,1},
                {1,2,1},
                {4,1,1},
                {1,4,1},
                {5,3,1},
                {3,5,1}

        };
        public static int[][] NONDIRECTED_CYCLED_EDGELIST_2 = {
                {0,1,1},
                {1,0,1},
                {2,1,1},
                {1,2,1},
                {3,2,1},
                {2,3,1},
                {3,4,1},
                {4,3,1},
                {4,2,1}
        };
        public static int[][] NONDIRECTED_CYCLED_EDGELIST = {
                {3,0,1},
                {0,3,1},
                {1,3,1},
                {3,1,1},
                {2,1,1},
                {1,2,1},
                {4,1,1},
                {1,4,1},
                {4,2,1},
                {2,4,1},
                {5,3,1},
                {3,5,1},
                {5,4,1},
                {4,5,1}
        };

        public static  int[][] COURSE_SCHEDULE_1 = {
                {0,1,1},
                {3,0,1},
               // {1,3,1},
                {2,1,1},
                {4,1,1},
                {4,2,1},
                {5,3,1},
                {5,4,1}
        };

        public static  int[][] COURSE_SCHEDULE_2 = {
                {3,0,1},
                {1,3,1},
                {2,1,1},
                {4,1,1},
                {4,2,1},
                {5,3,1},
                {5,4,1},
                {0,1,1}

        };

        public static  int[][]
                COURSE_SCHEDULE_3 = {
                {0 ,  9, 1},
                {1 , 11, 1},
                {10,  4, 1},
                {10,  5, 1},
                {10,  6, 1},
                {3 , 4 , 1},
                {3 , 5 , 1},
                {5 , 8 , 1},
                {8 , 9 , 1},
                {6 , 4 , 1},
                {5 , 1 , 1},
                {8 , 6 , 1},
                {7 , 8 , 1},
                {8 , 9 , 1},
                {11,  2, 1},
            //    {2 ,  1, 1}

        };


        public static int [][] NONE ={
                {5,4,1},
                        {1,2,1},
                                {2,3,1},
                                        {1,3,1},
                                                {1,5,1},
                                                        {0,0,1}
        };

        public static int[][] CSA002={
                {0,1, 1},
                {0,2, 3},
                {2,0, 3},
                {3,0, 1},
                {0,4, 2},
                {4,0, 2},
                {3,1,-4},
                {2,4,-2},
                {6,1,-5},
                {1,6,-5},
                {6,0, 2},
                {2,0, 3},
                {5}

        };


        public static final int[][] CSA001 = {

                {0, 1,  1},
                {0, 2,  2},
                {0, 4, -1},
                {2, 3, -3},
                {2, 5, -6},
                {1, 4,  2},
                {4, 2,  9},
                {3, 4, -4},
                {5, 3,  8},
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

    // converts adj mtx list edges etc
    public static class Convertors {

        private static void emptyListAndNullEntryCheck(int [][] d2array){
            boolean validatorBoolean = d2array.length == 0;
            if(validatorBoolean){
                LOGGER.info("EdgeList can not be empty");
                System.exit(-1);
            }
            validatorBoolean = IntStream.range(0,d2array.length).anyMatch(a->d2array[a]==null);
            if(validatorBoolean){
                LOGGER.info("EdgeList can not have null entries");
            }
        }

        /**
         *
         * VERTEX ID Starts from 0
         * @param edges
         * @param isDirected
         * @return
         */
        public static int [][] convertEdgeListToAdjMatrix(int [][] edges, boolean isDirected){
            // validate
            emptyListAndNullEntryCheck(edges);
            // validate

            var streamData = new Object(){
                 int max = Integer.MIN_VALUE;
            };

            IntStream.range(0,edges.length).forEach(edge->{
                IntStream.range(0,edges[edge].length).filter(x->x<2).forEach(edgeelement->{
                    if(edges[edge][edgeelement]>streamData.max){
                        streamData.max = edges[edge][edgeelement];
                    }
                });
            });

            int numberOfVertex = streamData.max+1;
            // adjmtx
            int[][] result = new int[numberOfVertex][numberOfVertex];

            IntStream.range(0,edges.length).forEach(edge->{
                int[] ed = edges[edge];
                int src = ed[0];
                int dst = ed[1];
                int wt = ed.length == 3 ? ed[2]:1;
                result[src][dst] = wt;
                if(!isDirected){
                    result[dst][src] = wt;
                }
            });

            return result;
        }

        public static int[][] _convertEdgelistToAdjMtx(int[][] edges) {
            // {{1,2,1},{1,3,1}..}//

            int numOfVertices = Integer.MIN_VALUE;
            for(int i =0; i<edges.length;i++){

                if(edges[i].length>0){
                    if(edges[i][0] > numOfVertices) numOfVertices = edges[i][0];
                }
                if(edges[i].length>1){
                    if(edges[i][1] > numOfVertices) numOfVertices = edges[i][1];
                }

            }

            numOfVertices = numOfVertices+1;

            int[][] res = new int[numOfVertices][numOfVertices];

            for (int i = 0; i < edges.length; i++) {

                // {1,4,5} // {1,5}
                int fridx = Integer.MAX_VALUE;
                try {
                    fridx = edges[i][0];
                }catch (Exception e){
                    fridx = Integer.MAX_VALUE;
                }
                int toidx = Integer.MAX_VALUE;
                try {
                   toidx = edges[i][1];
                }catch (Exception e){
                    toidx = Integer.MAX_VALUE;
                }
                int weight = 0;
                try {
                    weight = edges[i][2];
                }catch (Exception e){
                    weight = 0;
                }

                if(fridx != Integer.MAX_VALUE && toidx != Integer.MAX_VALUE){
                    res[fridx][toidx] = weight;
                }
            }

            return res;
        }

        public static int[][] _convertEdgelistToAdjMtx(int[][] edges, int paramfromidx, int paramtoidx, Object paramweightidx) {
            // {{1,2,1},{1,3,1}..}//

            int numOfVertices = Integer.MIN_VALUE;
            for(int i =0; i<edges.length;i++){

                if(edges[i].length>paramfromidx){
                    if(edges[i][paramfromidx] > numOfVertices) numOfVertices = edges[i][paramfromidx];
                }
                if(edges[i].length>paramtoidx){
                    if(edges[i][paramtoidx] > numOfVertices) numOfVertices = edges[i][paramtoidx];
                }

            }

            int[][] res = new int[numOfVertices][numOfVertices];

            for (int i = 0; i < edges.length; i++) {

                // {1,4,5} // {1,5}
                int fridx = Integer.MAX_VALUE;
                try {
                    fridx = edges[i][0];
                }catch (Exception e){
                    fridx = Integer.MAX_VALUE;
                }
                int toidx = Integer.MAX_VALUE;
                try {
                    toidx = edges[i][1];
                }catch (Exception e){
                    toidx = Integer.MAX_VALUE;
                }
                int weight = 0;
                try {
                    if(paramweightidx != null){

                        weight = edges[i][(int)paramweightidx];
                    }
                }catch (Exception e){
                    weight = 0;
                }

                if(fridx != Integer.MAX_VALUE && toidx != Integer.MAX_VALUE){
                    res[fridx][toidx] = weight;
                }
            }

            return res;
        }

        public static int[][] _convertAdjMtxToEdgeList(int[][] adjMtx) {

            List<int[]> edges = new ArrayList<>();
            for (int i = 0; i < adjMtx.length; i++) {

                int from = i;
                for (int j = 0; j < adjMtx[i].length; j++) {
                    if (adjMtx[i][j] == 0) continue;
                    int to = j;
                    int weight = j == 0 ? Integer.MAX_VALUE : adjMtx[i][j];
                    int[] edge = {i, j, weight};
                    if(weight != Integer.MAX_VALUE)
                        edges.add(edge);
                }
            }


            int[][] res = new int[edges.size()][3];

            for (int i = 0; i < edges.size(); i++) {
                res[i] = edges.get(i);
            }

            return res;
        }

        public static int[][][] _convertAdjmtxToAdjList(int[][] adjmtx) {

            int[][][] adjList = new int[adjmtx.length][][];

            for (int i = 0; i < adjmtx.length; i++) {

                int[][] neighbours = null;

                int nbNumber = 0;

                for (int k = 0; k < adjmtx[i].length; k++) {

                    if (adjmtx[i][k] != 0) {
                        nbNumber++;
                    }
                }


                int insertIndex = 0;
                for (int j = 0; j < adjmtx[i].length; j++) {

                    if (adjmtx[i][j] != 0) {
                        int currentPointIndex = i;
                        int neighbourIndex = j;
                        int weight = adjmtx[i][j];
                        if (adjList[currentPointIndex] == null) {
                            adjList[currentPointIndex] = new int[nbNumber][2];
                        }
                        int idx =
                                adjList[i][insertIndex][0] = neighbourIndex;
                        adjList[i][insertIndex][1] = weight;
                        insertIndex++;
                    }
                }
            }

            return adjList;
        }

        public static int[][] _convertAdjListToAdjMtx(int[][][] adjlist) {

            int[][] res = new int[adjlist.length][adjlist.length];

            for (int i = 0; i < adjlist.length; i++) {

                for (int j = 0; j < adjlist[i].length; j++) {
                    int nbid = adjlist[i][j][0];
                    int weight = adjlist[i][j][1];

                    res[i][nbid] = weight;
                }

            }

            return res;

        }


    }

    //generates dummy data, array matrix etc
    public static class Generators {

        public static int [][] generateRandomMatrix(int rownum, int colnum, int low, int high){

            int [][] res = new int[rownum][colnum];
            for(int i = 0; i < rownum; i++){
                if(res[i]==null)res[i] = new int[colnum];
                for(int j = 0; j<colnum; j++){
                    int val =(int) SampleData.Generators.getRandom(low,high);
                    res[i][j] = val;
                }
            }

            return  res;
        }

        public static int[] generateIntArray(int length, int low, int high) {

            int[] res = new int[length];

            for (int i = 0; i < length; i++) {

                res[i] = (int) getRandom(low, high);
            }

            return res;
        }

        public static int[][][] generateRandomGraph(int numberOfVertex) {

            StringBuilder sb = new StringBuilder();
            int[][][] graph = new int[numberOfVertex][][];
            int edgeNummer = 0;
            for (int i = 0; i < numberOfVertex; i++) {

                // number of edges


                if (graph[i] == null) {

                    edgeNummer = (int) SampleData.Generators.getRandom(1, numberOfVertex - 1);
                    graph[i] = new int[edgeNummer][];
                }

                for (int j = 0; j < graph[i].length; j++) {
                    int noeighbourId = (int) SampleData.Generators.getRandom(0, numberOfVertex - 1);
                    int weight = (int) SampleData.Generators.getRandom(0, numberOfVertex - 1);
                    graph[i][j] = new int[]{noeighbourId, weight};
                    if (j == graph[i].length - 1) {
                        sb.append("{" + noeighbourId + "," + weight + "}").append("\n");
                    } else {
                        sb.append("{" + noeighbourId + "," + weight + "},");
                    }
                }


            }


            //   System.out.println(sb.toString());
            LOGGER.info("RANDOM GRAPH:\n" + sb.toString());


            return graph;

        }

        public static int[][][] generateRandomGraph(int numberOfVertex, boolean silent) {

            StringBuilder sb = new StringBuilder();
            int[][][] graph = new int[numberOfVertex][][];
            int edgeNummer = 0;
            for (int i = 0; i < numberOfVertex; i++) {

                // number of edges


                if (graph[i] == null) {

                    edgeNummer = (int) SampleData.Generators.getRandom(1, numberOfVertex - 1);
                    graph[i] = new int[edgeNummer][];
                }

                for (int j = 0; j < graph[i].length; j++) {
                    int noeighbourId = (int) SampleData.Generators.getRandom(0, numberOfVertex - 1);
                    int weight = (int) SampleData.Generators.getRandom(0, numberOfVertex - 1);
                    graph[i][j] = new int[]{noeighbourId, weight};
                    if (j == graph[i].length - 1) {
                        sb.append("{" + noeighbourId + "," + weight + "}").append("\n");
                    } else {
                        sb.append("{" + noeighbourId + "," + weight + "},");
                    }
                }


            }

            if (!silent)
                LOGGER.info("RANDOM GRAPH:\n" + sb.toString());


            return graph;

        }

        public static double getRandom(double low, double high) {
            double rand = (int) Math.floor(Math.random() * (high - low + 1) + low);
            return rand;
        }


    }

    //print data
    //trees, graphs, matrices etc
    public static class Printers{

        public static void printBSTree(IBinaryTreeNode root) {
            List<List<String>> lines = new ArrayList<List<String>>();

            List<IBinaryTreeNode> level = new ArrayList<IBinaryTreeNode>();
            List<IBinaryTreeNode> next = new ArrayList<IBinaryTreeNode>();

            level.add(root);
            int nn = 1;

            int widest = 0;

            while (nn != 0) {
                List<String> line = new ArrayList<String>();

                nn = 0;

                for (IBinaryTreeNode n : level) {
                    if (n == null) {
                        line.add(null);

                        next.add(null);
                        next.add(null);
                    } else {
                        String aa = n.toString();
                        line.add(aa);
                        if (aa.length() > widest) widest = aa.length();

                        next.add(n.getLeft());
                        next.add(n.getRight());

                        if (n.getLeft() != null) nn++;
                        if (n.getRight() != null) nn++;
                    }
                }

                if (widest % 2 == 1) widest++;

                lines.add(line);

                List<IBinaryTreeNode> tmp = level;
                level = next;
                next = tmp;
                next.clear();
            }

            int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
            for (int i = 0; i < lines.size(); i++) {
                List<String> line = lines.get(i);
                int hpw = (int) Math.floor(perpiece / 2f) - 1;

                if (i > 0) {
                    for (int j = 0; j < line.size(); j++) {

                        // split node
                        char c = ' ';
                        if (j % 2 == 1) {
                            if (line.get(j - 1) != null) {
                                c = (line.get(j) != null) ? '┴' : '┘';
                            } else {
                                if (j < line.size() && line.get(j) != null) c = '└';
                            }
                        }
                        System.out.print(c);

                        // lines and spaces
                        if (line.get(j) == null) {
                            for (int k = 0; k < perpiece - 1; k++) {
                                System.out.print(" ");
                            }
                        } else {

                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? " " : "─");
                            }
                            System.out.print(j % 2 == 0 ? "┌" : "┐");
                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? "─" : " ");
                            }
                        }
                    }
                    System.out.println();
                }

                // print line of numbers
                for (int j = 0; j < line.size(); j++) {

                    String f = line.get(j);
                    if (f == null) f = "";
                    int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                    int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                    // a number
                    for (int k = 0; k < gap1; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(f);
                    for (int k = 0; k < gap2; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();

                perpiece /= 2;
            }
        }

        //
        //https://csacademy.com/app/graph_editor/
        public static String stringifyEdgeList(int[][] edgelist) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < edgelist.length; i++) {
                for (int j = 0; j < edgelist[i].length; j++) {
                    if (j != edgelist[i].length - 1) {
                        stringBuilder.append(edgelist[i][j] + " ");
                    } else {
                        stringBuilder.append(edgelist[i][j] + "\n");
                    }
                }
            }
            return stringBuilder.toString();
        }

        public static  void PrintReport(ALogger logger, Map<Object,Object> data){
            for (Map.Entry entry : data.entrySet()){

                logger.info(entry.getKey()+":"+entry.getValue());
            }
        }

        public static String stringfyAdjacencyList(int[][][] adjlist) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < adjlist.length; i++) {
                for (int j = 0; j < adjlist[i].length; j++) {
                    int nbour = adjlist[i][j][0];
                    int weight = adjlist[i][j][1];

                    adjlist[i][j] = new int[]{nbour, weight};

                    if (j == adjlist[i].length - 1) {
                        sb.append("{" + nbour + "," + weight + "}").append("\n");
                    } else {
                        sb.append("{" + nbour + "," + weight + "},");
                    }
                }


            }

            return sb.toString();

        }

        public static String stringifyAdjacencyMatrix(int[][] adjmtx) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < adjmtx.length; i++) {
                for (int j = 0; j < adjmtx[i].length; j++) {
                    stringBuilder.append(adjmtx[i][j] + ",");
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }

        public static String stringifyArray(int [] arr){

            return Arrays.stream(arr).boxed().collect(Collectors.toList()).toString();
        }
    }

}
