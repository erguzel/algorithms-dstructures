import lib.util.ALogger;
import lib.util.SampleData;

import java.util.*;
import java.util.stream.*;

public class TopologicalSort {


    public void test(int[][] graph, int nofvertices) {
        int[] indegree = new int[nofvertices ];
        boolean[] visited = new boolean[nofvertices];
        Arrays.setAll(indegree, (a) -> 0);
        Queue<Integer> result = new LinkedList<>();


        IntStream.range(0, graph.length)
                .forEach(a -> {
                    indegree[graph[a][1]]++;
                });

        Queue<Integer> callstack = new LinkedList<>();

        IntStream.range(0, nofvertices)
                .forEach(a -> {
                    if (indegree[a] == 0) {
                        callstack.add(a);
                    }
                });

        while (!callstack.isEmpty()) {
            int current = callstack.poll();
            if(visited[current])continue;
            visited[current] = true;
            result.add(current) ;
            IntStream.range(0, graph.length)
                    .forEach(m -> {
                        if (graph[m][0] == current) {
                            int nbid = graph[m][1];
                            if(!visited[nbid]){
                                if (indegree[nbid]> 0) {
                                    indegree[nbid]--;
                                }
                                if(indegree[nbid]==0){
                                    callstack.add(nbid);
                                }
                            }
                        }
                    });

        }

        if(result.size()==nofvertices){
            System.out.println(result);
        }else {
            System.out.println("Cycle probable");
        }
    }


    public static ALogger<TopologicalSort> LOGGER = new ALogger<>(TopologicalSort.class);
    private int count = 0;

    public static void main(String[] args) {
        int[][] graph = SampleData.Csacademy.COURSE_SCHEDULE_3;
        TopologicalSort ts = new TopologicalSort();
        ts.test(graph, 12);
//        int[] res = ts.findTopSortIfExistsKahnEdgeList(graph,12);
//        LOGGER.info(Arrays.stream(res).boxed().collect(Collectors.toList()));
//        LOGGER.info(ts.count);

    }

    //
    // Edge list
    //
    public int[] findTopSortIfExistsKahnEdgeList(int[][] edges, int nofvertices) {
        int[] indegree = new int[nofvertices];
        Stack<Integer> callstack = new Stack<Integer>();
        Queue<Integer> path = new LinkedList<>();

        boolean[] visited = new boolean[nofvertices];
        for (int i = 0; i < edges.length; i++) {
            indegree[edges[i][1]]++;
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) callstack.add(i);
        }

        while (!callstack.isEmpty()) {
            count++;
            int current = callstack.pop();
            if (visited[current]) continue;
            visited[current] = true;
            path.add(current);
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][0] != current) continue;
                int nbid = edges[i][1];
                if (!visited[nbid]) {
                    if (indegree[nbid] > 0) indegree[nbid]--;
                    if (indegree[nbid] == 0) callstack.add(nbid);
                }
            }
        }

        return path.size() == nofvertices ? path.stream().mapToInt(a -> a).toArray() : new int[]{-1};
    }

}
