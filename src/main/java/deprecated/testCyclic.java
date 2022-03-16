package deprecated;

import java.util.*;

public class testCyclic{



    public boolean isCyclicDirectedNonRecursiveEdgeList(int[][] edges, int numberofvertices){
// do a simple dfs
// if you encounter an already visited node via nbours of nbours, return true
        boolean[] visited = new boolean[numberofvertices];
        boolean[] onstack = new boolean[numberofvertices];
        Stack<Integer> callstack = new Stack<>();

        for(int i =0; i < numberofvertices;i++){
            if(visited[i]) continue;
            callstack.add(i);

            while (!callstack.isEmpty()){
                int current = callstack.peek();
                if(!visited[current]){
                    visited[current] = true;
                    onstack[current] = true;
                }else {
                    onstack[current] = false;
                    callstack.pop();
                }

                for(int k = 0; k < edges.length;k++){
                    if(edges[k][0]!=current)continue;
                    int nbid = edges[k][1];
                     if(!visited[nbid]){
                         callstack.push(nbid);
                     }else if(onstack[nbid]) return  true;
                }
            }

        }

        return false;

    }//isCyclicNonDirectedNonRecursiveEdgeList

    public boolean isCyclicDirectedRecursiveEdgeList(int[][] edges, int numberofvertices){
// if while a vertex is inprogress, if the same vertex is encountered as in progress through an edge of other vertex,
// that means we found the cycle
        int[] visited = new int[numberofvertices];//0->nonvisited, 1->inprogress, 2->completed
        Stack<Integer> callstack = new Stack<Integer>();// call stack for nonvisited vertexes
// fill all candidates
        for(int i =0; i < numberofvertices; i++){
            callstack.add(i);
        }//for fill all candidates
// traverse candidates
        while(!callstack.isEmpty()){
            int current = callstack.pop();
            if(visited[current]==2)continue;
            if(isCyclicDirectedRecursiveEdgeListUtil(current,edges,visited,callstack)){
                return true;
            }//isCyclicDirectedRecursiveEdgeListUtil : a function that traverses nbours recursively
        }// while traverse candidates
        return false;
    }//isCyclicDirectedRecursiveEdgeList

    public boolean isCyclicDirectedRecursiveEdgeListUtil(int vertex,int[][]edges,int[]visited,Stack<Integer> callstack){
        visited[vertex] = 1;
// for nbours
        for(int i = 0; i < edges.length;i++){
            if(edges[i][0] != vertex)continue; // not our vertex
            int nbid = edges[i][1];//one of our nbour
// check visited states
            if(visited[nbid]==0)callstack.add(nbid);//a new candidate
            if(visited[nbid] == 1)return true;// we found the cycle
            if(isCyclicDirectedRecursiveEdgeListUtil(nbid,edges,visited,callstack)){
                return true;
            }//if  call for next nb if nb
        }//for nbours
        visited[vertex ] = 2;
        return false;
    }//



}// class
