package graph;

import java.util.*;


/**
 *    // do dfs from every vertex
 *    // catch a vertex which is already on stack while it is being visited;
 */
public class CycleDetectionDirected {


    //TODO: recursive impl

    public boolean hasCycle(int[][] edgelist, int numberofvertex){

        int edgeListLength = edgelist.length;
        boolean[] visited = new boolean[numberofvertex];
        boolean[] onstack = new boolean[numberofvertex];
        Stack<Integer> callstack = new Stack<>();

        for(int point = 0; point<numberofvertex;point++){
            if(visited[point])continue;
            callstack.add(point);
            while (!callstack.isEmpty()){
                int currentVertex = callstack.peek();
                if(!visited[currentVertex]){
                    visited[currentVertex] = true;
                    onstack[currentVertex] = true;
                }else{
                    onstack[currentVertex] = false;
                    // poll extra
                    callstack.pop();
                    continue;
                }
                // nbour navigate
                for(int nbEdgeId = 0; nbEdgeId<edgeListLength; nbEdgeId++){
                    if(edgelist[nbEdgeId][0]!=currentVertex)continue;
                    int nbid = edgelist[nbEdgeId][1];// check single vertexes TODO
                    if(!visited[nbid])callstack.push(nbid);
                    else if(onstack[nbid])return true;
                }
            }

        }
        return false;

    }

}
