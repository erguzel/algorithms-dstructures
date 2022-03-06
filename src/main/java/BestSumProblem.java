import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a function that takes targetSum and an array of numbers as argument.
 * The function should return the shortest combination of numbers that add up to that target sum.
 */
public class BestSumProblem extends ProblemBase {


    public static void main(String[] args) {
        int param1 = 100;
        int[] param2 = {1, 2, 5, 25};

        new Thread(() -> {
            BestSumProblem grr = new BestSumProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int[] num = grr.bestSum(param1, param2);
            grr.LOGGER.info("bestSumCall:" + (num == null ? "null" : Arrays.stream(num).boxed().collect(Collectors.toList())));
            grr.LOGGER.info("bestSumCall:" + grr.counter);
            timer.getBenchmark(timer, "bestSum");
        }).start();

        new Thread(() -> {
            BestSumProblem grr = new BestSumProblem();
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int[] num = grr.bestSum(param1, param2, new HashMap<Integer, int[]>());
            grr.LOGGER.info("bestSumCallMem:" + (num == null ? "null" : Arrays.stream(num).boxed().collect(Collectors.toList())));
            grr.LOGGER.info("bestSumCallMem:" + grr.counter1);
            timer.getBenchmark(timer, "bestSumCallMem");
        }).start();
    }

    public int[] bestSum(int targetsum, int[] arr) {

        return bestSumHelper(targetsum, arr);
    }

    public int[] bestSum(int targetsum, int[] arr, Map<Integer, int[]> mem) {

        return bestSumHelper(targetsum, arr, mem);
    }

    public int[] bestSumHelper(int targetsum, int[] arr) {
        counter++;
        if (targetsum == 0) {
            return new int[0];
        }
        if (targetsum < 0) return null;

        int[] shortest = null;

        for (int i = 0; i < arr.length; i++) {
            int remnant = targetsum - arr[i];
            int[] list = bestSumHelper(remnant, arr);
            if (list != null) {
                list = Arrays.copyOf(list, list.length + 1);
                if (shortest == null || list.length < shortest.length) {
                    shortest = list;
                }
            }
        }

        return shortest;
    }

    public int[] bestSumHelper(int targetsum, int[] arr, Map<Integer, int[]> mem) {
        counter1++;

        if (mem.containsKey(targetsum)) return mem.get(targetsum);
        if (targetsum == 0) {
            return new int[0];
        }
        if (targetsum < 0) return null;

        int[] shortest = null;
        mem.put(targetsum, shortest);

        for (int i = 0; i < arr.length; i++) {
            int remnant = targetsum - arr[i];
            int[] list = bestSumHelper(remnant, arr, mem);
            if (list != null) {
                list = Arrays.copyOf(list, list.length + 1);
                list[list.length - 1] = arr[i];
                if (shortest == null || list.length < shortest.length) {
                    shortest = list;

                }
            }
        }

        mem.put(targetsum, shortest);
        return shortest;
    }
}
