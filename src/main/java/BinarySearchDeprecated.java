import com.sun.source.tree.BreakTree;

public class BinarySearch {

    public static int count =0;
    public static int count1 =0;
    public static void main(String[] args) {

        int [] list = {-8,1,2,3,4,5,6,7,12,128};

        int res = searchBinary(-8,list);

        System.out.println(res);
        System.out.println(count1);

        int res1 = searchBinaryRecursive(list,0,list.length-1,-8);

        System.out.println(res1);
        System.out.println(count);

    }
    public static int searchBinary(int value, int[] list){

        int low = 0;
        int high = list.length-1;

        while (low<=high){

            count1++;

            int mid = (high+low)/2;

            if(list[mid] == value){
                return value;
            }
            else if(value<list[mid]){

                high = mid-1;

            }else if(value>list[mid]){

                low = mid+1;
            }
        }
        return -1;
    }

    public static int searchBinaryRecursive(int[] arr, int low, int high,int key){

        count++;
        if(low==high){
            if(arr[low] == key){
                return arr[low];
            }else
                return -1;
        }else {
            int mid = (low+high)/2;
            if(key==arr[mid]){
                return arr[mid];
            }else if(key<arr[mid]){
                return searchBinaryRecursive(arr,low,mid-1,key);
            }else if(key>arr[mid]){
                return searchBinaryRecursive(arr,mid+1,high,key);
            }
        }

        return -1;
    }

}
