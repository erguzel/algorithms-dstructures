import java.util.HashMap;
import java.util.Map;

/**
 * Write a function that takes in a targetSum and array of numbers. Return true if the sum can
 * be generated using numbers in array
 * you can use an element of the array as many times as needed
 * elements are nonnegative numbers
 */
public class CanSumProblem extends ProblemBase{

    public static void main(String[] args) {

        CanSumProblem grr = new CanSumProblem();
        int param1 = 8;
        int[] param2 = {2,3,5};

        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        int result = 0;
        boolean num = grr.canSum(param1,param2);
        grr.LOGGER.info("canSum:"+num);
        grr.LOGGER.info("canSumCount:"+grr.counter);
        timer.getBenchmark(timer,"canSum");

//        new Thread(()->{
//            ALogger.TIMER timer = new ALogger.TIMER();
//            timer.startTimer();
//            int result = 0;
//            boolean num = grr.canSum1(param1,param2,result);
//            grr.LOGGER.info("canSum:"+num);
//            grr.LOGGER.info("canSumCount:"+grr.counter);
//            timer.getBenchmark(timer,"canSum");
//
//        }).start();

//        new Thread(()->{
//            ALogger.TIMER timer = new ALogger.TIMER();
//            timer.startTimer();
//            boolean num = grr.canSumMem(param1,param2,new HashMap<Integer,Boolean>());
//            grr.LOGGER.info("canSumMem:"+num);
//            grr.LOGGER.info("canSumCountMem:"+grr.counter1);
//            timer.getBenchmark(timer,"canSumMem");
//
//        }).start();
    }

    public boolean canSum(int targetSum, int []numbers){
        this.counter++;
        if(targetSum == 0) return true;
        if(targetSum<0) return false;

        for(int i = 0; i < numbers.length;i++){
            int reminder = targetSum - numbers[i];
            if(canSum(reminder,numbers)){
                return true;
            }
        }

        return false;

    }

    public boolean canSumMem(int targetSum, int []numbers, Map<Integer,Boolean> mem){
        this.counter1++;
        int key = targetSum;
        if(mem.containsKey(key)){
            return mem.get(key);
        }
        if(targetSum == 0) return true;
        if(targetSum<0) return false;

        for(int i = 0; i < numbers.length;i++){
            int reminder = targetSum - numbers[i];
            if(canSumMem(reminder,numbers,mem)){
                mem.put(reminder,true);
                return true;
            }
        }

        mem.put(targetSum,false);
        return false;

    }



}