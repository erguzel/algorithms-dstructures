import lib.model.MyBSTree;
import lib.model.ProblemBase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSumProblem extends ProblemBase {

    private static class ArrayElement implements Comparable<ArrayElement> {
        private int idx;
        private int val;

        public ArrayElement(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(ArrayElement o) {
            return this.val > o.val ? 1 : -1;
        }

        @Override
        public String toString() {
            return String.format("[%d : %d]", idx, val);
        }
    }

    public static void main(String[] args) {
        int qs[] = {1, 2, 3, 4, 5};
        int target = 9;

        TwoSumProblem twoSumProblem = new TwoSumProblem();
        Stack<Integer> resu = twoSumProblem.twoSum(qs, target);

        System.out.println(resu);
    }

    public Stack<Integer> twoSum(int[] nums, int target) {
        for(int outerindex = 0; outerindex<nums.length+1;outerindex++){
            for (int innerindex = outerindex; innerindex<nums.length+1;innerindex++){
                List pairs = new ArrayList();
                for(int i = outerindex; i < innerindex; i++){
                    pairs.add(i);
                }
                System.out.println(pairs);
            }
            System.out.println();
        }
        return null;
    }
}
