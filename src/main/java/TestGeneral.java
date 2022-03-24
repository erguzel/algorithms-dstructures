import java.util.*;
import java.util.stream.IntStream;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no
 * elements without changing the order of the remaining elements. For example, [3,6,2,7] is a
 * subsequence of the array [0,3,1,6,2,2,7].
 */
public class TestGeneral {
    public static class SumHelper extends Stack<Integer>{
        int total = 0;
        int targetsum = -1;

        public SumHelper(int targetsum) {
            this.targetsum = targetsum;
        }

        public boolean add(Integer item) {
            boolean res = false;
            if(this.total-item>=0){
                this.push(item);
                res = true;
            }
             return res;
        }


        public Integer pop() {
            int poped = super.pop();
            this.total = this.total - poped;
            return poped;
        }
        public boolean isFound (){
         return  this.total == this.targetsum;
        }
    }

    public static class OptimalDistance {
        private int gym;
        private int school;
        private int store;
    }

    public static void main(String[] args) {


        int[] cand = {1,3,8,2,7,9};

        TreeSet<Integer> seet = new TreeSet<>((a,b)->a+b<=10?-1:0);
        Stack<Integer> cal = new Stack<>();

        int i = 0;
        int j = 0;
        int target = 10;
        SumHelper sumHelper = new SumHelper(10);
        while (i<cand.length){
           boolean space = sumHelper.add(cand[i]);
           if(sumHelper.isFound()){
               System.out.println("HELLO");
           }
           if(!space){
               while (j<cand.length){
                   boolean more = sumHelper.add(cand[j]);
                   if(sumHelper.isFound()){
                       System.out.println("HELLO");
                   }
                   if(!more){
                       sumHelper.pop();
                       j++;
                   }
               }
           }
           i++;
        }

        System.out.println("alla");

    }

    private void charish() {
        //33-126 characters
        int[] charfreq = new int[127];

        String name = "ooooolgun";

        IntStream.range(0, name.length()).forEach(a -> {
            System.out.println("charats:" + name.charAt(a));
            charfreq[name.charAt(a)]++;
        });

        IntStream.range(0, charfreq.length).forEach(x -> System.out.println(charfreq[x]));


        System.out.println("freq of o " + charfreq[name.charAt(0)]);
    }
}
