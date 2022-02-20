import java.util.Random;

public class SampleInputs {
    /**
     * vertex[neighbour,weight]
     * @return
     */
    public static int[][][] get18weightedShortestPath() {


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

    public static int[][][] generateRandomGraph(int numberOfVertex){

        int[][][]  graph = new int[numberOfVertex][][];
        int edgeNummer = 0;
        for(int i = 0; i < numberOfVertex; i ++){

            // number of edges

            if(graph[i] == null){

                edgeNummer = (int) SampleInputs.getRandom(0,numberOfVertex-1);
                graph[i] = new int[edgeNummer][];
            }

            for(int j = 0; j < graph[i].length; j++){
                int noeighbourId =(int) SampleInputs.getRandom(0,numberOfVertex-1);
                int weight =(int) SampleInputs.getRandom(0,numberOfVertex-1);
                graph[i][j] =  new int[]{noeighbourId,weight};
            }

        }

        return graph;

    }
    public static double getRandom(double low, double high){
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
