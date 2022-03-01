import java.util.Arrays;
import java.util.stream.Collectors;

public class SelectionSort {

    static ALogger<SelectionSort> LOGGER = new ALogger<>(SelectionSort.class);

    public static void main(String[] args) {
        int [] arr = SampleData.Generators.generateIntArray(10,-100,100);
        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));

        SelectionSort sort = new SelectionSort();
        sort.sort(arr);

        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));
    }

    public void sort(int[] arr) {

//validate
        String validationcondition = arr.length == 0 ? "empty" :
                arr.length == 1 ? "single" : "none";

        if (validationcondition != "none") {
//todo more informative return
            return;
        }//validate
//validate


        int nextidx = 0;
        int minidx = -1;
        boolean shouldswap = false;
        while (nextidx< arr.length) {

            int searchidx = nextidx;
            int min = Integer.MAX_VALUE;
            while (searchidx < arr.length) {

                if (arr[searchidx] < min) {
                    minidx = searchidx;
                    min = arr[searchidx];
                }// min found
                searchidx++;
            }//while find min
            shouldswap = arr[nextidx] > min;

            if (shouldswap) {
                int temp = arr[nextidx];
                arr[nextidx] = arr[minidx];
                arr[minidx] = temp;
            }//should swap

            nextidx++;

        }//whille TODO condition


    }//sort
}//class
