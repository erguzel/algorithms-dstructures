import lib.model.ProblemBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * There is a ladder consisted of n steps
 * Following problems are derived from that fact
 */
public class LadderClimbingProblem extends ProblemBase {

    /**
     * You are allowed to take either 1 or 2 steps at a time
     * find the number of ways to reach the top?
     * @return
     */
    public int numberOfWaysToReachTop(int numberofsteps){
        if(numberofsteps==0)return 1;
        if(numberofsteps==1)return 1;
        // result will be in the numberofsteps th element
        int [] dp  = new int[numberofsteps+1];
        //base cases
        dp[0] = 1; // # of ways to reach 0th ladder using either 1 or 2 jumps
        dp[1] = 1;
        dp[2] = 2;//1step and f(1) or 2steps and f(0) = 2;
        //dp[3] = 1step and f(2) or 2steps and f(1) = 3
        // recurrence relation;
        // f(i) = number of ways of reaching ith step using 1 or 2 jumps at a time
        // f(i) = f(i-1) + f(i-2);

        IntStream.rangeClosed(2,numberofsteps).forEach(sLadderNo->{
            dp[sLadderNo] = dp[sLadderNo-1] + dp[sLadderNo-2];
        });

        return dp[numberofsteps];
    }

    /**
     * You are allowed to make a jump size at once defined in array s[]
     * how many ways to reach the top?
     * @param numberofsteps
     * @param allowedJumpSizesAtOnce
     * @return
     */
    public int numberOfWaysToReachTop(int numberofsteps, int[] allowedJumpSizesAtOnce){

        int[] dp = new int[numberofsteps+1];
        //base cases
        // f(i) number of different ways to reach ith step using allowed jump sizes only
        dp[0] = 1;// number of ways to reach 0th step
        // f(i) = s[0]step and f(i-s[0])) + s[1] step and f(i-s[1]) .... s[n]step and f(i-s[n]) -> i>s[k]
        //f(i) = f(i-s0) + f(i-s1)....f(i-sk)
        //  ∑ ∑f(i-s(k))
        //  i,k
        IntStream.rangeClosed(1,numberofsteps).forEach(sLadderNo->{
            IntStream.of(allowedJumpSizesAtOnce)
                    .filter(i->sLadderNo-i>=0)
                    .forEach(sJumpSize->{
                        dp[sLadderNo] += dp[sLadderNo-sJumpSize];
                    });
        });

        return dp[numberofsteps];

    }

    /**
     * You are allowed to make a jump size at once defined in array s[]
     * what is the minimum number of jumps to reach the top
     * @param numberofsteps
     * @param allowedJumpSizesAtOnce
     * @return
     */
    public int minimumNumberOfJumpsToTheTop(int numberofsteps, int[] allowedJumpSizesAtOnce){

        int[] dp = new int[numberofsteps+1];
        //base cases
        // f(i) minimum number of ways to reach ith step using allowed jumpSizes
        // f(0) = 1 // minimum number of ways to reach 0th step
        // f(1) = 1
        // f(i) = 1 + Min( s[0]step and f(1-s[0]), s[1]steps and f(1-s[1]), ..... s[n]steps and f(1-s[n])) =
        //  f(i) = 1+ ∑ Min[f(i- s[k])]
        //         i,k
        dp[0] = 0;
        dp[1] = 1;
//        dp[2] = 2;
//        dp[3] = 1;
////        dp[4] = 2;
////        dp[5] = 4;

        var streamHelper = new Object(){
            int min = Integer.MAX_VALUE;
            void reset(){
                this.min = Integer.MAX_VALUE;
            }
        };

        IntStream.rangeClosed(2,numberofsteps).forEach(sLadderNo->{
            IntStream.of(allowedJumpSizesAtOnce)
                    .filter(i->sLadderNo-i>=0)
                    .filter(ik->dp[sLadderNo-ik]+1 != Integer.MIN_VALUE)
                    .forEach(sStepSize->{
                        if(dp[sLadderNo-sStepSize]+1<=streamHelper.min){
                            streamHelper.min = dp[sLadderNo-sStepSize]+1;
                        }
                    });

            dp[sLadderNo] += streamHelper.min;
            streamHelper.reset();

        });


        return dp[numberofsteps]==Integer.MAX_VALUE?-1:dp[numberofsteps];
    }

