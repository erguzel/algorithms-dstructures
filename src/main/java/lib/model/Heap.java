package lib.model;
import java.util.Arrays;

public class Heap{


    private int [] data = null;
    private int heapsize = 0;

    public int[] getData() {
        return data;
    }

    public Heap(int root){
        this.data = new int [1];
        this.data[0] = root;
        this.heapsize++;
    }//ctor

    public void insert (int value){
        this.data = Arrays.copyOf(this.data,this.data.length+1);
// insert to last leaf
        this.data[this.data.length-1] = value;
        this.shiftUp();
        this.heapsize++;
    }//insert

    public int poll(){
//remove from top
//replace with the last
//shift down the new top

        if(this.heapsize==0)
            return -1;
        int polled = this.data[0];
// add beyond the heapsize
        this.data[0] = this.data[this.heapsize-1];
        this.data[this.heapsize-1] = polled;
        this.heapsize--;
        this.shiftDown();
        return polled;


    }//poll
    private void shiftUp(){
        int currentindex = this.data.length-1;
        while(currentindex !=-1){
            int parentindex = currentindex == 0 ? -1:(currentindex-1)/2 < this.heapsize?(currentindex-1)/2:-1;
            boolean parentgreater = parentindex !=-1 && this.data[parentindex] > this.data[currentindex];
// for a minheap heap
            if(parentgreater){
                int temp = this.data[currentindex];
                this.data[currentindex] = this.data[parentindex];
                this.data[parentindex] = temp;
            }//if parent great
            currentindex = parentindex;
        }//while true
    }//shift up

    private void shiftDown(){
// compare with left and right kid
// shift down as long as any greater value in below
        int currentidx = 0;
        while(currentidx!=-1){

            int leftidx = 2*currentidx + 1 < this.heapsize? 2*currentidx+1:-1;
            int rightidx = 2*currentidx + 2 < this.heapsize? 2*currentidx+2:-1;
            boolean shouldSwap = false;
            String compareWith = "";

            compareWith = (leftidx != -1 && rightidx != -1) && (this.data[leftidx] < this.data[rightidx])?"left":
                    leftidx != -1 && rightidx == -1?"left":
                            leftidx == -1 && rightidx == -1?"none":"right";


            shouldSwap = compareWith == "left"? (this.data[currentidx]> this.data[leftidx]):
                    compareWith == "right"? (this.data[currentidx]>this.data[rightidx]):
                            false;


            if(shouldSwap){

                int temp = this.data[currentidx];
                this.data[currentidx] = compareWith == "left"? this.data[leftidx]:
                        compareWith == "right" ? this.data[rightidx]:this.data[currentidx];

                if(compareWith=="right"){
                    this.data[rightidx] = temp;
                    currentidx = rightidx;
                }else if(compareWith == "left"){
                    this.data[leftidx] = temp;
                    currentidx = leftidx;
                }else{
                    currentidx = -1;
                }
            }else
                currentidx = -1;//should swap

        }//while true
    }// shift down
}//class
