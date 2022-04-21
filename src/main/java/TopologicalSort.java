import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Used for determining the sort of the directed graph from the minimum dependent vertex to each other
 *
 * If sorted vertex count is different than the number of vertexes, that is an indication of graph has cyclic dependency among some
 * vertices, thus the graph is not topologically sortable
 */
public class TopologicalSort {

    //O(V + E)

    // define indegree value of vertexes (#of vertexes which are dependent to each vertex
    // add all indegree-0 vertexes to callqueue
    // traverse graph, visit all  nodes until the queue empty
    // reduce the indegree of a vertex if it is not zero,
    // add the vertex which has 0 in degree value to callqueue
    // check result length is the same as number of vertexes given


    public int[] sortTopologicallyIfExists_Khan(int[][] edges, int numberofvertex){
        boolean[] visited = new boolean[edges.length];
        ArrayDeque<Integer> callqueue = new ArrayDeque<>();
        int[]indegree = new int[numberofvertex];
        List<Integer> sortedPath = new ArrayList<>();
        // fill indegree

        IntStream.range(0,edges.length).forEach(x->{
            int to = edges[x][1];
            indegree[to]++;
        });

        // start from 0 indegrees
        IntStream.range(0,indegree.length).filter(id->indegree[id]==0).forEach(id->{
            callqueue.offer(id);
        });


        while (!callqueue.isEmpty()){
                final int vtx = callqueue.poll();
                if(visited[vtx]==true)continue;
                visited[vtx] = true;
                sortedPath.add(vtx);
                IntStream.range(0,edges.length).filter(x->edges[x][0]==vtx).forEach(nbedgepairs->{
                    int nbid = edges[nbedgepairs][1];
                    if(!visited[nbid]){
                        if(indegree[nbid] > 0){
                            indegree[nbid]--;

                        }
                        if(indegree[nbid] == 0){
                            callqueue.offer(nbid);
                        }
                    }
                });
            }

        if(sortedPath.size()!=numberofvertex) {
            System.out.println("Graph not topologically sortable");
            return null;
        }

        return sortedPath.stream().mapToInt(a->a).toArray();
    }

}
