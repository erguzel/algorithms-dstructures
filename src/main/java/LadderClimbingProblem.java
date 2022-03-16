import lib.model.ProblemBase;

import java.util.*;
import java.util.stream.IntStream;

/**
 * There is a leadder to climb
 * It takes n steps to get the top
 * in each step, you can either one or two steps
 * in how many different sequence you can reach the top of the leadder
 */
public class LadderClimbingProblem extends ProblemBase {

    public static void main(String[] args) {

        int n = 4;

        LadderClimbingProblem ladderClimbingProblem = new LadderClimbingProblem();
        int res = ladderClimbingProblem.getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(n);
        System.out.println(res);
    }

    /**
     * 1. Define the objective function
     * f(i) = number of different ways ways to reach ith stairs
     * 2. Define the base case
     * f(0) = 1 ->if no leadder, there is only 1 different way
     * f(1) = 1 -> in case of 1 leadder, there is only 1 way
     * f(2) = 2 -> if 2 leadders exists, we can either take 1 step or 2
     * 3. Define recurrence relation
     * f(n) = f(n-1) + f(n-1); number of different ways of reaching nth step is the sum of number of different ways
     * to previous steps
     * 4. What is the order of execution
     * bottom up; starting from minimum to top
     * 5. Where is the result accumulated
     * f(n)
     * <p>
     * TC -> O(n)
     * SC -> O(n)
     *
     * @param numberofleadders
     * @return
     */
    public int getNumberOfDifferentWaysOfClimbingOnly2JumpsAllowed(int numberofleadders) {
        // base cases
        // define dp[] to hold the accumulative results
        int[] dp = new int[numberofleadders < 2 ? 2 : numberofleadders + 1];// to allocate dp[1] value
        dp[0] = 1;
        dp[1] = 1;

        IntStream.range(2, numberofleadders + 1).forEach(st -> {

            dp[st] = dp[st - 1] + dp[st - 2];

        });

        return dp[numberofleadders];
    }

