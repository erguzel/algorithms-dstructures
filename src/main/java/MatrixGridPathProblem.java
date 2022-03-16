import lib.model.ProblemBase;

import java.util.stream.IntStream;

/**
 * You are standing at the (0,0) point of a n * m matrix grid
 * You are allowed to move to either right or dowm
 * You need to reach (m, n) grid
 * How many different ways to get there
 */
public class MatrixGridPathProblem extends ProblemBase {

    /**
     * 1. Define objective function
     * f(i,j) -> #of max ways to reach (i,j)
     * 2. Define base cases
     * f(0,0) = 1; f(0,1) = 1; f(1,0)=1; f(1,1) = 2
     * 3. Define recurrence relation
     * F(m,n) = f(m-1,n)+f(m,n-1) // num of ways to get a cell is summ of # of ways to get predecessor cells
     * 4. Define execution order
     * Bottom up
     * 5. Define answer location
     * f(m,n) = f(Mmax,Nmax)
     *
     * @param
     * @return
     */
    public int maxNumberOfGetingToTheBottomRightGrid(int m, int n) {
        //validation
        boolean noNeedTo = m == 0 | n == 0;
        if (noNeedTo) return 1;
        //valudation

        // allocate memory for cumulative solutions
        int[][] dp = new int[m][n];
        IntStream.range(0, m).forEach(k -> {
            dp[k] = new int[n];
        });


        //initialize base case
        dp[0][0] = 1;
//        dp[0][1] = 1;
//        dp[1][0] = 1;
//        dp[1][1] = 2;

        IntStream.range(0, m).forEach(row -> {
            IntStream.range(0, n).forEach(col -> {
                if (row > 0 && col > 0) {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                } else if (row > 0) {
                    dp[row][col] = dp[row - 1][col];
                } else if (col > 0) {
                    dp[row][col] = dp[row][col - 1];
                }
            });
        });

        // result location
        return dp[m - 1][n - 1];
    }

    /**
     * Now grid can have obstacles
     * if given grid (i,j) = 1, you can not step there
     * How many different ways to reach bottom right
     *
     * @return
     */
    public int maxNumberOfGetingToTheBottomRightGridWithObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //validation TODO
        //initialize memory
        int[][] dp = new int[m][n];
        IntStream.range(0, m)
                .forEach(x -> {
                    dp[x] = new int[n];
                });

        //basecase
        dp[0][0] = 1;

        IntStream.range(0, m).forEach(row -> {
            IntStream.range(0, n)
                    .forEach(col -> {
                        if (grid[row][col] == 1) {
                            dp[row][col] = 0;
                            return;
                        }
                        if (row > 0 && col > 0) {
                            dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
                        } else if (row > 0) {
                            dp[row][col] = dp[row - 1][col];
                        } else if (col > 0) {
                            dp[row][col] = dp[row][col - 1];
                        }

                    });
        });

        return dp[m - 1][n - 1];
    }

    /**
     * Same problem, this time grid values represents points one can collect when visit
     * Find the maximum points one can collect throught the most profitable path
     * <p>
     * f(i,j) = maximum points one can collect till reaching (i,j)
     * <p>
     * P(i,j) point funciton (which is grid(i,j))
     * <p>
     * transition function
     * f(m,n) = max[f(m-1,n),f(m,n-1)] + P(m,n);
     * <p>
     * Base cases
     * f(0,0) = P(0,0);
     * f(0,1) = f(0,0)+P(0,1)
     * f(1,0) = f(0,0) + P(1,0)
     * f(1,1) = max[f(0,1),f(1,0)]+P(1,1)
     *
     * @param grid
     * @return
     */
    public int maximumGridPointOneCanCollectTillReachingBottomRightCorner(int[][] grid) {
        // validate:
        var validator = new Object() {
            boolean badarray = false;
            int zerogrid = -1;
        };
        if (grid == null) validator.badarray = true;
        int m = grid.length;
        if (m == 0) return 1;
        boolean anynullarray = IntStream.range(0, m).anyMatch(x -> grid[x] == null);
        if (anynullarray) {
            return -1;
        }
        int n = grid[0].length;
        /// validate

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        IntStream.range(0, m).forEach(row -> {
            IntStream.range(0, n).forEach(col -> {
                final int point = grid[row][col];
                if (row > 0 && col > 0) {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]) + point;
                } else if (row > 0) {
                    dp[row][col] = dp[row - 1][col] + point;
                } else if (col > 0) {
                    dp[row][col] = dp[row][col - 1] + point;
                }
            });
        });

        return dp[m - 1][n - 1];

    }

    /**
     * Return the most profitable path
     *
     * @param grid
     * @return
     */
    public int[][] maximumGridPointOneCanCollectTillReachingBottomRightCornerPath(int[][] grid) {
        // validate:
        var validator = new Object() {
            boolean badarray = false;
            int zerogrid = -1;
        };
        if (grid == null) validator.badarray = true;
        int m = grid.length;
        if (m == 0) return new int[][]{{0}};
        boolean anynullarray = IntStream.range(0, m).anyMatch(x -> grid[x] == null);
        if (anynullarray) {
            return null;
        }
        int n = grid[0].length;
        /// validate

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        IntStream.range(0, m).forEach(row -> {
            IntStream.range(0, n).forEach(col -> {
                final int point = grid[row][col];
                if (row > 0 && col > 0) {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]) + point;
                } else if (row > 0) {
                    dp[row][col] = dp[row - 1][col] + point;
                } else if (col > 0) {
                    dp[row][col] = dp[row][col - 1] + point;
                }
            });
        });

        int [][] ress = pathConstructionUtil(dp,m-1,n-1,new int[m + n-1][2],m-1+n-1);

        return ress;
    }

    /**
     * Retrieve the path which leads to the bottom right via the most profitable way;
     * @param dp
     * @param row
     * @param col
     * @param resultPath
     * @param currentIndex
     * @return
     */
    public int[][] pathConstructionUtil(int[][] dp, int row, int col, int[][] resultPath,int currentIndex) {
        if(row==0&&col==0){
            resultPath[currentIndex] = new int[]{row,col};
            return resultPath;
        }else if(row==0){
            //resultPath[resultPath.length-1] = new int[]{row,col-1};
            currentIndex = currentIndex-1;
            resultPath = pathConstructionUtil(dp,row,col-1,resultPath,currentIndex);
        }else if(col == 0){
            //resultPath[resultPath.length-1] = new int[]{row-1,col};
            currentIndex = currentIndex-1;
            resultPath = pathConstructionUtil(dp,row-1,col,resultPath,currentIndex);
        }else{
            if(dp[row-1][col]>dp[row][col-1]){
                //resultPath[resultPath.length-1] = new int[]{row-1,col};
                currentIndex = currentIndex-1;
                resultPath = pathConstructionUtil(dp,row-1,col,resultPath,currentIndex);
            }else {
                //resultPath[resultPath.length-1] = new int[]{row,col-1};
                currentIndex = currentIndex-1;
                resultPath = pathConstructionUtil(dp,row,col-1,resultPath,currentIndex);
            }

        }
        resultPath[currentIndex+1] = new int[]{row,col};
        return resultPath;
    }

}
