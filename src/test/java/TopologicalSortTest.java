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
}