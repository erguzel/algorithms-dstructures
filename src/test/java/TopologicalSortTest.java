import lib.model.abstraction.graph.IGraph;
import lib.util.SampleData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopologicalSortTest {

    public TopologicalSort instance = new TopologicalSort();
    @Test
    void sortTopologicallyIfExists_Khan() {
        int[][] input1 = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 4},
                {4, 5},
                {4, 6},
                {3, 2},
        };
        int numberofvertex1 = 7;
        int[] want1 = {0,3,1,2,4,5,6};

        int[][] input2 = SampleData.Csacademy.COURSE_SCHEDULE_1;
        int numberofvertex2 = 6;
        int[] want2 = {5,3,4,0,2,1};

        int[][] input3 = SampleData.Csacademy.COURSE_SCHEDULE_2;
        int numberofvertex3 = 6;
        int[] want3 = null;

        int[][] input4 = SampleData.Csacademy.COURSE_SCHEDULE_3;
        int numberofvertex4 = 12;
        int[] want4 = {0,3,7,10,5,8,1,6,9,11,4,2};

        assertAll("base",
                ()->assertArrayEquals(instance.sortTopologicallyIfExists_Khan(input1,numberofvertex1),want1 ),
                ()->assertArrayEquals(instance.sortTopologicallyIfExists_Khan(input2,numberofvertex2),want2 ),
                ()->assertArrayEquals(instance.sortTopologicallyIfExists_Khan(input3,numberofvertex3),want3 ),
                ()->assertArrayEquals(instance.sortTopologicallyIfExists_Khan(input4,numberofvertex4),want4 )
        );
    }

    @Test
    void sortTopologicallyIfExists_Khan_ListyGraph() {
        int[][] input1 = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 4},
                {4, 5},
                {4, 6},
                {3, 2},
        };
        int numberofvertex1 = 7;
        int[] want1 = {0,3,1,2,4,6,5};
        int [] res1 = instance.sortTopologicallyIfExists_Khan_ListyGraph(input1,numberofvertex1, IGraph.GraphTypes.EDGELIST);

        int[][] input2 = SampleData.Csacademy.COURSE_SCHEDULE_1;
        int numberofvertex2 = 6;
        int[] want2 = {5,4,3,2,0,1};
        int[] res2 = instance.sortTopologicallyIfExists_Khan_ListyGraph(input2,numberofvertex2,IGraph.GraphTypes.EDGELIST);

        int[][] input3 = SampleData.Csacademy.COURSE_SCHEDULE_2;
        int numberofvertex3 = 6;
        int[] want3 = null;
        int[] res3 =instance.sortTopologicallyIfExists_Khan_ListyGraph(input3,numberofvertex3,IGraph.GraphTypes.EDGELIST);

        int[][] input4 = SampleData.Csacademy.COURSE_SCHEDULE_3;
        int numberofvertex4 = 12;
        int[] want4 = {0,3,7,10,5,1,8,11,9,6,2,4};
        int [] res4 = instance.sortTopologicallyIfExists_Khan_ListyGraph(input4,numberofvertex4,IGraph.GraphTypes.EDGELIST);

        assertArrayEquals(res1,want1,"test1");
        assertArrayEquals(res2,want2,"test2");
        assertArrayEquals(res3,want3,"test3");
        assertArrayEquals(res4,want4,"test4");

    }
}