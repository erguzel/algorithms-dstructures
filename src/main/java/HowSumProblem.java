import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Write a function that takes in a targetSum and array of numbers as arguments
 * The function should return an array containing any combination of elements that add up tp
 * exactly the target sum. If there is no combination that adds up the target sum, return null
 * <p>
 * if multiple combinations found, you may return any of these.
 */
public class HowSumProblem extends ProblemBase {

    public static class SumTracker {
        int totalWeight = Integer.MIN_VALUE;
        int addUpWeight = 0;
        List candididates = new ArrayList<>();

        @Override
        public String toString() {
            return "totalWeight:" + totalWeight + "\n" +
                    "addUpWeight:" + addUpWeight + "\n" +
                    "candidates:" + candididates;
        }

        public SumTracker(int totalWeight, int addUpWeight) {
            this.totalWeight = totalWeight;
            this.addUpWeight = addUpWeight;
        }

        public SumTracker() {
        }
    }

    public static void main(String[] args) {

        int param1 = 10;
        int[] param2 = {1,2,3,5};


        new Thread(()->{
            HowSumProblem grr = new HowSumProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int[] num = grr.howSum(param1,param2);
            grr.LOGGER.info("howSumCall:"+ (num==null?"null": Arrays.stream(num).boxed().collect(Collectors.toList())));
            grr.LOGGER.info("howSumCall:"+grr.counter);
            timer.getBenchmark(timer,"howSum");
        }).start();

        new Thread(()->{
            HowSumProblem grr = new HowSumProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int[] num = grr.howSum(param1,param2,new HashMap<Integer,int[]>());
            grr.LOGGER.info("howSumMemcall:"+ (num == null?"null": Arrays.stream(num).boxed().collect(Collectors.toList())));
            grr.LOGGER.info("howSumMemcall:"+grr.counter1);
            timer.getBenchmark(timer,"howSumMem");
        }).start();

    }

    public int[] howSum(int targetSum, int[] numbers) {
        return howsumHelper(targetSum, numbers);
    }

    public int[] howSum(int targetSum, int[] numbers, HashMap<Integer, int[]> mem) {
        return howsumHelper(targetSum, numbers, mem);
    }

    private int[] howsumHelper(int targetsum, int[] numbers) {
        counter++;

        if (targetsum == 0) return new int[0];
        if (targetsum < 0) return null;

        for (int i = 0; i < numbers.length; i++) {
            int remnant = targetsum - numbers[i];
            int[] result = howsumHelper(remnant, numbers);
            if (result != null) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = numbers[i];
                return result;
            }
        }

        return null;
    }

    private int[] howsumHelper(int targetsum, int[] numbers, Map<Integer, int[]> mem) {
        counter1++;
        if (mem.containsKey(targetsum)) return mem.get(targetsum);
        if (targetsum == 0) return new int[0];
        if (targetsum < 0) return null;

        for (int i = 0; i < numbers.length; i++) {
            int remnant = targetsum - numbers[i];
            int[] result = howsumHelper(remnant, numbers, mem);
            if (result != null) {
                result = Arrays.copyOf(result, result.length + 1);
                result[result.length - 1] = numbers[i];
                mem.put(targetsum, result);
                return mem.get(targetsum);
            }
        }

        mem.put(targetsum, null);
        return null;
    }




}















