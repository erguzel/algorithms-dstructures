package deprecated;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;


public class CyclesInGraphDeprecated2 {
    private int count = 0;
//    static lib.util.ALogger<CyclesInGraphDeprecated2> LOGGER = new lib.util.ALogger<>(CyclesInGraphDeprecated2.class);
    public static void main(String[] args) {
//        int [][] graph = lib.util.SampleData.Csacademy.NONDIRECTED_NONCYCLED_EDGELIST_2;
//        CyclesInGraph ts = new CyclesInGraph();
//        boolean res = ts.isCyclicUndirectedRecursiveEdgeList(graph,11);
//        LOGGER.info(res);
//        LOGGER.info(ts.count);
    }

    //
    // Directed
    //
    public boolean hasCycleDirectedEdgeList(int[][] edges, int nofvertices){
        int[] visited = new int [nofvertices];
        Stack<Integer> callstack = new Stack<Integer>();
        for(int i = 0; i < nofvertices;i++){
            callstack.add(i);
        }//for fill stack
        while(!callstack.isEmpty()){
            int current = callstack.pop();
            if(visited[current] ==2)continue;
            if(hasCycleDirectedEdgeListUtil(current,edges,visited,callstack)){
                return true;
            }//call recursive
        }//while candidates
        return false;
    }// hasCycle

    public boolean hasCycleDirectedEdgeListUtil(int vertex, int[][]edges, int[] visited, Stack<Integer> callstack){
        count++;
        visited[vertex]=1;//in progress
        for(int l = 0; l<edges.length;l++){
            if(edges[l][1] != vertex)continue;
            int nbid = edges[l][0];
            if(visited[nbid]==0) callstack.add(nbid);
            if(visited[nbid]==1)return true;
            if(hasCycleDirectedEdgeListUtil(nbid,edges,visited,callstack)){
                return true;
            }//if has cycle in deeper level
        }//all nbours in deeper level
        visited[vertex] = 2;
        return false;
    }//hasCycleUtil

    // nonrecursive
    public boolean isCyclicDirectedNonrecursiveEdgeList(int[][] edges, int numberofvertices){
// do a simple dfs
// if you encounter an already visited node via nbours of nbours, return true
        boolean[] visited = new boolean[numberofvertices];
        boolean[] onstack = new boolean[numberofvertices];
        Stack<Integer> callstack = new Stack<>();

        for(int i =0; i < numberofvertices;i++) {
            if (visited[i]) continue;
            callstack.add(i);

            while (!callstack.isEmpty()) {
                count++;
                int current = callstack.peek();
                if (!visited[current]) {
                    visited[current] = true;
                    onstack[current] = true;
                } else {
                    onstack[current] = false;
                    callstack.pop();
                }

                for (int k = 0; k < edges.length; k++) {
                    if (edges[k][0] != current) continue;
                    int nbid = edges[k][1];
                    if (!visited[nbid]) {
                        callstack.push(nbid);
                    } else if (onstack[nbid]) return true;
                }
            }

        }

        return false;

    }//isCyclicNonDirectedNonRecursiveEdgeList


    //
    // Undirected
    //
    // Disjoint sets
    public boolean isCyclicUndirectedGraphAdjMtx(int[][] graph){
        LinkedList<Set> sets = new LinkedList<>();
        for(int i =0;i<graph.length;i++){
            Set s = new HashSet();
            s.add(i);
            sets.add(i,s);

        }
        boolean [] visited = new boolean[graph.length];
        Stack<Integer> callstack = new Stack<>();
        callstack.add(0);
        while(!callstack.isEmpty()){
            int current = callstack.pop();
            if(visited[current])continue;
            visited[current] = true;
            for(int i =0; i< graph[current].length; i++){
                if(graph[current][i]==0)continue;
                int[] edge = new int[]{current,i};
                int setid1 = -1;
                int setid2 = -1;
                if(!visited[i]){
                    callstack.add(i);
                    for(int m = 0; m < sets.size();m++){
                        if(sets.get(m).contains(edge[0])){
                            setid1 = m;
                            break;
                        }
                    }
                    for(int m = 0; m < sets.size();m++){
                        if(sets.get(m).contains(edge[1])){
                            setid2 = m;
                            break;
                        }
                    }

                    if(setid1==-1 || setid2==-1) {

                        System.out.println("negative index, linked list error");
                        System.exit(-1);
                    }

                    if(setid1 != setid2){
                        Set set1= sets.get(setid1);
                        Set set2 = sets.get(setid2);
                        set1.addAll(set2);
                        sets.remove(set2);
                    }
                    else
                        return true;
                }
            }
        }




        return false;

    }
    // Recursive
    public boolean isCyclicUndirectedRecursiveEdgeList(int[][] edges, int numberofvertices){
        boolean[] visited = new boolean[numberofvertices];

//for each vertex
        for(int i= 0; i<numberofvertices;i++){
            if(!visited[i]){
                if(isCyclicUndirectedRecursiveEdgeListUtil(i,edges,visited,-1)){
                    return true;
                }// if neighbours
            }//if nonvisited
        }//foreach vertex
        return false;
    }//isCyclicUndirectedRecursiveEdgeList
    public boolean isCyclicUndirectedRecursiveEdgeListUtil(int vertex, int[][] edges,boolean[] visited, int parent){
        visited[vertex] = true;
        for(int i = 0;i<edges.length;i++){
            if(edges[i][0]!=vertex) continue;
            int nbid = edges[i][1];
            if(nbid != parent){
                if(visited[nbid]){
                    return true;
                }else{
                    if(isCyclicUndirectedRecursiveEdgeListUtil(nbid,edges,visited,vertex)){
                        return true;
                    }// if recursive
                }
            }
        }//for deeper nbour

        return false;
    }//isCyclicUndirectedRecursiveEdgeList


}