    // up to 3 jumps at once
    public int getNumberOfDifferentWaysOfClimbingOnly3JumpsAllowed(int numberofleadders) {
        // base cases
        // this time we are allowed to jump 1, 2 or 3 times at once
        // add to the base case

        // define dp[] to hold the accumulative results
        int[] dp = new int[numberofleadders < 3 ? 3 : numberofleadders + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        IntStream.range(3, numberofleadders + 1).forEach(stride -> {

            dp[stride] = dp[stride - 1] + dp[stride - 2] + dp[stride - 3];

        });

        return dp[numberofleadders];
    }

    // op to k jumps at once
    //f(n) = f(n-1) + f(n-1)+....f(n-k); number of different ways of reaching nth step is the sum of number of different ways
    public int getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowed(int numberofleadders, int k) {
        // base cases
        // this time we are allowed to jump k times
        // add to the base case

        // define dp[] to hold the accumulative results
        int[] dp = new int[numberofleadders < 1 ? 1 : numberofleadders + 1];
        dp[0] = 1;

        IntStream.range(1, numberofleadders + 1).forEach(nofleadders -> {
            IntStream.range(1, k + 1)
                    .filter(i -> nofleadders - i >= 0)// to avid - indexes
                    .forEach(allowedStepsAtOnce -> {
                        dp[nofleadders] += dp[nofleadders - allowedStepsAtOnce];
                    });
        });

        return dp[numberofleadders];
    }

    //up to k jumps allowed
    // isforbidden[i] step if false, no way to reach here
    public int getNumberOfDifferentWaysOfClimbingOnlyKJumpsAllowedForbiddenStairs(int numberofleadders, int k, boolean[] isForbidden) {
        // base cases
        int[] dp = new int[numberofleadders < 1 ? 1 : numberofleadders + 1];
        dp[0] = 1;

        IntStream.range(1, numberofleadders + 1).forEach(leaddernumber -> {
            IntStream.range(1, k + 1)
                    .filter(i -> leaddernumber - i >= 0)
                    .forEach(allowedStepsAtOnce -> {
                        if (isForbidden[leaddernumber - 1]) {
                            dp[leaddernumber] = 0;// current leadder corresponds to i-1th isforbideen element
                        } else {
                            dp[leaddernumber] += dp[leaddernumber - allowedStepsAtOnce];
                        }
                    });
        });

        return dp[numberofleadders];
    }

    //
    // Optimisation problems
    //

    /**
     * Given a leadder which has n steps
     * Given a cost array P(n), which represents the cost of stepping on that particular leadder
     * while using only k jumps out of a time
     * Find the minimum cost to reach the top
     * <p>
     * <p>
     * // Solution
     * 1. Define Objective function
     * f(i) = minimum cost of reaching ith step.
     * 2. Define base cases
     * f(0) = 0; f(1) = 3; f(2) = 2; f(3)= 6  (minimum costs)
     * 3. Define transition function
     * f(n) = P(n) = min[f(n-2),f(n-1)]
     * 4. Define execution order
     * Bottom up
     * 5. Answer location is f(n)
     */

    public int minimumCostToGetTop(int numberofleadders, int maxjumpcapacity, int[] costarray) {
        //initalize result allocaiton
        int[] dp = new int[numberofleadders < 4 ? 4 : numberofleadders + 1];
        dp[0] = 0;
        dp[1] = 3;
        dp[2] = 2;
        dp[3] = 6;

        for (int i = 2; i < numberofleadders + 1; i++) {
            for (int j = 0; j < maxjumpcapacity + 1; j++) {
                if (i - j < 0) continue;
                dp[i] = costarray[i] + Math.min(dp[i - j - 2], dp[i - j - 1]);
            }
        }

        return dp[numberofleadders];
    }


    /**
     * Given a leadder which has n steps
     * Given a cost array P(n), which represents the cost of stepping on that particular leadder
     * while using only 1 or 2 jumps out of a time
     * Find the minimum cost to reach the top
     * <p>
     * <p>
     * // Solution
     * 1. Define Objective function
     * f(i) = minimum cost of reaching ith step.
     * 2. Define base cases
     * f(0) = 0; f(1) = 3; f(2) = 2; f(3)= 6  (minimum costs)
     * 3. Define transition function
     * f(n) = P(n) = min[f(n-2),f(n-1)]
     * 4. Define execution order
     * Bottom up
     * 5. Answer location is f(n)
     * <p>
     * T O(n)
     * S O(n)
     */
    public int minimumCostToGetTop2steps(int numberofleadders, int[] costarray) {
        //initalize result allocaiton
        int[] dp = new int[numberofleadders < 4 ? 4 : numberofleadders + 1];
        dp[0] = 0;
        dp[1] = 3;
        dp[2] = 2;
        dp[3] = 6;

        IntStream.range(2, numberofleadders + 1).forEach(stepno -> {
            dp[stepno] = costarray[stepno] + Math.min(dp[stepno - 1], dp[stepno - 2]);
        });

        return dp[numberofleadders];
    }

    public int[] minimumCostToGetTop2stepsPath(int numberofleadders, int[] costarray) {
        //initalize result allocaiton
        int[] dp = new int[numberofleadders+1];
        int[] from = new int[numberofleadders+1]; //for path
        dp[0] = 0;
        from[0] = 0;
        dp[1] = 3;
        from[1] = 2;
        from[2] = 3;

        IntStream.range(2, numberofleadders + 1).forEach(stepno -> {

            dp[stepno] = costarray[stepno] + Math.min(dp[stepno - 1],dp[stepno - 2]);
            if (dp[stepno-1]>dp[stepno-2]) {
                from[stepno] = stepno - 2;
            } else {
                from[stepno] = stepno - 1;
            }

        });


        List<Integer> path = new ArrayList<>();
        for (int cur = numberofleadders; cur >= 0; cur = from[cur]) {
            path.add(cur);
            if(cur==0)break;
        }

        Collections.reverse(path);
        int res [] = path.stream().mapToInt(a->a).toArray();
        return res;
    }


    //
    //
    /////////////////////

    /**
     * Old fashion loops
     */
    //up to 2 jumps
    public int getDifferentWaysV1(int numberofleadders) {
        int[] dp = new int[numberofleadders < 2 ? 2 : numberofleadders + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < numberofleadders + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[numberofleadders];
    }

    //up to 3 jumps
    public int getDifferentWaysV2(int numberofleadders) {
        int[] dp = new int[numberofleadders < 3 ? 3 : numberofleadders + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < numberofleadders + 1; i++) {
            dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
        }

        return dp[numberofleadders];
    }

    //up to k jumps
    public int getDifferentWaysV3(int numberofleadders, int k) {
        int[] dp = new int[numberofleadders < 1 ? 1 : numberofleadders + 1];
        dp[0] = 1;
        for (int i = 1; i < numberofleadders + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (i - j < 0) continue;
                dp[i] += dp[i - j];
            }
        }
        return dp[numberofleadders];
    }

    //up to k jumps with forbidden steps
    public int getDifferentWaysV4(int numberofleadders, int k, boolean[] isforbidden) {
        int[] dp = new int[numberofleadders < 1 ? 1 : numberofleadders + 1];
        dp[0] = 1;
        for (int i = 1; i < numberofleadders + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (i - j < 0) continue;
                if (isforbidden[i - 1]) {
                    dp[i] = 0;
                } else {
                    dp[i] += dp[i - j];
                }

            }
        }
        return dp[numberofleadders];
    }

    //
    // Optimisation
    //
    public int minimumCostToGetTop2stepsOld(int numberofleadders, int[] costarray) {
        //initalize result allocaiton
        int[] dp = new int[numberofleadders < 4 ? 4 : numberofleadders + 1];
        dp[0] = 0;
        dp[1] = 3;
        dp[2] = 2;
        dp[3] = 6;

        for (int i = 2; i < numberofleadders + 1; i++) {
            dp[i] = costarray[i] + Math.min(dp[i - 2], dp[i - 1]);
        }

        return dp[numberofleadders];
    }
}
