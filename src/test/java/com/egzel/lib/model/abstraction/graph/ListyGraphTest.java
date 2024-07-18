package com.egzel.lib.model.abstraction.graph;

import org.junit.jupiter.api.Test;

import com.egzel.lib.model.ProblemBase;
import com.egzel.lib.model.abstraction.graph.IGraph;
import com.egzel.lib.model.abstraction.graph.ListyGraph;
import com.egzel.lib.util.DataUtil;
import com.egzel.lib.util.SampleData;

import static org.junit.jupiter.api.Assertions.*;

class ListyGraphTest extends ProblemBase {

    @Test
    public void testInitializationAdjList(){
        int [][] adjlist = SampleData.AdjLists.DNW_TREE_8_1;
        int nofvertices = 8;
        IGraph.GraphTypes graphType = IGraph.GraphTypes.ADJLIST;
        ListyGraph listyGraph = new ListyGraph(adjlist,nofvertices,graphType);
        //want
        int order = 8;
        int size = 7;

        //

        assertAll("adjList1",
                ()->assertEquals(listyGraph.getSize(),size),
                ()->assertEquals(listyGraph.getOrder(),order)
        );

    }

    @Test
    public void testInitializationAdjMtx(){
        int[][] adjmtx = DataUtil.Convertors.convertAdjListToAdjMatrix(SampleData.AdjLists.DNW_TREE_8_1,true);
        int nofvertices = 8;
        IGraph.GraphTypes graphType = IGraph.GraphTypes.ADJMTX;
        ListyGraph listyGraph = new ListyGraph(adjmtx,nofvertices,graphType);
        //want
        int order = 8;
        int size = 7;

        assertAll("adjList1",
                ()->assertEquals(listyGraph.getSize(),size),
                ()->assertEquals(listyGraph.getOrder(),order)
        );
    }

    @Test
    public void testInitializationEdgeList(){
        int[][] elist = new int[][]{
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {   3},
                {   4},
                {5, 6},
                {5, 7},
        };

        int nofvertices = 8;
        IGraph.GraphTypes graphType = IGraph.GraphTypes.EDGELIST;
        ListyGraph listyGraph = new ListyGraph(elist,nofvertices,graphType);
        //want
        int order = 8;
        int size = 7;

        assertAll("adjList1",
                ()->assertEquals(listyGraph.getSize(),size),
                ()->assertEquals(listyGraph.getOrder(),order)
        );
    }
}