package sort;

import lib.util.ALogger;
import lib.util.DataUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseArray {

    static ALogger<ReverseArray> LOGGER = new ALogger<>(ReverseArray.class);
    public static void reverse(Object [] arr) {


        int endIndex = 0;
        for(int i = 0; i < arr.length ;i++){
            endIndex = (arr.length-i-1);
            if(i>=endIndex){
                return;
            }
            Object  temp = arr[i];
            arr[i] = arr[endIndex];
            arr[endIndex] = temp;


        }//for
    }//reverse

    public static void main(String[] args) {

        Object [] arr = Arrays.stream(DataUtil.Generators.generateIntArray(15,-4,12)).mapToObj(a->a).toArray();

        LOGGER.info(Arrays.stream(arr).collect(Collectors.toList()));

        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();

        ReverseArray reverseArray = new ReverseArray();
        reverseArray.reverse(arr);
        LOGGER.info(Arrays.stream(arr).collect(Collectors.toList()));
        timer.getBenchmark(timer);

    }
}//class
