import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest extends ProblemBase {

    private BinarySearch instance = new BinarySearch();

    @Test
    void searchRecursive() {
        List<BSArrayKeyAndWant> subjects = Arrays.asList(
                new BSArrayKeyAndWant(new int[]{},11,Integer.MIN_VALUE),
                new BSArrayKeyAndWant(new int[]{11,222,3333},3333,3333),
                new BSArrayKeyAndWant(new int[]{-12,34,56,88,89,89,89},89,89),
                new BSArrayKeyAndWant(new int[]{-1, 2,3,44,55,63},44,44)
        );

        subjects.forEach(a->assertEquals(instance.searchRecursive(a.arr,0,a.arr.length-1,a.key),a.want));
    }


    @Test
    void search() {

        List<BSArrayKeyAndWant> subjects = Arrays.asList(
                new BSArrayKeyAndWant(new int[]{},11,Integer.MIN_VALUE),
                new BSArrayKeyAndWant(new int[]{11,222,3333},3333,3333),
                new BSArrayKeyAndWant(new int[]{-12,34,56,88,89,89,89},89,89),
                new BSArrayKeyAndWant(new int[]{-1, 2,3,44,55,63},44,44)
        );

        subjects.forEach(a->assertEquals(instance.search(a.arr,a.key),a.want));

    }
    private static class BSArrayKeyAndWant{
        int[] arr = null;
        int key = Integer.MAX_VALUE;
        int want = Integer.MIN_VALUE;

        public BSArrayKeyAndWant(int[] arr, int key, int want) {
            this.arr = arr;
            this.key = key;
            this.want = want;
        }
    }
}