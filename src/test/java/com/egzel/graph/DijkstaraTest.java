package com.egzel.graph;

import org.junit.jupiter.api.Test;

import com.egzel.graph.Dijkstara;
import com.egzel.lib.model.ProblemBase;
import com.egzel.lib.util.DataUtil;
import com.egzel.lib.util.SampleData;

import static org.junit.jupiter.api.Assertions.*;

class DijkstaraTest extends ProblemBase {

    public Dijkstara instance = new Dijkstara();

    @Test
    void shortestPath() {

        int[][] graph = DataUtil.Convertors.convertAdjMatrixToEdgeList(SampleData.GraphOnlineRu.DUCK_DIR_WEG, false);
        int numberofvertex = 10;
        int src = 3;
        int dest = 2;
        boolean isDirected = false;
        int[] want = {3, 8, 7};

        int[][] graph1 = DataUtil.Convertors.convertAdjMatrixToEdgeList(SampleData.GraphOnlineRu.DUCK_DIR_WEG, true);
        int numberofvertex1 = 10;
        int src1 = 3;
        int dest1 = 2;
        boolean isDirected1 = true;
        int[] want1 = {3, 4};

        int[][] graph2 = DataUtil.Convertors.convertAdjMatrixToEdgeList(SampleData.GraphOnlineRu.SUMMER, false);
        int numberofvertex2 = 6;
        int src2 = 2;
        int dest2 = 1;
        boolean isDirected2 = false;
        int[] want2 = {2,4,5};


        int[][] graph3 = DataUtil.Convertors.convertAdjMatrixToEdgeList(SampleData.GraphOnlineRu.SUMMER, true);
        int numberofvertex3 = 10;
        int src3 = 5;
        int dest3 = 0;
        boolean isDirected3 = true;
        int[] want3 = {5,4,2};


        int [][] graph4 = {
                {0,1,1},
                {0,2,1},
                {1,2,1},
                {1,4,1},
                {4,5,1},
                {4,6,1},
                {3,2,1}
        };
        int numberofvertex4 = 7;
        int src4= 4;
        int dest4 =0;
        boolean isDirected4 = true;
        int[] want4 = {};

        assertAll("base",
                () -> assertArrayEquals(instance.shortestPath(graph, numberofvertex, src, dest, isDirected), want),
                () -> assertArrayEquals(instance.shortestPath(graph1, numberofvertex1, src1, dest1, isDirected1), want1),
                () -> assertArrayEquals(instance.shortestPath(graph2, numberofvertex2, src2, dest2, isDirected2), want2),
                () -> assertArrayEquals(instance.shortestPath(graph3, numberofvertex3, src3, dest3, isDirected3), want3),
                () -> assertArrayEquals(instance.shortestPath(graph4, numberofvertex4, src4, dest4, isDirected4), want4)
        );


    }

    @Test
    void testShortestDistance() {
        int[][] graph = DataUtil.Convertors.convertAdjMatrixToEdgeList(SampleData.GraphOnlineRu.DUCK_DIR_WEG, false);
        int numberofvertex = 10;
        int src = 3;
        int dest = 2;
        boolean isDirected = false;
        int want = 5;

        int[][] graph1 = DataUtil.Convertors.convertAdjMatrixToEdgeList(SampleData.GraphOnlineRu.DUCK_DIR_WEG, true);
        int numberofvertex1 = 10;
        int src1 = 3;
        int dest1 = 2;
        boolean isDirected1 = true;
        int want1 = 15;

        assertAll("base",
                () -> assertEquals(instance.shortestDistance(graph, numberofvertex, src, dest, isDirected), want),
                () -> assertEquals(instance.shortestDistance(graph1, numberofvertex1, src1, dest1, isDirected1), want1)
        );
    }
}