package sort;

/**
 * Searches in sorted arrays
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] arra = {-1, 2,3,44,55,63};

        BinarySearch generalTest = new BinarySearch();
        int res  = generalTest.search(arra, 55);

        System.out.println(res);
    }

    public int searchRecursive(int arr[], int left, int right , int key){
        if (arr.length == 0)return Integer.MIN_VALUE;

        int mid = (left+right)/2;

        if(arr[mid]==key){
            return arr[mid];
        }else if(arr[mid]<key && mid<arr.length){

           return searchRecursive(arr,mid+1,right,key);

        }else if(arr[mid]>key && mid<arr.length){

           return searchRecursive(arr,left,mid-1,key);

        }

        return Integer.MIN_VALUE;
    }

    // log(n)
//sorted arrays
    public int search(int arr[], int key){

        int low = 0;
        int high = arr.length-1;

        while(low<=high){
            int  mid = (low+high)/2;

            if(key==arr[mid]){
                return key;
            }//if found
            else if(key< arr[mid]){
                high = mid-1;
            }// key at left
            else if(key>arr[mid]){
                low = mid +1;
            }//key at right
        }//while low at low

        return Integer.MIN_VALUE;
    }//search
}
