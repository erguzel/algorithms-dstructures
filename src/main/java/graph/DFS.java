import lib.util.ALogger;
import lib.util.SampleData;

import java.util.*;

/**
 * O(E+V)
 */
public class DFS {

    ALogger<DFS> LOGGER = new ALogger<>(DFS.class);

    private int count=0;
    public static void main(String[] args) {

        int[][] graph = SampleData.Csacademy.NONDIRECTED_CYCLED_EDGELIST_3;
        int[][] edgesDirected = {

                {1, 2, 3},
                {2, 3, 2},
                {3, 4, 1},
                {4, 5, 4},
                {5, 0, 2},
                {1, 4, 1},
                {6, 2, 1},
                {0, 6, 1},
                //   {4, 2, 1},
        };
        DFS dfs = new DFS();
      //  dfs.traverseGraphEdgeListRecursive(graph, 6, 4 ,true);
        // int[] res = dfs.findPathIfExistsEdgeListRecursive(graph, 6, 4 ,0,true);
       // dfs.LOGGER.info(Arrays.stream(res).boxed().collect(Collectors.toList()));
        //dfs.hasCycleDirectedEdgeListRecursive(edgesDirected,7,true);
        System.out.println("cnt:"+dfs.count);
        //System.out.println("res:"+res);
    }

    public int[] findPathIfExistsEdgeList(int[][] graph, int nofvertices, int source, int dest) {
        boolean[] visited = new boolean[nofvertices];
        Stack<Integer> callstack = new Stack<>();
        callstack.add(source);
        Object[] previous = new Object[nofvertices];


        while (!callstack.isEmpty()) {
            count++;
            int current = callstack.pop();
            visited[current] = true;
            for (int i = 0; i < graph.length; i++) {
                if (graph[i][0] != current) continue;
                int nbid = graph[i][1];
                if (!visited[nbid]) {
                    previous[nbid] = current;
                    callstack.add(nbid);
                    visited[nbid] = true;
                    if(nbid==source){
                        break;
                    }
                }

            }
        }

        List<Object> path = new ArrayList<>();
        Object src = source;
        Object des = dest;
        while (des != null) {
            path.add(previous[(int)des]);
            des = previous[(int) des];
        }
        Collections.reverse(path);

        return path.stream().filter(a->a!=null).mapToInt(a->Integer.parseInt(a.toString())).toArray();


    }
    public int[] findPathIfExistsEdgeListRecursive(int[][] graph,int nofvertices,int source, int dest, boolean isVerbose){
        boolean[] visited = new boolean[nofvertices];
        Object[] prev = new Object[nofvertices];
        if(isVerbose)LOGGER.info("recursion starts: source="+source);
        findPathIfExistsEdgeListRecursiveUtil(source,graph,visited,prev,dest,isVerbose);

        if(prev.length==0)return new int[0];

        List path = new ArrayList();
        int dess = dest;
        while (prev[dess] != null){
            path.add(prev[dess]);
            dess = (int)prev[dess];
        }
        Collections.reverse(path);
        return path.stream().filter(a->a!=null).mapToInt(a->Integer.parseInt(a.toString())).toArray();

    }
    private void findPathIfExistsEdgeListRecursiveUtil(int vertex, int[][] graph, boolean[]visited,Object[]prev, int destination, boolean isVerbose){
        count++;
        if(isVerbose)LOGGER.info(String.format("recursion %d : ",count));
        if(isVerbose)LOGGER.info(String.format("vertex %d : ",vertex));
        visited[vertex]=true;
        for(int i = 0; i<graph.length;i++){
            if(graph[i][0]!=vertex)continue;
            int nbid = graph[i][1];
            if(!visited[nbid]){
                prev[nbid]= vertex;
                visited[nbid] = true;
                if(destination == nbid){
                    return;
                }else
                    findPathIfExistsEdgeListRecursiveUtil(nbid,graph,visited,prev,destination,isVerbose);

            }
        }
    }

    public void traverseGraphEdgeList(int[][] graph, int nofvertices, int source, boolean isVerbose){
        boolean[] visited = new boolean[nofvertices];
        Stack<Integer> callstack = new Stack<>();
        callstack.add(source);
        if(isVerbose)LOGGER.info("traverse starts: source="+source);
        while (!callstack.isEmpty()){
            count++;
            int current = callstack.pop();
            System.out.println(current);
            visited[current] = true;
            for(int i = 0; i < graph.length;i++){
                if(graph[i][0]!=current)continue;
                int nbid = graph[i][1];
                if(!visited[nbid]){
                    if(isVerbose)LOGGER.info(String.format("adding neighbour of %d -> %d",current,nbid));
                    callstack.add(nbid);
                    visited[nbid] = true;
                }
            }

        }

    }
    public void traverseGraphEdgeListRecursive(int[][] graph, int nofvertices,int source, boolean isVerbose){
        boolean[] visited = new boolean[nofvertices];
        if(isVerbose)LOGGER.info("traverse starts: source="+source);
        traverseGraphEdgeListRecursiveUtil(source,graph,visited,isVerbose);
    }
    private void traverseGraphEdgeListRecursiveUtil(int vertex, int[][] graph,boolean[]visited,boolean isVerbose){
        count++;
        System.out.println(vertex);
        if(isVerbose)LOGGER.info(String.format("recursion %d : ",count));
        if(isVerbose)LOGGER.info(String.format("vertex %d : ",vertex));
        visited[vertex] = true;
        for(int i = 0;i<graph.length;i++){
            if(graph[i][0]!= vertex)continue;
            int nbid = graph[i][1];
            if(!visited[nbid]){
                visited[nbid] =true;
                traverseGraphEdgeListRecursiveUtil(nbid,graph,visited,isVerbose);
            }

        }
    }

}
