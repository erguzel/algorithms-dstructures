package com.egzel.problem;

import java.util.*;
import java.util.stream.IntStream;

import com.egzel.lib.model.ProblemBase;

/**
 * * *  Write a function that takes in a targetSum and array of numbers as arguments
 * The function should return an array containing any combination of elements that add up tp
 * exactly the target sum. If there is no combination that adds up the target sum, return null
 * <p>
 * if multiple combinations found, you may return any of these.
 */
public class SubsetSumProblem extends ProblemBase {

    public static void main(String[] args) {



    }

    /**
     * @param set
     * @param target
     * @return
     */
    public boolean subsetSumElements(int[] set, int target) {

        int setLength = set.length;
        int targetLength = target;

        int[][] dp = new int[setLength+1][targetLength+1];
        dp[0][0] = 1;
        //1st column
        IntStream.range(1,setLength+1).forEach(r->{
            dp[r][0]=1;
        });

        IntStream.range(1,targetLength+1).forEach(c->{
            if(c < setLength+1){
                if(set[c-1]==c){
                    dp[0][c] =1;
                }
            }
        });

        IntStream.range(1,setLength+1).forEach(r->{
            IntStream.range(1,targetLength+1).forEach(c->{
                if(c-set[c-1]==0){
                    dp[r][c]=dp[r-1][c-set[c-1]];
                }
            });
        });


        return false;
    }

}
