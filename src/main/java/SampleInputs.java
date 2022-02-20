import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SampleInputs {

    private  static  ALogger<SampleInputs> LOGGER = new ALogger<>(SampleInputs.class);

    public static int[][][] getResourcesGraph_rs0001() {

            return new int[][][]{
                    {{1, 3}, {2, 5}, {3, 1}},
                    {{0, 3}, {2, 1}, {5,3}},
                    {{4, 6}, {0, 5}, {1,1},{5,1}},
                    {{0, 1}, {4, 4}},
                    {{3,4}, {2, 6}},
                    {{2,1}, {1, 3},{6,2},{7,3}},
                    {{5,2}, {7, 7}},
                    {{6,7}, {5, 3},{9,4},{8,2}},
                    {{7,2}, {9, 1}},
                    {{8,1},{7,4}}
            };

    }

    public static  class GraphOnlineRu{

        // http://graphonline.ru/en/?graph=wzlBAENqKUvRjYLcZZcst
        public static final int[][] BROWN = {
                {0, 7, 5, 0, 0, 0, 0},
                        {7, 0, 0, 11, 0, 0, 0},
                                {5, 0, 0, 0, 0, 0, 0},
                                        {0, 11, 1, 0, 1, 0, 3},
                                                {0, 0, 0, 1, 0, 1, 0},
                                                        {0, 0, 0, 5, 0, 0, 0},
                                                                {0, 0, 1, 0, 0, 0, 0}

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

        public static int[][][] convertToAdjacencyList(int[][] adjmtx){

            int [][][] adjList = new int[adjmtx.length][][];

            for(int i =0; i<adjmtx.length;i++){

                int [][] neighbours = null;

                int nbNumber = 0;

                for(int k = 0; k<adjmtx[i].length;k++){

                    if (adjmtx[i][k] != 0){
                        nbNumber++;
                    }
                }


                int insertIndex = 0;
               for(int j = 0; j < adjmtx[i].length;j++){

                   if(adjmtx[i][j] != 0){
                       int currentPointIndex = i;
                       int neighbourIndex = j;
                       int weight = adjmtx[i][j];
                       if(adjList[currentPointIndex] == null){
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

    }

    public static int[][][] printAdjacencyList(int[][][] graph, String graphName){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < graph.length; i ++){
            for(int j = 0; j < graph[i].length; j++){
                int nbour = graph[i][j][0];
                int weight =graph[i][j][1];

                graph[i][j] =  new int[]{nbour,weight};

                if(j==graph[i].length-1){
                    sb.append("{"+nbour+","+weight+"}").append("\n");
                }else {
                    sb.append("{"+nbour+","+weight+"},");
                }
            }


        }

        LOGGER.info(graphName.toUpperCase()+"_GRAPH:\n"+sb.toString());
        return graph;

    }

    public static int[][][] generateRandomGraph(int numberOfVertex){

        StringBuilder sb = new StringBuilder();
        int[][][]  graph = new int[numberOfVertex][][];
        int edgeNummer = 0;
        for(int i = 0; i < numberOfVertex; i ++){

            // number of edges


            if(graph[i] == null){

                edgeNummer = (int) SampleInputs.getRandom(1,numberOfVertex-1);
                graph[i] = new int[edgeNummer][];
            }

            for(int j = 0; j < graph[i].length; j++){
                int noeighbourId =(int) SampleInputs.getRandom(0,numberOfVertex-1);
                int weight =(int) SampleInputs.getRandom(0,numberOfVertex-1);
                graph[i][j] =  new int[]{noeighbourId,weight};
                if(j==graph[i].length-1){
                    sb.append("{"+noeighbourId+","+weight+"}").append("\n");
                }else {
                    sb.append("{"+noeighbourId+","+weight+"},");
                }
            }


        }


     //   System.out.println(sb.toString());
        LOGGER.info("RANDOM GRAPH:\n"+sb.toString());





        return graph;

    }

    public static int[][][] generateRandomGraph(int numberOfVertex, boolean silent){

        StringBuilder sb = new StringBuilder();
        int[][][]  graph = new int[numberOfVertex][][];
        int edgeNummer = 0;
        for(int i = 0; i < numberOfVertex; i ++){

            // number of edges


            if(graph[i] == null){

                edgeNummer = (int) SampleInputs.getRandom(1,numberOfVertex-1);
                graph[i] = new int[edgeNummer][];
            }

            for(int j = 0; j < graph[i].length; j++){
                int noeighbourId =(int) SampleInputs.getRandom(0,numberOfVertex-1);
                int weight =(int) SampleInputs.getRandom(0,numberOfVertex-1);
                graph[i][j] =  new int[]{noeighbourId,weight};
                if(j==graph[i].length-1){
                    sb.append("{"+noeighbourId+","+weight+"}").append("\n");
                }else {
                    sb.append("{"+noeighbourId+","+weight+"},");
                }
            }


        }

        if(!silent)
            LOGGER.info("RANDOM GRAPH:\n"+sb.toString());





        return graph;

    }

    private static double getRandom(double low, double high){
        double rand = (int)Math.floor(Math.random()*(high-low+1)+low);
        return rand;
    }





    public static void main(String[] args) {

        generateRandomGraph(41);

        // input matrix
        char[][] mat =
                {
                        { 'D', 'E', 'H', 'X', 'B' },
                        { 'A', 'O', 'G', 'P', 'E' },
                        { 'D', 'D', 'C', 'F', 'D' },
                        { 'E', 'B', 'E', 'A', 'S' },
                        { 'C', 'D', 'Y', 'E', 'N' }
                };

        int[][][] input = {
                {{1, 1}},
                {{0, 1}, {3, 6}},
                {{3, 4}, {4, 3}, {5, 2}},
                {{1, 6}, {2, 4}, {4, 1}},
                {{3, 1}, {2, 3}},
                {{2, 2}}
        };

        int[][][] info = {
                {{1,6},{3,1},{5,1},{6,5}},
                {{0,6},{3,2},{4,2},{2,5},{6,2}},
                {{1,5},{4,5}},
                {{0,1},{1,2},{4,1},{5,7}},
                {{1,2},{2,5},{3,1}},
                {{0,1},{3,7}},
                {{1,5},{1,2}}
        };

        int [][] input1 = {
                {1},
                {0,3},
                {3,4,5},
                {1,2,4},
                {2,3},
                {2}
        };


        int[][][] randpm = generateRandomGraph(10);



        System.out.println("asd");

    }
}
