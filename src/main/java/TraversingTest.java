import lib.model.ProblemBase;

import java.util.*;

public class TraversingTest extends ProblemBase {


    public static void main(String[] args) {

        int nofvertices = 7;
        int[][] edgesDirected = {

                {1, 2, 3},
                {2, 3, 2},
                {3, 4, 1},
                {4, 5, 4},
                {5, 0, 2},
                {1, 4, 1},
                {6, 2, 1},
                {4, 1, 1},
             //   {0, 6, 1},
             //   {4, 2, 1},
        };


        {
            boolean[] visited = new boolean[nofvertices];
            int[] status = new int[nofvertices]; //0 nonvisited 1 visited 2 completed

            Stack<Integer> callstack = new Stack<>();
            for (int i = 0; i < nofvertices; i++) {
                callstack.add(i);
            }

            while (!callstack.isEmpty()) {
                int current = callstack.pop();
                for (int i = 0; i < edgesDirected.length; i++) {
                    boolean rs = cycleUtil(current, edgesDirected, status);
                    //System.out.println(current);
                    System.out.println(rs);
                    return;
                }
            }
        }

    }


    public static boolean cycleUtil(int vertex, int [][] graph,int[] status){
        status[vertex] = 1;
        for(int k = 0; k < graph.length;k++){
            if(graph[k][0]!=vertex) continue;
            int nbid = graph[k][1];
            if(status[nbid] == 2)continue;
            if(status[nbid]==1)return true;
            if(cycleUtil(nbid,graph,status)){
                System.out.println(nbid);
                return true;
            }
        }
        status[vertex] =2;
        return false;
    }


}
