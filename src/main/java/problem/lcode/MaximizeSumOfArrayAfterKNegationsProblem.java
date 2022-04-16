package problem.lcode;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * 1005. Maximize Sum Of Array After K Negations
 *
 * Given an integer array nums and an integer k, modify the array in the following way:
 *
 *     choose an index i and replace nums[i] with -nums[i].
 *
 * You should apply this process exactly k times. You may choose the same index i multiple times.
 *
 * Return the largest possible sum of the array after modifying it in this way.
 *
 * Input: nums = [3,-1,0,2], k = 3
 * Output: 6
 * Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
 */
public class MaximizeSumOfArrayAfterKNegationsProblem {


    public int largestSum(int[]nums, int k){
        var streamHelper = new Object(){
            int sum = 0;
        };

        TreeMap<Integer, Integer> sset = new TreeMap<>((a, b) -> a > b ? 1 : -1);
        IntStream.range(0, nums.length).forEach(el -> {
            sset.put(nums[el], el);
            streamHelper.sum = streamHelper.sum+nums[el];
        });
        IntStream.range(0, k).forEach(x -> {
            Map.Entry<Integer, Integer> ent = sset.pollFirstEntry();
            int idx = ent.getValue();
            int temp = nums[idx];
            temp = temp * (-1);
            nums[idx] = temp;
            sset.put(temp, idx);
            streamHelper.sum = streamHelper.sum+(2*temp);
        });

        return streamHelper.sum;
    }
}