    /**
     * M[] represents the forbidden ladders to step on. One can not step on those ladder steps.
     * what is the number of ways to reach the top
     * @param numberofladders
     * @param allowedJumpSizesAtOnce
     * @param forbidden
     * @return
     */
    public int numberOfWaysToReachTop(int numberofladders,int[] allowedJumpSizesAtOnce, boolean[] forbidden ){
        int[] dp = new int[numberofladders+1];
        /**
         * M(k) = { 1 forbidden
         *          0 else }
         *  f(i) number of ways to reach ith ladder without stepping forbidden steps
         *  f(0) = 1 * [1-M(0)] ;
         *  f(1) = 1 * [1-M(0)]
         *  f(2) = f(2-s[0]) * 1-M[2] + f(2-s[1]) * 1-M[2] .....+ f(2-s[k]) * 1-M[2]
         *
         *  f(i) = f(i-s[0]) * 1-M[i] + f(i-s[1]) * 1-M[i] .......+ f(i-s[k]) * 1-M[i];
         *
         */

        dp[0] = 1 * (forbidden[0]?0:1);

        IntStream.rangeClosed(1,numberofladders).forEach(sLadderNo->{
            IntStream.of(allowedJumpSizesAtOnce)
                    .filter(i->sLadderNo-i>=0)
                    .forEach(sStepSize->{
                        dp[sLadderNo] += dp[sLadderNo-sStepSize] * (forbidden[sLadderNo-1]?0:1);

            });
        });

        return dp[numberofladders];
    }

    /**
     * c[x] represents the cost of stepping at xth ladder.
     * find the minimum cost to reach the top
     * @param numberofladders
     * @param allowedJumpSizesAtOnce
     * @param costarray
     * @return
     */
    public int minimumCostToReachTop(int numberofladders, int[]allowedJumpSizesAtOnce, int[]costarray){
        /**
         * f(i) minimum cost to reach ith number
         *
         * f(0) = c[0]
         * f(1) = Min(f(1-s[0]) ... f(1-sk)) + c[1]
         * f(2) = Min[ f(2- s[0], f(2-s[1].....f(2-s[k]))) + c[2];
         *
         * f(i) =  ∑ (Min{ f(i-s(k) } + c[i]
         *        i,k
         *
         */

        int[] dp= new int[numberofladders+1];
        dp[0] = costarray[0];

        var streamHelper = new Object(){
            int min = Integer.MAX_VALUE;
            void reset (){
                this.min = Integer.MAX_VALUE;
            }
        };

        IntStream.rangeClosed(1,numberofladders).forEach(sLadderNo->{
            IntStream.of(allowedJumpSizesAtOnce).filter(i->sLadderNo-i>=0)
                    .forEach(sStepSize->{
                        if(dp[sLadderNo-sStepSize]<streamHelper.min){
                            streamHelper.min = dp[sLadderNo-sStepSize];
                        }
                    });
            dp[sLadderNo] += costarray[sLadderNo] + streamHelper.min;
            streamHelper.reset();
        });

        return dp[numberofladders];
    }

    /**
     * M[] represents the array which contains the allowed jump sizes at once
     * what is the path for the minimum number of steps to reach the top?
     * @param numberofsteps
     * @param allowedJumpSizesAtOnce
     * @return
     */
    public int[] minimumNumberOfJumpsPath(int numberofsteps, int[]allowedJumpSizesAtOnce ){

        int[] dp = new int[numberofsteps+1];
        //base cases
        // f(i) minimum number of ways to reach ith step using allowed jumpSizes
        // f(0) = 1 // minimum number of ways to reach 0th step
        // f(1) = 1
        // f(i) = 1 + Min( s[0]step and f(1-s[0]), s[1]steps and f(1-s[1]), ..... s[n]steps and f(1-s[n])) =
        //  f(i) = 1+ ∑ Min[f(i- s[k])]
        //         i,k
        dp[0] = 0;
        dp[1] = 1;
//        dp[2] = 2;
//        dp[3] = 1;
         int[] from = new int[numberofsteps+1];
         from[0] = -1;
         from[1] = 0;
//         from[2] = 0;
////        dp[4] = 2;
////        dp[5] = 4;

        var streamHelper = new Object(){
            int min = Integer.MAX_VALUE;
            int minId = -1;
            void reset(){
                this.min = Integer.MAX_VALUE;
                this.minId = -1;
            }
        };

        IntStream.rangeClosed(2,numberofsteps).forEach(sLadderNo->{
            IntStream.of(allowedJumpSizesAtOnce)
                    .filter(i->sLadderNo-i>=0)
                    .forEach(sStepSize->{
                        if(dp[sLadderNo-sStepSize]<=streamHelper.min){
                            streamHelper.min = dp[sLadderNo-sStepSize];
                            streamHelper.minId = sLadderNo-sStepSize;
                        }
                    });

            dp[sLadderNo] += (1+ streamHelper.min);
            from[sLadderNo] = streamHelper.minId;
            streamHelper.reset();

        });

        List<Integer> path = new ArrayList<>();

        int looper = numberofsteps;
        path.add(looper);
        while (from[looper] != -1){
            path.add(from[looper]);
            looper = from[looper];
        }

        Collections.reverse(path);

        int[] res = path.stream().mapToInt(a->a).toArray();

        return res;

    }
}
