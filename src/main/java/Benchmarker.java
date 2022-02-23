public class Benchmarker {

    static  ALogger<Benchmarker> LOGGER = new ALogger<>(Benchmarker.class);
    public static void main(String[] args) {

//       int[][][] sample = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.DUCK_DIR_WEG);
//       int[][][] summer = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.SUMMER);
//       int[][][] brown = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.BROWN);
//       int[][][] rs001 = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.RS_0001);
//       int[][][] random = SampleData.generateRandomGraph(15000,true);
//
//        SampleInputs.printAdjacencyList(sample,"GO0001_ADJMTX");

         int[][] adjmtx = SampleData.GraphOnlineRu.WEIGHTED_GRAPH;

         int[][] edgeList = SampleData.Convertors.convertAdjMtxToEdgeList(adjmtx);
         LOGGER.info(SampleData.Csacademy.stringfyGraphForOnlineVisualisation(edgeList));

         int [][] backadmtx = SampleData.Convertors.convertEdgelistToAdjMtx(edgeList,true,0,1,null);

         LOGGER.info(SampleData.GraphOnlineRu.stringfyGraphForOnlineVisualisation(backadmtx));

         int[][] backedgelist = SampleData.Convertors.convertAdjMtxToEdgeList(backadmtx);

         LOGGER.info(SampleData.Csacademy.stringfyGraphForOnlineVisualisation(backedgelist));

        LOGGER.
                info("");

    }
}
