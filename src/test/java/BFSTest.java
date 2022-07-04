import graph.BFS;
import lib.util.SampleData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    private BFS instance = new BFS();
    @Test
    void testPathExists_EDLIST(){

        int input [][] = SampleData.EdgeLists.GRAPH01_NW_UNDCYX_DCY0_8;


        assertEquals(instance.pathExists_EDLIST(input,7,6,true),false);
        assertEquals(instance.pathExists_EDLIST(input,7,6,false),true);

    }

    @Test
    void testGetPath_EDLIST() {

        int [][] input = SampleData.EdgeLists.GRAPH01_NW_UNDCYX_DCY0_8;

        int[] wantedInDirected = {1,0,3,6};
        int[] wantedDirected = {};

      // instance.getPath_EDLIST(input,8,1,7,false);
       // assertArrayEquals(instance.getPath_EDLIST(input,8,7,1,true),wantedDirected);

        assertArrayEquals(instance.getPath_EDLIST(input,8,1,7,false),wantedInDirected);
        assertArrayEquals(instance.getPath_EDLIST(input,8,7,1,true),wantedDirected);
    }
}