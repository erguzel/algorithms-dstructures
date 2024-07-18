package com.egzel.sort;

import java.util.*;
import java.util.stream.Collectors;

import com.egzel.lib.model.Heap;
import com.egzel.lib.util.ALogger;
import com.egzel.lib.util.DataUtil;

public class HeapSort {

    static ALogger<HeapSort> LOGGER = new ALogger<>(HeapSort.class);

    public static void main(String[] args) {

        int [] arr = DataUtil.Generators.generateIntArray(5,30,130);


        LOGGER.info("\n" + Arrays.stream(arr).boxed().collect(Collectors.toList()));
        HeapSort hs = new HeapSort();

        int[] res = hs.sort(arr);

        LOGGER.info("\n" + Arrays.stream(res).boxed().collect(Collectors.toList()));
    }


    public int[] sort(int[] arr) {


        Heap heap = new Heap(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            heap.insert(arr[i]);
        }

        for (int i = 0; i < arr.length;i++){
            heap.poll();
        }

        return heap.getData();
    }


}
