import java.util.PriorityQueue;

public class LongestPathInMatrix {

    public static class MtxElement{
        public int i = -1;
        public int j = -1;
    }
    public static void main(String[] args) {

        // input matrix
        char[][] mat =
                {
                        { 'D', 'E', 'H', 'X', 'B' },
                        { 'A', 'O', 'G', 'P', 'E' },
                        { 'D', 'D', 'C', 'F', 'D' },
                        { 'E', 'B', 'E', 'A', 'S' },
                        { 'C', 'D', 'Y', 'E', 'N' }
                };

        LongestPathInMatrix ll = new LongestPathInMatrix();
        ll.longestPath(mat);


    }

    public void longestPath(char[][] graph){

        PriorityQueue<Character> asd = new PriorityQueue<>(((a,b)->a>b?-1:1));
        asd.add('A');
        asd.add('c');
        asd.add('L');
        asd.add('x');

        char a = asd.poll();
        //a = (char) (a + 1);
        System.out.println(a);

        for(int i = 0; i < graph.length; i++){
            for (int j = 0; j < graph[i].length;j++ ){

            }
        }

    }
}
