/**
 * Write a function that takes a number as argument, returns the nth number of fibonacci sequence
 */
public class FibonacciSeriesProblem extends ProblemBase{

    public static void main(String[] args){
        FibonacciSeriesProblem grr = new FibonacciSeriesProblem();
        int param1 = 15;

        new Thread(()->{
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int num = grr.getFibonacciNumber(param1);
            grr.LOGGER.info("result_getFibonacciNumber:"+num);
            grr.LOGGER.info("count_getFibonacciNumber:"+grr.counter);

            timer.getBenchmark(timer,"getFibonacciNumber");

        }).start();


        new Thread(()->{
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            int num = grr.getFibonacciNumberMemorization(param1,new Object[param1+1]);
            grr.LOGGER.info("result_getFibonacciNumberMemorization:"+num);
            grr.LOGGER.info("count_getFibonacciNumberMemorization:"+grr.counter1);
            timer.getBenchmark(timer,"getFibonacciNumberMemorization");

        }).start();
    }


    public int getFibonacciNumber(int n){
        counter++;
        return n <= 2?1:
                getFibonacciNumber(n-2)+getFibonacciNumber(n-1);


    }

    public int getFibonacciNumberMemorization(int n,Object[] memo){
        counter1++;

        if(memo[n] != null){
            return (int)memo[n];
        }
        if(n <=2 )
            return 1;


        memo[n] = getFibonacciNumberMemorization(n-1,memo)+getFibonacciNumberMemorization(n-2,memo);
        return (int)memo[n];
    }


}