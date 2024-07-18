package com.egzel.problem.lcode;

import java.util.stream.IntStream;

import com.egzel.lib.model.ProblemBase;

/**
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 */
public class ClimbingStairsProblem extends ProblemBase {

    /**
     * f(i) -> #of ways of reaching ith step
     *
     * f(0) = 1;
     * f(1) = 1;
     *
     * f(i) = f(i-1) + f(i-2)
     *
     * result at dp[n]
     */


    public int climbStairs(int n ){
        int[]dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;

        IntStream.rangeClosed(2,n).forEach(step->{
            dp[step] = dp[step-1] + dp[step-2];
        });

//        for(int i = 2; i < n; i++){
//            dp[i] = dp[i-2]+dp[i-1];
//        }

        return dp[n];

    }

}
