package sort;

import lib.util.ALogger;
import lib.util.DataUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertionSort{

    static  ALogger<InsertionSort> LOGGER = new ALogger<>(InsertionSort.class);
    public static void main(String[] args) {
        int [] arr = DataUtil.Generators.generateIntArray(10,-100,100);
        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));

        InsertionSort sort = new InsertionSort();
        sort.sort(arr);

        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));

    }

    public void sort(int[] arr){

        String validationState = arr.length == 0?"empty":
                arr.length<1 ? "single":
                        "none";

//validate
        if(validationState != "none"){
            if(validationState=="empty")
                return ;
            if(validationState=="single")
                return;
        }// if valid state
/////validate


        int nextidx = 1;// next idx bound of sorted list
        while(nextidx < arr.length){

            int scanidx = 0;
            while(scanidx < nextidx){

                boolean shouldswap = arr[nextidx] < arr[scanidx];

                if (shouldswap){
                    int temp = arr[scanidx];
                    arr[scanidx] = arr[nextidx];
                    arr[nextidx]= temp;
                }//should swap

                scanidx++;

            }//while scan

            nextidx++;

        }//while true
    }//sort
}//insertionSort



