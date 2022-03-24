package deprecated;

import lib.model.ProblemBase;

import java.util.stream.IntStream;

/**
 * You have unlimited coins in denominations of
 * 1, 3,5 and 10 units i.e to make a 4£ you can give either 1-1-1-1-1, 3-1 or 1-3 banknodes
 * for given amount of total payment n
 * how many different ways to prepare the payment amount
 */
public class CoinChangeProblemDeprecated extends ProblemBase {

    /**
     * 1. Define objective function
     * f(i) -> number of different ways of preparing the i amount of payment
     * 2. Define base cases
     * f(0)=1; f(1) = 1 ; f(2) = 1; f(3) = 2
     * 3. Define recurrence relation
     * f(i) = f(i-1)+f(i-3)+f(i-5)+f(i-10) till the all denominations satisfied
     * 4. Execution order, bottom up
     * 5. Result is located in f(n)
     *
     * @param n
     * @return
     */
    public int numberOfWaysOfpayment(int n) {
        // validation
        int[] dp = new int[n + 1];//result will be in dp[n]
        // base cases
        dp[0] = 1;
        // if statements cover the - indexes cases
        IntStream.rangeClosed(0, n).forEach(amount -> {

            if (amount - 1 >= 0) dp[amount] += dp[amount - 1];
            if (amount - 3 >= 0) dp[amount] += dp[amount - 3];
            if (amount - 5 >= 0) dp[amount] += dp[amount - 5];
            if (amount - 10 >= 0) dp[amount] += dp[amount - 10];
        });

        return dp[n];
    }

