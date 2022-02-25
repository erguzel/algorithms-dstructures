import java.awt.image.SampleModel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleData {

    private static ALogger<SampleData> LOGGER = new ALogger<>(SampleData.class);

    /**
     * Adj martixes
     */
    public static class GraphOnlineRu {

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

    public static class Csacademy {



        public static int[][] CSA002={
                {0 ,1 , 1},
                {0 ,2 , 3},
                {2 ,0 , 3},
                {3 ,0 , 1},
                {0 ,4 , 2},
                {4 ,0 , 2},
                {3 ,1 ,-4},
                {2 ,4 ,-2},
                {6 ,1 ,-5},
                {1 ,6 ,-5},
                {6 ,0 , 2},
                {2 ,0 , 3}

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

    public static class Convertors {

        public static int[][] convertEdgelistToAdjMtx(int[][] edges,boolean undirected,int paramfromidx, int paramtoidx, Object paramweightidx) {
            // {{1,2,1},{1,3,1}..}//



            int[][] res = new int[edges.length][];

            for (int i = 0; i < edges.length; i++) {

                // {1,4,5} // {1,5}
                int fridx = edges[i][paramfromidx];;
                int toidx = edges[i][paramtoidx];
                int weight =1;
                if(paramweightidx != null){

                    weight = edges[i][(int)paramweightidx];
                }

                if(res[fridx] == null){
                    res[fridx] = new int[edges.length];
                }

                res[fridx][toidx] = weight;
                if(undirected){
                    if(res[toidx] == null){

                        res[toidx] = new int[edges.length];

                    }
                    res[toidx][fridx] = weight;
                }


            }

            System.out.println(res.length);

            int nodeCount = 0;
            for(int i = 0; i < res.length; i++){

                if(res[i] !=null){
                    nodeCount++;
                }
            }



            for(int i = 0; i < nodeCount; i++){

                if(res[i] == null)continue;

                res[i] = Arrays.copyOf(res[i],nodeCount);
            }

            res = Arrays.copyOf(res,nodeCount);

            return res;
        }

        public static int[][] convertEdgelistToAdjMtx(int[][] edges,boolean undirected) {
            // {{1,2,1},{1,3,1}..}//

            int[][] res = new int[edges.length][];

            for (int i = 0; i < edges.length; i++) {

                // {1,4,5} // {1,5}
                int fridx = edges[i][0];;
                int toidx = edges[i][1];
                int weight = edges[i][2];

                if(res[fridx] == null){
                    res[fridx] = new int[edges.length];
                }

                res[fridx][toidx] = weight;
                if(undirected){
                    if(res[toidx] == null){

                        res[toidx] = new int[edges.length];

                    }
                    res[toidx][fridx] = weight;
                }


            }

            System.out.println(res.length);

            int nodeCount = 0;
            for(int i = 0; i < res.length; i++){

                if(res[i] !=null){
                    nodeCount++;
                }
            }



            for(int i = 0; i < nodeCount; i++){

                if(res[i] == null)continue;

                res[i] = Arrays.copyOf(res[i],nodeCount);
            }

            res = Arrays.copyOf(res,nodeCount);

            return res;
        }

        public static int[][] convertAdjMtxToEdgeList(int[][] adjMtx) {

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

        public static int[][][] convertAdjmtxToAdjList(int[][] adjmtx) {

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

        public static int[][] convertAdjListToAdjMtx(int[][][] adjlist) {

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

    public static class Printers{

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



    public static void main(String[] args) {


    }
}
