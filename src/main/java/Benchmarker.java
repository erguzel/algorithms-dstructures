public class Benchmarker {


    public static void main(String[] args) {

       int[][][] sample = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.WEIGHTED_GRAPH);
       int[][][] summer = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.SUMMER);
       int[][][] brown = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.BROWN);
       int[][][] rs001 = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.RS_0001);
       int[][][] random = SampleInputs.generateRandomGraph(15000,true);
//
//        SampleInputs.printAdjacencyList(sample,"GO0001_ADJMTX");



        ALogger.TIMER t = new ALogger.TIMER();

        t.startTimer();
        DijkstaraWithOnlyBuildInTypes db = new DijkstaraWithOnlyBuildInTypes();
        db.getShortestPathAndDistance(sample, 8, 0);
        t.getBenchmark(t);


        t.startTimer();

        Dijkstara d = new Dijkstara();
        d.getShortestPathAndDistance(sample, 8, 0);

        t.getBenchmark(t);







    }
}
