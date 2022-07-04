package graph;

import lib.util.ALogger;

import java.util.*;
import java.util.stream.IntStream;

/**
 * O(V+E) time using Kosaraju’s
 */

public class StronglyConnectedComponents {

    static ALogger<StronglyConnectedComponents> LOGGER = new ALogger<>(StronglyConnectedComponents.class);

    public static void main(String[] args) {
        int [][] graph =  {
                {0, 1},
                {1, 2},
                {2, 0},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 4},
                {6, 3},
                {7, 1},
                {8, 4},
                {8, 7}

        };

        String dsd = "Ollggun";


        int[][] test1 =null;// DataUtil.Convertors._convertAdjListToAdjMtx(DataUtil.Generators.generateRandomGraph(1000,true));
        int[][] test = null;//DataUtil.Convertors._convertAdjMtxToEdgeList(test1);
        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        StronglyConnectedComponents stronglyConnectedComponents = new StronglyConnectedComponents();
        stronglyConnectedComponents.getComponents(graph,9);
        timer.getBenchmark(timer);

    }

    public List<Set> getComponents(int [][] graph, int nofvertices){
        // result
        List<Set> result = new ArrayList<Set>();
        //
        // find dfs order
        //reverse graph
        // do dfs wiht dfs order on reversed graph

        Stack<Integer> firstDfsOrder = new Stack<>();
        boolean[] visited = new boolean[nofvertices];

        //for each vertice do dfs
        IntStream.range(0,nofvertices).forEach(a->{
            int current = a;
            if(!visited[current]){
                dfsUtil(current,graph,visited,firstDfsOrder,null,false);
            }
        });

        //reverse graph
        // todo copy input data
        IntStream.range(0,graph.length).forEach(x->{
            int temp = graph[x][0];
            graph[x][0] = graph[x][1];
            graph[x][1] = temp;
        });

        // reset visited
        Arrays.fill(visited,false);

        // do reverse fds and add each vertex as components
        while (!firstDfsOrder.isEmpty()){
            int current = firstDfsOrder.pop();
            Set component = new HashSet();
            component.add(current);
            if(!visited[current]){
                dfsUtil(current,graph,visited,null,component,true);
                result.add(component);
            }


        }

        System.out.println(result);
        return result;
    }

    //if fill component true, ignores dfsOrder stack and fills the component set
    //if fill component false, ignores filling component and fills the dfsOrder stack
    public void dfsUtil(int vertex, int[][] graph, boolean[]visited, Stack<Integer>dfsOrder, Set component, boolean fillComponent){
        visited[vertex] = true;
        IntStream.range(0,graph.length).forEach(x->{
            if(graph[x][0]==vertex){
                int nb = graph[x][1];
                if(!visited[nb]){
                    if(fillComponent){
                        component.add(nb);
                    }
                    dfsUtil(nb,graph,visited,dfsOrder,component,fillComponent);
                }
            }
        });

        if(!fillComponent){
            dfsOrder.add(vertex);
        }
    }
}
