
import java.util.*;


/**
 *    // do dfs from every vertex
 *    // catch a vertex which is already on stack while it is being visited;
 */
public class CycleDetectionDirected {


    //TODO: recursive impl

    public boolean hasCycle(int[][] edgelist,int numberofvertex){

        int edgeListLength = edgelist.length;
        boolean[] visited = new boolean[numberofvertex];
        boolean[] onstack = new boolean[numberofvertex];
        Stack<Integer> callstack = new Stack<>();

        for(int point = 0; point < numberofvertex;point++){
            int vertex = point;
            if(visited[vertex])continue;
            callstack.push(vertex);
            while (!callstack.isEmpty()){
                int currentVtx = callstack.peek();
                if(!visited[currentVtx]){
                    visited[currentVtx] = true;
                    onstack[currentVtx]  = true;
                }else {
                    onstack[currentVtx] = false;
                    callstack.pop();
                    continue;
                }
                for(int nbedgeId = 0; nbedgeId<edgeListLength ;nbedgeId++){
                    if(edgelist[nbedgeId][0]!=currentVtx)continue;
                    int nbid = edgelist[nbedgeId][1];
                    if(!visited[nbid]){
                        callstack.push(nbid);
                    }else if (onstack[nbid]){
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
