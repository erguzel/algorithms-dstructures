package com.egzel.problem;

import java.util.TreeSet;

import com.egzel.lib.model.ProblemBase;

/**
 * Given an integer array A and a target integer k
 * find the kth largest element in a
 */
public class KthLargestElementProblem extends ProblemBase {

    public static void main(String[] args) {

        KthLargestElementProblem kthLargestElementProblem = new KthLargestElementProblem();
        int[] arr = {4, 2, 9, 7, 5, 6, 7, 1, 3};
        int target = 10;

        int res = kthLargestElementProblem.getKthLargestElement(target, arr);

        kthLargestElementProblem.LOGGER.info(res);
    }


    //O(nlogn) - o(n)
    public int getKthLargestElement(int target, int[] arr) {
// validate
        String validationState = arr.length == 0 ? "empty" :
                "none";
        if (validationState != "none") {
            return -1;
        }//if valid

// treeset
        TreeSet<Integer> treeset = new TreeSet<>((a, b) -> a > b ? -1 :1);// allow duplicates and, sort descending

        for (int i = 0; i < arr.length; i++) {
            treeset.add(arr[i]);
        }//for fill treeset nlogn

        boolean isFound = treeset.size() >= target;
        if (!isFound) return -1;

        //remove untill n logk
        for (int i = 0; i < target - 1; i++) {
            treeset.pollFirst();
        }

        return treeset.size()>0?treeset.pollFirst():-1;

    }//getKthLargest
}// class
