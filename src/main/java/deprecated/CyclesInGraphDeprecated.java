package deprecated;

import java.util.*;

/**
 * Find if there is a cycle in an undirected graph
 */
public class CyclesInGraphDeprecated {


    private int count;
    public static void main(String[] args) {

//        int[][] graph = lib.util.SampleData.Csacademy.COURSE_SCHEDULE_3;
//        CyclesInGraphDeprecated cyclesInGraph = new CyclesInGraphDeprecated();
//        boolean res = cyclesInGraph.isCyclicDirectedRecursiveEdgeList(graph, 12);
//        System.out.println(res);



    }

    //
    // Edge List
    //
    public boolean isCyclicDirectedEdgeList(int[][] edges, int numberofvertices){
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
    // Edge List Recursive
    //
    public int[] getCycleInDirectedGraphRecursiveEdgeList(int[][] graph, int numberofvertex){
        int[] visited = new int[numberofvertex];
        Queue<Integer> path = new LinkedList<>();
        Stack<Integer> callstack = new Stack<>();

        for(int i =0;i<visited.length;i++){
            callstack.add(i);
        }

        while (!callstack.isEmpty()){
            int current = callstack.pop();
            if(visited[current]==2)continue;
            if(getCycleInDirectedGraphRecursiveEdgeListUtil(current,graph,visited,callstack,path)){
               // path.add(current);
                return path.stream().mapToInt(a->a).toArray();

            }
        }

        return path.stream().mapToInt(a->a).toArray();


    }
    public boolean getCycleInDirectedGraphRecursiveEdgeListUtil(int vertex, int[][] graph, int[] visited, Stack<Integer> callstack, Queue<Integer> path){
        visited[vertex] = 1;//inprogress
        for(int h = 0; h<graph.length; h++){
            if(graph[h][0]!=vertex)continue;
            int to = graph[h][1];
            if(visited[to]==0){
                callstack.add(to);
            }
            if(visited[to]==1){
                return true;
            }

            if(getCycleInDirectedGraphRecursiveEdgeListUtil(to,graph,visited,callstack,path)){
                path.add(to);
                return true;
            }
        }

        visited[vertex] = 2;
        return false;
    }

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

//    public boolean isCycleInDirectedGraphRecursiveEdgeList(int[][] graph, int numberOfVertex){
//        int visited[] = new int[numberOfVertex];
//        Stack<Integer> callStack = new Stack<>();
//        for(int i = 0; i<visited.length;i++){
//            callStack.add(i);
//        }
//
//        while (!callStack.isEmpty()) {
//            int current = callStack.pop();
//            if (visited[current] == 2) continue;
//            if (isCycleInDirectedGraphRecursiveEdgeListUtil(graph,current,visited,callStack)) {
//                return true;
//
//            }
//        }
//
//        return false;
//    }
//    public boolean isCycleInDirectedGraphRecursiveEdgeListUtil(int[][] graph,int current,int[]visited,Stack<Integer> callstack){
//        visited[current]=1;
//        for(int i =0;i<graph.length;i++){
//            if(graph[i][0]!=current)continue;
//            int to = graph[i][1];
//            if(visited[to]==0)callstack.add(to);
//            if(visited[to]==1)return true;
//            if(isCycleInDirectedGraphRecursiveEdgeListUtil(graph,to,visited,callstack))return true;
//        }
//        visited[current]=2;
//        return false;
//    }
//
    //
    // Adjacency Matrix
    //
    public boolean isCyclicDirectedGraphRecursiveAdjMtx(int[][] graph) {
        int[] visited = new int[graph.length];//0 nonvisited 1 being explored 2 completed
        Stack<Integer> callStack = new Stack<>();// next candidate
        //fill all candidates
        for (int i = graph.length-1; i >=0 ; i--) {
            callStack.add(i);
        }

        while (!callStack.isEmpty()) {
            int current = callStack.pop();
            if(visited[current]==2)continue;
            if(isCyclicDirectedGraphRecursiveAdjMtxUtil(current,graph,visited,callStack)){
                return true;
            }
        }
        return false;
    }
    public boolean isCyclicDirectedGraphRecursiveAdjMtxUtil(int vertex, int [][] graph, int[] visited, Stack<Integer> callStack){
        count++;
        visited[vertex] = 1;

        for(int i = 0; i < graph[vertex].length;i++){
            if(graph[vertex][i]==0)continue;
            if(visited[i]==0){
                callStack.add(i);
            }
            if(visited[i] ==1){
                return true;
            }
            if(isCyclicDirectedGraphRecursiveAdjMtxUtil(i,graph,visited,callStack)){
                return true;
            }
        }
        visited[vertex] = 2;
        return false;
    }
    //
    // Adjacenty Matrix recursive
    //
    public int[] getCycleInDirectedGraphRecursiveAdjMtx(int[][] graph){
        int[] visited = new int[graph.length];//0 nonvisited 1 being explored 2 completed
        Stack<Integer> callStack = new Stack<>();// next candidate
        Queue<Integer> path = new LinkedList<>();
        //fill all candidates
        for (int i = graph.length-1; i >=0 ; i--) {
            callStack.add(i);
        }

        while (!callStack.isEmpty()) {
            int current = callStack.pop();
            if(visited[current]==2)continue;
            if(getCycleInDirectedGraphRecursiveAdjMtxUtil(current,graph,visited,callStack,path)){
                path.add(current);
                return path.stream().mapToInt(a->a).toArray();
            }
        }
        return path.stream().mapToInt(a->a).toArray();
    }
    public boolean getCycleInDirectedGraphRecursiveAdjMtxUtil(int vertex, int [][] graph, int[] visited, Stack<Integer> callStack, Queue<Integer> path){

        visited[vertex] = 1;

        for(int i = 0; i < graph[vertex].length;i++){
            if(graph[vertex][i]==0)continue;
            if(visited[i]==0){
                callStack.add(i);
            }
            if(visited[i] ==1){
                return true;
            }
            if(getCycleInDirectedGraphRecursiveAdjMtxUtil(i,graph,visited,callStack,path)){
                path.add(i);
                return true;
            }
        }
        visited[vertex] = 2;
        return false;
    }


    //
    //Undirected
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
    //
    // Undirected recursive
    //
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
