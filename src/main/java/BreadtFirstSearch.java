import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BreadtFirstSearch {



    ///< my global fields



    ///>

    public void execute(int[][] points, int startPoint){

        // initial validation TODO

        // visited point track
        boolean[] visited = new boolean[points.length];
        // hold candidates
        Queue<Integer> candidatePoints = new LinkedList<>();
        candidatePoints.add(startPoint);
        visited[startPoint] = true ;// mark start point visited

        int currentPoint = 0;
        int[] adjacentPoints = null;
        int nextAdjacent = 0;

        while (!candidatePoints.isEmpty()){

            currentPoint = candidatePoints.poll();
            System.out.println("Traversing>"+currentPoint);

            adjacentPoints = points[currentPoint];

            for(int i = 0; i < adjacentPoints.length; i++){

                nextAdjacent = adjacentPoints[i];
                boolean nextAdjacentVisisted = visited[nextAdjacent];
                if(!nextAdjacentVisisted){

                    visited[nextAdjacent] = true;

                    candidatePoints.add(nextAdjacent);

                }

            }
        }



    }



    public static void main(String[] args) {

        int [][] input = {
                {1},
                {0,3},
                {3,4,5},
                {1,2,4},
                {2,3},
                {2}
        };


        BreadtFirstSearch breadtFirstSearch = new BreadtFirstSearch();
        breadtFirstSearch.execute(input,2);

    }
}
