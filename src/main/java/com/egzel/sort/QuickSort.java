package com.egzel.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.egzel.lib.util.ALogger;
import com.egzel.lib.util.DataUtil;

/**
 * Strategy : divide and conquer
 * worstcase : O(n^2)
 * bestcase : O(nlog(n))
 */


public class QuickSort{
    static ALogger<QuickSort> LOGGER = new ALogger<>(QuickSort.class);

    // divide and conquer
// algorithm
// partite array
// sort left of pivot
// sort right of pivot

    public void sort(int[] arr, int left, int right){
        int partiteid = partition(arr,left,right);
        if(left<partiteid-1){
            sort(arr, left, partiteid-1);
        }//if sort left
        if(partiteid<right){
            sort (arr,partiteid,right);
        }// if sort right
    }// sort

    public int partition(int[] arr, int left, int right){
        int pivot = arr[(left+right)/2];
        while(left<=right){
            while(arr[left]<pivot){left++;}//shift right
            while(arr[right]>pivot){right--;}//shift left
            if(left<=right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++; right--;
            }// if swap
        }//while left in left
        return left;
    }// partition
    public static void main(String[] args) {

       //  int [] arr = {1,56,0,-2,5,23,-29,213,9};
        int[] arr = DataUtil.Generators.generateIntArray(20, -69, 64);

        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        ALogger.TIMER timer = new ALogger.TIMER();
        timer = timer.startTimer();
        QuickSort sort = new QuickSort();
        sort.sort(arr,0,arr.length-1);
        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        timer.getBenchmark(timer);


    }
}//class sort.QuickSort


