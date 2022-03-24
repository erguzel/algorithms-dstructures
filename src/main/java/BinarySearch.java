/**
 * Searches in sorted arrays
 */
public class BinarySearch {

    public int searchRecursive(int arr[], int left, int right , int key){
        if (arr.length == 0)return -1;

        int mid = (left+right)/2;

        if(arr[mid]==key){
            return arr[mid];
        }else if(arr[mid]<key && mid<arr.length){

           return searchRecursive(arr,mid+1,right,key);

        }else if(arr[mid]>key && mid<arr.length){

           return searchRecursive(arr,left,mid-1,key);

        }

        return -1;
    }

    /**
     * Returns the index of key element
     * @param arr
     * @param key
     * @return
     */
    public int search(int[] arr, int key){
        if (arr.length == 0)return -1;


        int low = 0;
        int high = arr.length;

        while (low<=high){
            int mid = (low+high)/2;
            if(arr[mid]==key){
                return arr[mid];
            }
            else if(arr[mid]<key){
                low = mid+1;
            }else if(arr[mid]>key){
                high = mid-1;
            }
        }

        return -1;
    }
}
