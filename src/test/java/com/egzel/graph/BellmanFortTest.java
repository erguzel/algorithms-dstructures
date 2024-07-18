package com.egzel.graph;

import org.junit.jupiter.api.Test;

import com.egzel.graph.BellmanFord;
import com.egzel.lib.util.SampleData;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFortTest {

    private BellmanFord instance = new BellmanFord();
    @Test
    void testShortestDistance() {

        int[][] input = SampleData.EdgeLists.UNDIRECTED_9_WEIGHTED_NEGATIVE_CYCLE_001;
        int numofvertex = 9;
        int source = 1;
        int target = 7;
        int want = -11;
        //
        //
        //
        int[][] input1 = SampleData.EdgeLists.UNDIRECTED_9_WEIGHTED_NEGATIVE_CYCLE_NEGATIVECYCLE_001;
        int numofvertex1 = 9;
        int source1 = 1;
        int target1 = 7;
        int want1 = Integer.MAX_VALUE;
        assertAll("base",
                ()->assertEquals(instance.shortestDistance(input,numofvertex,source,target),want),
                ()->assertEquals(instance.shortestDistance(input1,numofvertex1,source1,target1),want1)
        );
    }
}