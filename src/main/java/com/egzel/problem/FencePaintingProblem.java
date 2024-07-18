package com.egzel.problem;

import java.util.HashMap;
import java.util.stream.IntStream;

import com.egzel.lib.model.ProblemBase;
import com.egzel.lib.util.ALogger;

/**
 * You have a fence which has n number of blocks
 * You need to paint all blocks either with red(0) or blue(1)
 * Not more than 2 blocks, same colors can be used
 * How many different ways the paint all fences
 */
public class FencePaintingProblem extends ProblemBase {

    public static void main(String[] args) {


        new Thread(()->{
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int n2 = 50; int want2 = 16;
            FencePaintingProblem fencePaintingProblem = new FencePaintingProblem();
            int res = fencePaintingProblem.numberOfFencePaintingWaysRecursive(n2);
            System.out.println("res:"+res);
            fencePaintingProblem.LOGGER.info("count:" +
                    fencePaintingProblem.counter);
            timer.getBenchmark(timer);
        },"nonmemorization").start();

        new Thread(()->{
            int n2 = 50; int want2 = 16;
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            FencePaintingProblem fencePaintingProblem = new FencePaintingProblem();
            int res = fencePaintingProblem.numberOfFencePaintingWaysRecursiveMem(n2,new HashMap<>());
            System.out.println("res:"+res);
            fencePaintingProblem.LOGGER.info("count:" +
                    fencePaintingProblem.counter);
            timer.getBenchmark(timer);
        },"Memorization").start();
    }
    /**
     * 1. Define objective function
     *  f(i) is the number of ways to paint ith block
     * 2. Base cases
     * f(0) = 1
     * f(1) = 2
     * f(2) = 4;
     * 3. recurrence relation f(i) = f(i-1) + f(i-2)
     * 4. Execution order: Bottom up
     * 5. Result exists in f(n)
     * @param numberofblocks
     * @return
     */
    public int numberOfFencePaintingWays(int numberofblocks){
        int [] dp = new int[numberofblocks+1];
        // bases
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;

        IntStream.rangeClosed(3,numberofblocks).forEach(block->{
           dp[block] = dp[block-1]+dp[block-2];
        });

        return dp[numberofblocks];
    }

    /**
     * We divide the problem in to 2 parts
     * A. calculate the number of different ways if previous color is same
     * B. calculate the number of different ways if previous color is different
     *
     * colors - 0 : blue, 1: green -> j represent colors
     * 1. Define objective function
     * j->{0,1}
     *
     *  f(i,j) = f(i-1,j) + f(0=i-2,1-j)
     *
     *
     * @param numberofblocks
     * @return
     */
    public int numberOfFencePaintingWays2PartProblem(int numberofblocks) {

        int dp[][] = new int[numberofblocks+1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[2][0] = 2;
        dp[2][1] = 2;

        IntStream.range(3, numberofblocks+1).forEach(block -> {
            IntStream.rangeClosed(0, 1).forEach(color -> {
                dp[block][color] = dp[block - 1][1 - color] + dp[block - 2][1 - color];

            });
        });

//        IntStream.range(3, numberofblocks+1).forEach(block -> {
//            IntStream.rangeClosed(0, 1).forEach(color -> {
//                dp[block][color] = dp[block - 1][color] + dp[block - 2][1 - color];
//
//            });
//        });

        return (dp[numberofblocks][0] + dp[numberofblocks][1]);
    }
    //
    // With recursion with memorization
    //
    public int numberOfFencePaintingWaysRecursive(int numberofblocks){
        counter++;
        //base cases
        if(numberofblocks==0)return 1;
        if(numberofblocks==1)return 2;
        if(numberofblocks == 2) return 4;
        int res = numberOfFencePaintingWaysRecursive(numberofblocks-2)+
                numberOfFencePaintingWaysRecursive(numberofblocks-1);
        return res;
    }

    //
    // With recursion with memorization
    //
    public int numberOfFencePaintingWaysRecursiveMem(int numberofblocks, HashMap<Integer,Integer> mem){
        counter++;
        //base cases
        if(numberofblocks==0)return 1;
        if(numberofblocks==1)return 2;
        if(numberofblocks == 2) return 4;
        int res1 = -1;
        int res2 = -1;
        if(mem.containsKey(numberofblocks-1)){
            res1 = mem.get(numberofblocks-1);
        }else {
            res1 = numberOfFencePaintingWaysRecursiveMem(numberofblocks-1,mem);
            mem.put(numberofblocks-1,res1);
        }
        if(mem.containsKey(numberofblocks-2)){
            res2 = mem.get(numberofblocks-2);
        }else {
            res2 = numberOfFencePaintingWaysRecursiveMem(numberofblocks-2,mem);
            mem.put(numberofblocks-2,res2);
        }

        return res1+res2;
    }
}
