package problem;

import lib.model.ALinkedList;
import lib.model.ProblemBase;

public class RecusionGeneralProblem extends ProblemBase {

    public static void main(String[] args) {

        RecusionGeneralProblem recusionGeneralProblem = new RecusionGeneralProblem();
//        String result = recusionGeneralProblem.reverseString(input);
//        boolean result = recusionGeneralProblem.isPalindrom(input.toLowerCase());
       // String result = recusionGeneralProblem.convertToBinaryString(233);
        //double result = recusionGeneralProblem.convertToNumber("111");
        //long result = recusionGeneralProblem.sumOfNaturalNumbers(10);
        //int[] result = {3,12,234,356,1123,23456};
        int key = 123;
        //recusionGeneralProblem.mergeSort(result);
//        ALinkedList<Integer> linkedList = new ALinkedList<>();
//        linkedList.append(1);
//        linkedList.append(2);
//        linkedList.append(3);
//        linkedList.append(4);
//        linkedList.append(5);
//        linkedList.reverse();
//        int[][] data = lib.util.SampleData.Convertors.convertEdgelistToAdjMtx(lib.util.SampleData.Csacademy.CSA001,false);
//        boolean result = recusionGeneralProblem.dfsTraversal(data,12);
//        recusionGeneralProblem.LOGGER.info(result);
//        recusionGeneralProblem.LOGGER.info(recusionGeneralProblem.counter);

        int res = recusionGeneralProblem.numberOfDigitsInGivenBigNumber(1255355522,2,0);

        System.out.println(res);
    }


    public int numberOfDigitsInGivenBigNumber(long number, int digitToCount, int count){
       if(number<9){
           if(number==digitToCount){
               return 1;
           }else
               return 0;
       }

       if(number%10 == digitToCount){
           return 1+numberOfDigitsInGivenBigNumber(number/10,digitToCount,count);
       }else
           return 0+ numberOfDigitsInGivenBigNumber(number/10,digitToCount,count);

    }

    public String reverseString(String input){
        if(input == ""){
            return "";
        }

        String news = input.substring(1);
        String last = String.valueOf(input.charAt(0));

        return reverseString(news)+last;
    }

    public boolean isPalindrom(String input){
        if(input.length() == 0|input.length() == 1){
            return true;
        }

        if(input.charAt(0) == input.charAt(input.length()-1)){
            return isPalindrom(input.substring(1,input.length()-1));
        }

        return false;
    }

    public String convertToBinaryString(int value){
        if(value ==0)return "";
        int res = value/2;
        int rem = value % 2;

        return  convertToBinaryString(res) + rem;
    }

    public double convertToNumber(String binary){
        if(binary == "")return 0.0;
        double charVal = Double.parseDouble(String.valueOf(binary.charAt(0)));
        int powerof2 = binary.length()-1;
        double pow = Math.pow(2,powerof2);
        double val = pow*charVal;
        return convertToNumber(binary.substring(1)) + val;
    }

    public long sumOfNaturalNumbers(int value){
        counter++;
        if(value == 0)return 0;
        return sumOfNaturalNumbers(value-1) + value;
    }

    public int binarySearch(int[] numbers , int value){

       return binarySearchHelper(numbers,0,numbers.length-1,value);
    }

    private int binarySearchHelper(int[] numbers, int left, int right, int key){
        counter++;

        int mid = (left+right)/2;

        if(left>right)
            return -1;

        if(numbers[mid] == key)
            return key;

        if(key<numbers[mid]){
            return binarySearchHelper(numbers,left,mid-1,key);
        }else {
            return binarySearchHelper(numbers,mid+1,right,key);
        }
    }

    public void mergeSort(int[] numbers){
        if(numbers.length<2)return;

        int mid = numbers.length/2;
        int [] left = new int[mid];
        int [] right = new int[numbers.length-mid];

        for(int i = 0; i < left.length; i++){
            left[i] = numbers[i];
        }
        for(int i = 0; i < right.length; i++){
            right[i] = numbers[mid+i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(numbers,left,right);
    }

    private void merge(int[] arr, int[] left, int[] right){

        int lx=0,rx=0,kx = 0;

        while (lx<left.length && rx<right.length ){

            if(left[lx]<right[rx]){
                arr[kx] = left[lx];
                lx++;

            }else {
                arr[kx] = right[rx];
                rx++;

            }

            kx++;
        }

        while (lx<left.length){
            arr[kx] = left[lx];
            lx++;
            kx++;

        }

        while (rx<right.length){
            arr[kx] = right[rx];
            rx++;
            kx++;

        }

    }

    public <T> void reverseLinkedList(ALinkedList<T> linkedList){

      linkedList.reverse();
    }

    // adj mtx
    public boolean dfsTraversal(int[][] graph, int key){

        boolean[] visited = new boolean[graph.length];
        int currentNode = 0;
        visited[0] = true;
        return isFoundDfs(graph,currentNode,key,visited);

    }

    private boolean isFoundDfs(int[][] graph, int node, int key, boolean[] visited){
        if(node == key) return true;

        for(int i = 0; i < graph[node].length; i++){
            if(graph[node][i] == 0)continue;
            if(!visited[i]){
                visited[i] = true;
                return isFoundDfs(graph,i,key,visited);
            }
        }
        return false;
    }

}
