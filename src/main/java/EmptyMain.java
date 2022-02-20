import java.util.Arrays;


public class EmptyMain {

    static ALogger<EmptyMain> LOGGER = new ALogger<>(EmptyMain.class);

    public static void main(String[] args) {

       int[][][] sample = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.GO0001_ADJMTX);
       int[][][] summer = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.SUMMER);
       int[][][] brown = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.BROWN);
       int[][][] rs001 = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.RS_0001);
//
//        SampleInputs.printAdjacencyList(sample,"GO0001_ADJMTX");




       SampleInputs.generateRandomGraph(14);

        LOGGER.setStartTime();

        Dijkstara d = new Dijkstara();
        d.getShortestPath(rs001, 8, 0);

        LOGGER.getBenchmarh();



    }
}
