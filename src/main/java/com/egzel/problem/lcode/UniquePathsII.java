package com.egzel.problem.lcode;

import java.util.stream.IntStream;

/**
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]). The robot can only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * Example 1
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rowRange = obstacleGrid.length;
        int colRange = obstacleGrid[0].length;

        if(obstacleGrid[0][0]==1)return 0;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        //base case
        dp[0][0] = 1;

        IntStream.range(1,rowRange).forEach(r->{
            final boolean currentCellWithoutObstacleAndUpperCellIsValid = obstacleGrid[r][0] == 0 && dp[r-1][0]==1;
            dp[r][0]= currentCellWithoutObstacleAndUpperCellIsValid?1:0;
        });

        IntStream.range(1,colRange).forEach(c->{
            final boolean currentCellWithoutObstacleAndLeftCellIsValid = obstacleGrid[0][c] == 0 && dp[0][c-1]==1;
            dp[0][c]= currentCellWithoutObstacleAndLeftCellIsValid?1:0;
        });


        IntStream.range(1,dp.length).forEach(row->{
            IntStream.range(1,dp[row].length).forEach(col->{
                final boolean hasObstacle = obstacleGrid[row][col]==1;
                if(hasObstacle){
                    dp[row][col]=0;
                }
                else {
                    dp[row][col] = dp[row-1][col]+dp[row][col-1];
                }
            });
        });

        return dp[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
