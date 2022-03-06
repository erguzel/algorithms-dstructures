import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given a sorted array of integers and an integer target
 * find the index of the first and the last position of target in array.
 * if target is not in array, return [-1,-1]
 */
public class FirstAndLastPositionProblem extends ProblemBase {


    public static void main(String[] args) {
        int arr[] = {2, 4, 5, 5, 5, 5, 5, 7, 9, 9};
        int target = 9;
        FirstAndLastPositionProblem firstAndLastPositionProblem = new FirstAndLastPositionProblem();
        int[] res = firstAndLastPositionProblem.findIdxBinary(arr,target);
        firstAndLastPositionProblem.LOGGER.info(Arrays.stream(res).boxed().collect(Collectors.toList()));

    }

    /**
     * Brute force ~O(n)
     * @param target
     * @param arr
     * @return
     */
    public int[] findFistAndLastindex(int target, int[] arr) {
        int idx = 0;
        int endidx = -1;
        int startidx = -1;

        while (idx < arr.length) {
            if (arr[idx] != target) {
                idx++;
            } else {
                int idx2 = idx + 1;
                while (idx2 < arr.length && arr[idx2] == target) {
                    idx2++;
                }
                if (idx2 > idx) {
                    endidx = idx2 - 1;
                    startidx = idx;
                    break;
                }
            }

        }

        return new int[]{startidx, endidx};

    }

    /**
     * O(logn) binarysearch
     * @param arr
     * @param tar
     * @return
     */
    public int[] findIdxBinary(int[] arr, int tar) {
        int startidx = findLeftIdxBinary(arr, 0, arr.length - 1, tar);
        int endidx = findRightIdxBinary(arr, 0, arr.length - 1, tar);

        return new int[]{startidx, endidx};

    }

    private int findLeftIdxBinary(int[] arr, int left, int right, int key) {

        int midx = (left + right) / 2;

        if (left == right) {
            if (arr[left] == key) {
                return left;
            } else {
                return -1;
            }
        } else {
            if (arr[midx] == key && arr[midx - 1] == key) {
                return findLeftIdxBinary(arr, left, midx - 1, key);
            } else if (key < arr[midx]) {
                return findLeftIdxBinary(arr, left, midx - 1, key);
            } else if (key > arr[midx]) {
                return findLeftIdxBinary(arr, midx + 1, right, key);
            } else if (arr[midx] == key) {
                return midx;
            }
        }


        return -1;

    }

    private int findRightIdxBinary(int[] arr, int left, int right, int key) {

        int midx = (left + right) / 2;

        if (left == right) {
            if (arr[left] == key) {
                return left;
            } else {
                return -1;
            }
        } else {
            if (arr[midx] == key && arr[midx + 1] == key) {
                return findRightIdxBinary(arr, midx + 1, right, key);
            } else if (key < arr[midx]) {
                return findRightIdxBinary(arr, left, midx - 1, key);
            } else if (key > arr[midx]) {
                return findRightIdxBinary(arr, midx + 1, right, key);
            } else if (arr[midx] == key) {
                return midx;
            }
        }


        return -1;

    }


}
