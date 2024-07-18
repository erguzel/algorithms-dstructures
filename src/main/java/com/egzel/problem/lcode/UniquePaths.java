package com.egzel.problem.lcode;

import java.util.stream.IntStream;

/**  62. Unique Paths
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Input: m = 3, n = 7
 * Output: 28
 *
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */
public class UniquePaths {

    public int numberOfUniquePaths(int m, int n){
        /**
         * f(i,j) = # of unique paths to i , j
         * f(0,0) = 0;
         * f(0,n) = 1
         * f(n,0) = 1
         *
         * f(i,j) -> f(i-1,j) + f(i,j-1)
         *
         */

        int[][] dp = new int[m][n];

        IntStream.range(0,dp.length).forEach(row->{
            IntStream.range(0,dp[row].length).filter(col->row==0||col==0).forEach(c->{
                dp[row][c]=1;
            });
        });

//        for(int i = 0; i < dp.length;i++){
//            for(int j = 0; j < dp[i].length;j++){
//                if(i==0 || j==0)dp[i][j] = 1;
//            }
//        }
//
//        for(int i = 1; i < dp.length; i++){
//            for(int j = 1; j < dp[i].length;j++){
//                dp[i][j] = dp[i-1][j]+dp[i][j-1];
//            }
//        }




        IntStream.range(1,dp.length).forEach(row->{
            IntStream.range(1,dp[row].length).forEach(col->{
                dp[row][col] = dp[row-1][col]+dp[row][col-1];
            });
        });

        return dp[m-1][n-1];
    }
}