    /**
     * Same problem,
     * this time denominations are given
     *
     * @param n
     * @param denominations
     * @return
     */
    public int numberOfWaysOfPaymentWithDenominations(int n, int[] denominations) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        IntStream.rangeClosed(0, n).forEach(amount -> {
            IntStream.range(0, denominations.length).forEach(denomidx -> {
                if (amount - denominations[denomidx] >= 0) {
                    dp[amount] += dp[amount - denominations[denomidx]];
                }
            });
        });
        return dp[n];
    }

    /**
     * Same problem,
     * this time no unlimited coins but you must use exactly t number of coins
     * f(i,t) number of ways of creating i amount using t number of coins
     * BaseCases
     * f(0,0) = 1 -> number of making 0 cent using 0 number of coins
     * f(1,0) = 0 -> number of making 1 cent using 0 number of coins..
     * f(i,0)=0; i>0
     * <p>
     * f(0,1) = 0 -> number of making 0 cent using 1 number of coins
     * f(1,1) = 1 -> number of making 1 cent using 1 number of coins
     * <p>
     * transition function as d1..dn are denominations
     * f(i,t) = f(i-d1,t-1)+f(i-d2,t-1)+...f(i-d_n , t-1);
     *
     * @param amount
     * @param denominations
     * @param numberofcoins used exactly
     * @return
     */
    public int numberOfWaysOfPaymentWithDenominationsWithNumberOfCoins(int amount, int[] denominations, int numberofcoins) {

        int[][] dp = new int[amount + 1][numberofcoins + 1];
        dp[0][0] = 1;

        IntStream.rangeClosed(0, amount).forEach(sAmount -> {
            IntStream.rangeClosed(0, numberofcoins).forEach(sCoinnumber -> {
                //number of ways of making amnt amount with 0 number of coins is 0
                if (sAmount > 0 && sCoinnumber == 0) {
                    dp[sAmount][sCoinnumber] = 0;
                    return;
                }
                IntStream.of(denominations).forEach(sDenom -> {
                    if (sAmount - sDenom >= 0) {
                        dp[sAmount][sCoinnumber] += dp[sAmount - sDenom][sCoinnumber - 1];
                    }
                });
            });
        });

        return dp[amount][numberofcoins];
    }

    /**
     * Same problem,
     * this time we are allowed to use maximum t numner of coins instead of being forced to use exactly t number of coins
     * f(i,t) number of ways of creating i amount using at most t number of coins
     * BaseCases
     * f(0,0) = 1 -> number of making 0 cent using maximum 0 number of coins
     * f(1,0) = 0 -> number of making 1 cent using maximum 0 number of coins..
     * f(i,0)=0; i>0
     * <p>
     * f(0,1) = 1 -> number of making 0 cent using maximum 1 number of coins (only difference from previous problem)
     * f(1,1) = 1 -> number of making 1 cent using 1 number of coins
     * <p>
     * transition function as d1..dn are denominations
     * f(i,t) = f(i-d1,t-1)+f(i-d2,t-1)+...f(i-d_n , t-1);
     *
     * @param n
     * @param denominations
     * @param t
     * @return
     */
    public int numberOfWaysOfPaymentWithDenominationsWithThresholdNumberOfCoins(int n, int[] denominations, int t) {
        int[][] dp = new int[n + 1][t + 1];
        dp[0][0] = 1;
        dp[0][1] = 1;

        IntStream.rangeClosed(0, n).forEach(sAmount -> {
            IntStream.rangeClosed(0, t).forEach(sCoinnumber -> {
                //number of ways of making amnt amount with 0 number of coins is 0
                if (sAmount > 0 && sCoinnumber == 0) {
                    dp[sAmount][sCoinnumber] = 0;
                    return;
                }
                if (sAmount == 0 && sCoinnumber > 0) {
                    dp[sAmount][sCoinnumber] = 1;
                    return;
                }
                IntStream.of(denominations).forEach(sDenom -> {
                    if (sAmount - sDenom >= 0) {
                        dp[sAmount][sCoinnumber] += dp[sAmount - sDenom][sCoinnumber - 1];
                    }
                });
            });
        });

        return dp[n][t];
    }

    /**
     * Same problem, you have unlimited supply of coins, and you are only allowed to use even number of coins
     * <p>
     * 2 Main cases t= [0,1] 0 -> even and odd representations
     * f(i,0) -> number of ways of making i amount using even number of coins
     * f(i,1) -> number of ways of making i amount using odd number of coins
     * <p>
     * f(0,0) = 1 #of ways of making 0 amount using even number of coins
     * f(0,1) = 1
     * f(1,0) =0
     * f(1,1) = 1
     * f(2,0) =1
     * f(2,1) = 0
     * f(3,0) = 0
     * f(3,1) = 1 (1x1) or 3(0)
     * <p>
     * f(i,0) = f(i-d0, 1) + f(i-d1, 1) + f(i-d2,1) .... + f(i-dn, 1); //
     * f(i,1) = f(i-d0, 0) + f(i-d1, 0) + f(i-d2,0) .... + f(i-dn, 0); //
     *
     * @param n
     * @param denominations
     * @return
     */
    public int numberOfWaysOfPaymentWithDenominationsUsingEvenNumberOfCoins(int n, int[] denominations) {
        int[][] dp = new int[n + 1][2];
        int t[] = new int[]{0, 1};// 0 for even number of coins 1 for opposit

        dp[0][0] = 1;
        dp[0][1] = 1;

        IntStream.rangeClosed(0, n).forEach(sAmount -> {
            IntStream.of(denominations).filter(f -> sAmount - f >= 0)
                    .forEach(sDenom -> {
                        dp[sAmount][0] += dp[sAmount - sDenom][1];
                        dp[sAmount][1] += dp[sAmount - sDenom][0];
                    });
        });

        return dp[n][0];
    }

    /**
     * Same problem, we now must find the total number of unique ways to create given amount n using unlimited supply of coins
     * and given denominations
     *
     * @param n
     * @param denominations
     * @return
     */
    public int numberOfUNIQUEWaysOfPaymentWithDenominationsUsingEvenNumberOfCoins(int n, int[] denominations) {

        return -1;
    }
}
