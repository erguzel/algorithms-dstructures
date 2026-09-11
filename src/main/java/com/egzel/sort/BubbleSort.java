package com.egzel.sort;

import java.util.Arrays;


public class BubbleSort {

    public static void main(String[] args){
        int[] arrs = {4,3,5,12,3,0,-1};

        BubbleSort bs = new BubbleSort();
        bs.bubbleSort(arrs);


        Arrays.stream(arrs).forEach(a-> System.out.println(a));
    }

    public  void bubbleSort(int[] paramArr){

        int index = 0;//
        int indexVal=0;
        int innerIndex = 0;
        int innerIndexVal = 0;
        while (index<paramArr.length){

            indexVal = paramArr[index];

            while(innerIndex<index){

                innerIndexVal = paramArr[innerIndex];

                if(innerIndexVal>indexVal){

                    int temp = paramArr[index];
                    paramArr[index] = paramArr[innerIndex];
                    paramArr[innerIndex] = temp;
                }
                innerIndex++;
            }
            innerIndex = 0;
            index++;

        }
    }

}