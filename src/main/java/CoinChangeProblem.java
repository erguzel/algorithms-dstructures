import lib.model.ProblemBase;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * You have coins in given denominations
 * for given amount of n, solve following problems
 */
public class CoinChangeProblem extends ProblemBase {

    /**
     * for given unlimited supply of coins,
     * for given denominations = {1,3,5,10}
     * How many ways of preparing n value
     *
     * @param n
     * @return
     */
    public int numberOfWaysToPrepareChange(int n) {
        if (n == 0) return 1;
        /**
         * f(i) # of ways of preparing n value using denominations of 1,3,5,10
         * base cases
         * f(0) = 1; number of ways of preparing 0 value
         * f(1) = 1 = 1;
         * f(2) = 11 = 1
         * f(3) = 111 or 30 = f(3-1) + f( 3-3) = f(2) + f(0) = 2;
         *
         * f(i) = f(i-1) + f(i-3) + f(i-5) + f(i-10);
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        //dp[1]= 1 ;
        IntStream.rangeClosed(1, n).forEach(sValue -> {
            if (sValue - 1 >= 0) {
                dp[sValue] += dp[sValue - 1];
            }
            if (sValue - 3 >= 0) {
                dp[sValue] += dp[sValue - 3];
            }
            if (sValue - 5 >= 0) {
                dp[sValue] += dp[sValue - 5];
            }
            if (sValue - 10 >= 0) {
                dp[sValue] += dp[sValue - 10];
            }
        });

        return dp[n];
    }

    /**
     * with unlimited supply of coins,
     * for given denominations d[k],
     * what is the number of ways of preparing value n
     *
     * @param n
     * @param denominations
     * @return
     */
    public int numberOfWaysToPrepareChange(int n, int[] denominations) {
        /**
         * f(i) number of ways of preparing i value with given denominations
         * f(0) = 1;
         * f(1) = f(1-d0)+f(1-d1)+.....f(1-dk)
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        IntStream.rangeClosed(1, n).forEach(sValue -> {
            IntStream.of(denominations)
                    .filter(i -> sValue - i >= 0)
                    .forEach(sDenom -> {
                        dp[sValue] += dp[sValue - sDenom];
                    });
        });

        return dp[n];
    }

    /**
     * Instead of unlimited coins but you have exactly t number of coins
     * for given denominations d[], how many different ways of creating n value using
     * exactly t number of coins
     */
    public int numberOfWaysToPrepareChange(int n, int[] denominations, int t) {
        if(n == 0 && t ==0) return 1;
        /**
         * f(i,t) => number of ways to create i value using t coins with given denominations
         *
         * f(0,0) = 1; #of ways of creating 0 value with 0 number of coins
         * f(0,1) = 0; # of ways of creating 0 value using 1 number of coins
         * f(0,i) = 0;
         *
         * f(1,0) = 0;
         * f(i,0) = 0;
         *
         * f(1,1) = f(1-do,1-1) + f(1-d1,1-1)+....f(1-dk,1-1);
         * f(i,t) = f(i-d0,t-1) + f(i-d1,t-1)......f(1-dk,t-1)
         *
         * f(i,t) = ∑ f(i-dk,t-1)
         *          i,k
         */
        int[][] dp = new int[n + 1][t + 1];

        dp[0][0] = 1;
        dp[0][1] = 0;
        //dp[0][i] = 0;
        //dp[i][0] = 0;

        IntStream.rangeClosed(0, n).forEach(sValue -> {
            IntStream.rangeClosed(0, t).forEach(sCoinNumber -> {

                if (sValue > 0 && sCoinNumber == 0) {
                    dp[sValue][sCoinNumber] = 0;
                    return;
                }
                if (sValue == 0 && sCoinNumber > 0) {
                    dp[sValue][sCoinNumber] = 0;
                    return;
                }
                IntStream.of(denominations)
                        .filter(j -> sValue - j >= 0)
                        .forEach(sDenom -> {
                            dp[sValue][sCoinNumber] += dp[sValue - sDenom][sCoinNumber - 1];
                        });
            });
        });


        return dp[n][t];
    }

    /**
     * Instead of unlimited coins but you have t number of coins. But you do not have to use
     * all of them. i.e. for given denominations d[], how many different ways of creating n value using
     * at most t number of coins
     */
    public int numberOfWaysToPrepareChangeMaxThreshold(int n, int[] denominations, int t) {
        if(n==0 && t==0)return 1;
        /**
         * f(i,t) => number of ways to create i value using maximum t coins with given denominations
         *
         * f(0,0) = 1; #of ways of creating 0 value using max 0 number of coins
         * f(1,0) = 0;
         * f(0,1) = 1 ; # of ways of creating 0 value using max 1 number of coins
         * f(i,0) = 0;
         *
         * f(1,1) = f(1-do,1-1) + f(1-d1,1-1)+....f(1-dk,1-1);
         * f(i,t) = f(i-d0,t-1) + f(i-d1,t-1)......f(1-dk,t-1)
         *
         * f(i,t) = ∑ f(i-dk,t-1)
         *          i,k
         */
        int[][] dp = new int[n + 1][t + 1];

        dp[0][0] = 1;
        dp[0][1] = 1;


        IntStream.rangeClosed(0, n).forEach(sValue -> {
            IntStream.rangeClosed(0, t).forEach(sCoinNumber -> {

                if (sValue > 0 && sCoinNumber == 0) {
                    dp[sValue][sCoinNumber] = 0;
                    return;
                }
                IntStream.of(denominations)
                        .filter(j -> sValue - j >= 0)
                        .forEach(sDenom -> {
                            dp[sValue][sCoinNumber] += dp[sValue - sDenom][sCoinNumber - 1];
                        });
            });
        });


        return dp[n][t];
    }

    /**
     * Having unlimited supply of coins, and using the given denominations d[x]
     * what is the number of ways of to make n value using even number of coins;
     */
    public int numberOfWaysToPrepareChangeWithEvenNumberOfCoins(int n, int[] denominations){
       //TODO CONTINUE

        /** d = {1,3,5,10}
         * f(i) -> # of ways to make i value using even number of coins
         * f(0) = 1 (0 number of coins)
         * f(1) = 1coin = 0
         * f(2)  = 1coin1coin =1
         * f(3) = 111 or 3  = f(2) + f(0) = 2
         * f(4) = 1111 or 31 or 13 = f(3) + f(1) = 3
         * f(5) = 11111 or 113 or 131 or 113 or 5 = f(4) + f(3) + f(0) = 5
         * */
        int[] dp = new int[n+1];
        dp[0] = 1;


        IntStream.rangeClosed(5,n).forEach(sVal->{
            IntStream.of(denominations).filter(i->sVal-i>=0).forEach(sDenom->{
                dp[sVal]+=dp[sVal-sDenom];
            });
        });

        return dp[n];
    }

    /**
     * Having unlimited supply of coins, and using the given denominations d[x]
     * what is the number of ways of to make n value using minimum number of coins;
     */
    public int numberOfWaysToPrepareChangeWithMinimumNumberOfCoins(int n , int [] denominations){
        /**
         * f(0) = 0;
         *
         * f(i) = ∑Min[1+ f(i-dk)];
         */
        int dp[ ] = new int[n+1];
        dp[0] = 0;

        var streamHelper = new Object(){
            int min = Integer.MAX_VALUE;
            void reset(){
                this.min = Integer.MAX_VALUE;
            }
        };

        IntStream.rangeClosed(1,n).forEach(sVal->{
            IntStream.of(denominations)
                    .filter(i->sVal-i>=0)
                    .filter(ik->1+dp[sVal-ik] != Integer.MIN_VALUE)
                    .forEach(sDenom->{
                        if(dp[sVal-sDenom]+1<streamHelper.min){
                            streamHelper.min = dp[sVal-sDenom]+1;
                        }
                    });
            dp[sVal] += streamHelper.min;
            streamHelper.reset();
        });

        return dp[n]==Integer.MAX_VALUE ?-1:dp[n];
    }
}
