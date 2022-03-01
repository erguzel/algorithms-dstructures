import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Strategy : divide and conquer
 * worstcase : O(n^2)
 * bestcase : O(nlog(n))
 */


public class QuickSort{
    static  ALogger<QuickSort> LOGGER = new ALogger<>(QuickSort.class);
    public void sort(int[] arr, int left, int right){
        int partidx = partite(arr,left,right) ;//
        if(left<partidx-1){
            sort(arr,left,partidx-1);
        }//if
        if(partidx<right){
            sort(arr,partidx,right);
        }//if
    }//sort

    public int partite(int [] arr, int left, int right){
        int pivot = arr[ (right+left)/2];
        while(left<= right){
            while(arr[left]<pivot){left++;}//while left
            while(arr[right]>pivot){right--;}//while right
            if(left<=right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right]=temp;
                left++;
                right--;
            }//if left - right
        }//while outer
        return left;
    }//partite
    public static void main(String[] args) {

       //  int [] arr = {1,56,0,-2,5,23,-29,213,9};
        int[] arr = SampleData.Generators.generateIntArray(20, -69, 64);

        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        ALogger.TIMER timer = new ALogger.TIMER();
        timer = timer.startTimer();
        QuickSort sort = new QuickSort();
        sort.sort(arr,0,arr.length-1);
        LOGGER.info(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        timer.getBenchmark(timer);


    }
}//class QuickSort


