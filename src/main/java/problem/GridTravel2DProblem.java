package problem;

import lib.model.ProblemBase;
import lib.util.ALogger;

import java.util.HashMap;
import java.util.Map;

/**
 * On a 2d grid, you are in top left. You can go right or down only. You need to go to bottom
 * right of the grid.
 * How many different path you can follow?
 */

public class GridTravel2DProblem extends ProblemBase {

    public static void main(String[] args){
        GridTravel2DProblem grr = new GridTravel2DProblem();
        int param1 = 8;
        int param2 = 8;

        new Thread(()->{
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            long num = grr.traverse(param1,param2);
            grr.LOGGER.info("resultTraverse:"+num);
            grr.LOGGER.info("callcountTraverse:"+grr.counter);
            timer.getBenchmark(timer,"callcountTraverse");

        }).start();


        new Thread(()->{
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            long num = grr.traverseMem(param1,param2,new HashMap<>());
            grr.LOGGER.info("resultTraverseMem:"+num);
            grr.LOGGER.info("callcountTraverseMem:"+grr.counter1);
            timer.getBenchmark(timer,"callcountTraverseMem");

        }).start();
    }

    public long traverse(int m , int n){
        this.counter++;
        // base cases
        if(m==0 || n == 0)return 0;
        if(m==1&& n == 1)return 1;

        return traverse(m-1,n) + traverse(m,n-1);

    }

    public long traverseMem(int m , int n, Map<String,Long> mem){
        this.counter1++;
        String key = m+","+n;
        if(mem.containsKey(key)){
            return mem.get(key);
        }
        // base cases
        if(m==0 || n == 0)return 0;
        if(m==1&& n == 1)return 1;

        mem.put(key,traverseMem(m-1,n,mem) + traverseMem(m,n-1,mem));

        return mem.get(key);

    }
}