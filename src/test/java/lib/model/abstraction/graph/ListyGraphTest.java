package lib.model.abstraction.graph;

import lib.model.ProblemBase;
import lib.util.DataUtil;
import lib.util.SampleData;
import org.junit.jupiter.api.Test;

class ListyGraphTest extends ProblemBase {

    @Test
    public void testInitializationAdjList(){
        int [][] t11 = SampleData.AdjLists.DNW_TREE_8_1;
        int t12 = 8;
        IGraph.GraphTypes t13 = IGraph.GraphTypes.ADJLIST;
        // adj mtx
        int[][] t21 = DataUtil.Convertors.convertAdjListToAdjMatrix(SampleData.AdjLists.DNW_TREE_8_1,true);
        int t22 = 8;
        IGraph.GraphTypes t23 = IGraph.GraphTypes.ADJMTX;


        ListyGraph listyGraph = new ListyGraph(t11,t12,t13);

        ListyGraph listyGraph1 = new ListyGraph(t21,t22,t23);

        LOGGER.info("end of test");



    }
}