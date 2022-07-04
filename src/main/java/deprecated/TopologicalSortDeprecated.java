package deprecated;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class TopologicalSortDeprecated {


    private int count;

    public static void main(String[] args) {
//        int [][] graph = lib.util.SampleData.Csacademy.COURSE_SCHEDULE_3;

//        TopologicalSortOld topologicalSort = new TopologicalSortOld();
//        int[] res = topologicalSort.findTopSortKahnEdgeList(graph,12);
//        LOGGER.info(Arrays.stream(res).boxed().collect(Collectors.toList()));
//        LOGGER.info(topologicalSort.count);

        TopologicalSortDeprecated topologicalSort1 = new TopologicalSortDeprecated();
      //  int[] res1 = topologicalSort1.topSortRecursiveEdgeList(graph,12);
       // System.out.println(Arrays.stream(res1).boxed().collect(Collectors.toList()));



    }

    //
    //  EdgeList
    //
    public int[] findTopSortKahnEdgeList(int[][] graph, int numberofvertxes){
        int[] indegree = new int[numberofvertxes];
        boolean[] visited = new boolean[numberofvertxes];
        Stack<Integer> callstack = new Stack<>();
        Queue<Integer> path= new LinkedList<>();
        for(int i = 0;i<graph.length;i++){
            int to = graph[i][1];
            indegree[to]++;
        }
        // start from outer vertexes
        for(int i = 0;i < numberofvertxes; i++){
            if(indegree[i]==0){
                callstack.add(i);
            }
        }

        while (!callstack.isEmpty()){
            int current= callstack.pop();
            path.add(current);
            if(visited[current])continue;
            visited[current] = true;
            // nbours
            for(int i = 0; i < graph.length; i++){
                if(graph[i][0]!=current)continue;
                int to = graph[i][1];
                if(!visited[to]){
                    if(indegree[to]>0){
                        indegree[to]--;
                    }
                    if(indegree[to]==0){
                        callstack.add(to);
                    }
                }
            }
        }

        return path.stream().mapToInt(a->a).toArray();

    }

    public int[] findTopSortRecursiveEdgeList(int[][] graph,int numberofvertxes){

        boolean visited[] = new boolean[numberofvertxes];
        Queue<Integer> path = new LinkedList<>();
        for(int i = 0; i < graph.length;i++){
            int current = graph[i][0];//[a,b] a->b
            if(!visited[current]){
                findTopSortRecursiveEdgeListUtil(graph,current,visited,path);
            }
        }



        return path.stream().mapToInt(a->a).toArray();
    }
    public void findTopSortRecursiveEdgeListUtil(int[][] graph, int current,boolean[]visited, Queue<Integer> path){
       count++;
        visited[current] = true;
        // explore nbours
        for(int i = 0; i < graph.length; i++){
            if(graph[i][0]==current){
                int to = graph[i][1];
                if(!visited[to]){
                    findTopSortRecursiveEdgeListUtil(graph,to,visited,path);
                }
            }
        }

        path.add(current);
    }

    public int [] topSortRecursiveEdgeList(int[][] edges, int nofvertices){
        boolean [] visited = new boolean[nofvertices];
        Stack<Integer> callstack = new Stack<Integer>();
        Queue<Integer> path = new LinkedList<>();

        for (int i = 0; i < nofvertices; i++){
            if(!visited[i]){
                topSortRecursiveEdgeListUtil(i,edges,visited,callstack,path);
            }
        }

        return path.stream().mapToInt(a->a).toArray();
    }
    public void topSortRecursiveEdgeListUtil(int current, int[][] edges, boolean[] visited,Stack<Integer> callstack, Queue<Integer> path){
        count++;
        visited[current] = true;
        for(int i = 0; i < edges.length; i++){
            if(edges[i][0] != current)continue;
            int nb = edges[i][1];
            if(!visited[nb]){
                topSortRecursiveEdgeListUtil(nb,edges,visited,callstack,path);
            }
        }
        path.add(current);

    }

    //
    // Adj Matrix
    //
    public int[] findTopSortRecursiveAdjMtx(int[][] graph) {

        boolean[] visited = new boolean[graph.length];// track if visited
        Queue<Integer> path = new LinkedList<>();// for path
// for each vertex, we call dfs if necessary
        for (int i = 0; i < graph.length; i++) {
            int current = i;
            if (!visited[current]) {
                findTopSortRecursiveAdjMtxUtil(graph, current, visited, path);
            }//call dfs
        }//for each vertex

        Integer[] as = path.toArray(new Integer[0]);
      //  sort.ReverseArray.reverse(as);

        return Arrays.stream(as).mapToInt(a -> a).toArray();
    }// find topsort recursive
    private void findTopSortRecursiveAdjMtxUtil(int[][] graph, int current, boolean[] visited, Queue<Integer> path) {
        visited[current] = true;
// explore nbours
        for (int i = 0; i < graph[current].length; i++) {
            if (graph[current][i] == 0) continue;
            int currentnbour = i;
            if (!visited[i]) {
                findTopSortRecursiveAdjMtxUtil(graph, currentnbour, visited, path);
            }//call deeper stack
        }//for nbours

//add call back value to path
        path.add(current);
    }// topsort recursive util
    public int[] findTopSortKahnAdjMtx(int[][] graph) {

        Stack<Integer> callstack = new Stack<Integer>();//for next dfs call
        Queue<Integer> path = new LinkedList<>();// for path
        boolean[] visited = new boolean[graph.length];//for visited track
        int[] indegree = new int[graph.length]; // for tracking unconnected degree of vertexes

// fill cache with number of connections whicht towards them

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[j][i] != 0) {
                    indegree[i]++;
                }// means there is a connection to that vertex
            }//foreach neightbour
        }//f0r each vertex

// add vertexes which has no dependen cies to call stack

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) callstack.add(i);
        }// fill stack

// traverse from nondependent vertexes

        while (!callstack.isEmpty()) {
            int current = callstack.pop();//add to path queue
            path.add(current);
            if (visited[current]) continue;
            visited[current] = true;

// explore neighbours (not a visit !)
            for (int i = 0; i < graph[current].length; i++) {
                if (graph[current][i] == 0) continue;
                if (!visited[i]) {
                    if (indegree[i] > 0) {
                        indegree[i]--;//reduce 1 for priority
                    }
                    if (indegree[i] == 0) {
                        callstack.add(i);
                    }//add callstack if reaches priority
                }//if not visited
            }//for nbours
        }//traverse

        return path.stream().mapToInt(a -> a).toArray();
    }//find topsortnonrecursinve
}
