package com.egzel.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.egzel.lib.util.ALogger;
import com.egzel.lib.util.DataUtil;

public class MergeSort {

    static ALogger<MergeSort> LOGGER = new ALogger<>(MergeSort.class);

    public static void main(String[] args) {

//        int[] arrr = lib.util.SampleData.Generators.generateIntArray(5,-17,17);
//
//        LOGGER.info("\n"+ Arrays.stream(arrr).boxed().collect(Collectors.toList()));
//
//        sort.MergeSort mergeSort = new sort.MergeSort();
//        mergeSort.sortMerge(arrr);
//
//        LOGGER.info("\n"+ Arrays.stream(arrr).boxed().collect(Collectors.toList()));


        MergeSort mergeSort = new MergeSort();
        int[] arr1 = DataUtil.Generators.generateIntArray(8,-50,50);

        System.out.println(Arrays.stream(arr1).boxed().collect(Collectors.toList()));

        MergeSort mergeSort1 = new MergeSort();

        mergeSort1.sort(arr1);

        System.out.println(Arrays.stream(arr1).boxed().collect(Collectors.toList()));



    }

    public void sort(int[]arr){

        if(arr.length<2)return;

        int mid = arr.length/2;

        int[] left = new int[mid];
        int [] right = new int[arr.length-mid];

        for(int i = 0; i < mid; i++){
            left[i] = arr[i];
        }


        for(int i = 0; i < right.length; i++){
            right[i] = arr[mid+i];
        }

        sort(left);
        sort(right);
        merge(left,right,arr);

    }

    public void merge(int[] left, int[] right, int[] arr) {

        int lx = 0;
        int rx = 0;
        int kx = 0;

        int leftlength = left.length;
        int rightlength = right.length;

        while (lx<leftlength && rx<rightlength){

            if(left[lx]<right[rx]){
                arr[kx] = left[lx];
                lx++;
            }else {
                arr[kx] = right[rx];
                rx++;
            }

            kx++;
        }

        while (lx<leftlength){
            arr[kx] = left[lx];
            lx++;
            kx++;
        }

        while (rx<rightlength){
            arr[kx] = right[rx];
            rx++;
            kx++;
        }

    }//merge

}
