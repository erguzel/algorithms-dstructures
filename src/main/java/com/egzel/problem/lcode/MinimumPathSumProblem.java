package com.egzel.problem.lcode;

import java.util.stream.IntStream;

import com.egzel.lib.model.ProblemBase;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 */
public class MinimumPathSumProblem extends ProblemBase {

    static class DpEntry{
        public int row;
        public int col;
        public int val;

        public DpEntry(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    public int minimumPathSum(int[][] grid){
        // f(i,j) -> minimum sum to go from i to j
        // recurrence
        // f(i,j) = grid(i,j) + min(dp[i][j+1], dp[i+1][j])
        // base cases dp[0][0] = grid[0][0]
        // result is at dp[grid.length][grid.length]

        int [][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];

        //only possible to navigate from left
        IntStream.range(1,grid[0].length).forEach(x->{
            dp[0][x] = grid[0][x] + dp[0][x-1];
        });

        //only possible to navigate from top
        IntStream.range(1,grid.length).forEach(x->{
            dp[x][0] = grid[x][0] + dp[x-1][0];
        });

        IntStream.range(1,grid.length).forEach(row->{
            IntStream.range(1,grid[0].length).forEach(col->{
                // f(i,j) = grid(i,j) + min(dp[i][j+1], dp[i+1][j])
                dp[row][col] = grid[row][col] + Math.min(dp[row][col-1],dp[row-1][col]);

            });
        });


        return dp[grid.length-1][grid[0].length-1];
    }
}
