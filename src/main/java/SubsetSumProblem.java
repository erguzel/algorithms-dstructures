import lib.model.ProblemBase;

import java.util.*;
import java.util.stream.IntStream;

/**
 * * *  Write a function that takes in a targetSum and array of numbers as arguments
 * The function should return an array containing any combination of elements that add up tp
 * exactly the target sum. If there is no combination that adds up the target sum, return null
 * <p>
 * if multiple combinations found, you may return any of these.
 */
public class SubsetSumProblem extends ProblemBase {
    private Stack<Integer> vals = new Stack<>();

    public static void main(String[] args) {


    }

    /**
     * @param set
     * @param target
     * @return
     */
    public boolean subsetSumElements(int[] set, int target, Map<Integer, Boolean> mem) {
        /**
         * f(i,g(j)) true if i sums up to g(j)
         *
         */

        return false;
    }

}
