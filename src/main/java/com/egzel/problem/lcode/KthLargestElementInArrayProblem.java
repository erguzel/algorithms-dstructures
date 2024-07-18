package com.egzel.problem.lcode;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**215. Kth Largest Element in an Array
 *
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 */
public class KthLargestElementInArrayProblem {

    public int findKthLargest(int[] nums, int k){

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a,b)->a>b?-1:1);

        IntStream.range(0,nums.length).forEach(x->{
            priorityQueue.add(nums[x]);
        });

        var streamHelper = new Object(){
            int res  = Integer.MAX_VALUE;
        };

        IntStream.range(0,k).forEach(x->{
            streamHelper.res = priorityQueue.poll();
        });

        return streamHelper.res;
    }
}
