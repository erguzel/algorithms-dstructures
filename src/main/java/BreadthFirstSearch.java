import java.util.*;

/**
 * Use Cases
 *1) Shortest Path and Minimum Spanning Tree for unweighted graph In an unweighted graph, the shortest path is the path with least number of edges. With Breadth First, we always reach a vertex from given source using the minimum number of edges. Also, in case of unweighted graphs, any spanning tree is Minimum Spanning Tree and we can use either Depth or Breadth first traversal for finding a spanning tree.
 *
 * 2) Peer to Peer Networks. In Peer to Peer Networks like BitTorrent, Breadth First Search is used to find all neighbor nodes.
 *
 * 3) Crawlers in Search Engines: Crawlers build index using Breadth First. The idea is to start from source page and follow all links from source and keep doing same. Depth First Traversal can also be used for crawlers, but the advantage with Breadth First Traversal is, depth or levels of the built tree can be limited.
 *
 * 4) Social Networking Websites: In social networks, we can find people within a given distance ‘k’ from a person using Breadth First Search till ‘k’ levels.
 *
 * 5) GPS Navigation systems: Breadth First Search is used to find all neighboring locations.
 *
 * 6) Broadcasting in Network: In networks, a broadcasted packet follows Breadth First Search to reach all nodes.
 *
 * 7) In Garbage Collection: Breadth First Search is used in copying garbage collection using Cheney’s algorithm. Refer this and for details. Breadth First Search is preferred over Depth First Search because of better locality of reference:
 *
 * 8) Cycle detection in undirected graph: In undirected graphs, either Breadth First Search or Depth First Search can be used to detect cycle. We can use BFS to detect cycle in a directed graph also,
 *
 * 9) Ford–Fulkerson algorithm In Ford-Fulkerson algorithm, we can either use Breadth First or Depth First Traversal to find the maximum flow. Breadth First Traversal is preferred as it reduces worst case time complexity to O(VE2).
 *
 * 10) To test if a graph is Bipartite We can either use Breadth First or Depth First Traversal.
 *
 * 11) Path Finding We can either use Breadth First or Depth First Traversal to find if there is a path between two vertices.
 *
 * 12) Finding all nodes within one connected component: We can either use Breadth First or Depth First Traversal to find all nodes reachable from a given node.
 *
 * Many algorithms like Prim’s Minimum Spanning Tree and Dijkstra’s Single Source Shortest Path use structure similar to Breadth First Search.
 *
 * There can be many more applications as Breadth First Search is one of the core algorithms for Graphs.
 */
public class BreadthFirstSearch{

    ALogger<BreadthFirstSearch> LOGGER = new ALogger<>(BreadthFirstSearch.class);

    public int findShortestDistances(int[][][] graph, int paramsourceid, int paramdestid){

        Throwable stackTrace = new Throwable();

        int[] distances = new int [graph.length];
        Object[] previous = new Object [graph.length] ;//defaults are null
        for(int i = 0;  i < distances.length; i++){distances[i]=Integer.MAX_VALUE;}//for fill defaults

        Queue<Integer> queue = new LinkedList<>();
        distances[paramsourceid] = 0 ;//distance to self
        queue.add (paramsourceid);
        List<Object> trajectory = new ArrayList<>();
        boolean [] visited = new boolean[graph.length];

        int pollCount = 0;
        while(!queue.isEmpty()){
            int currentVertex = queue.poll();
            if(visited[currentVertex]){
                LOGGER.info("Already Visited:"+currentVertex);
                continue;
            }
            trajectory.add(currentVertex);
            pollCount++;

            int distanceSoFarToCurrentVertex = distances[currentVertex];

            for(int i = 0; i < graph[currentVertex].length; i++){
                int nbindex = graph[currentVertex][i][0];
                int newDistance = distanceSoFarToCurrentVertex + 1 ;//default weight is 1
                if(newDistance < distances[nbindex]){
                    distances[nbindex] = newDistance;
                    previous[nbindex] = currentVertex;
                    queue.add(nbindex);
                }//if distance compare
            }//for neighbours
            visited[currentVertex] = true;
        }//while

// Results prepare

        int shortestDistanceFound =  distances[paramdestid];

        int iteratorIndex = paramdestid;
        List<Object> path =  new ArrayList<>();
        while(previous[iteratorIndex] != null){

            path.add(previous[iteratorIndex]);
            iteratorIndex = (int)previous[iteratorIndex];
        }

        Collections.reverse(path);
        LOGGER.info("PATH:"+path,stackTrace);
          LOGGER.info("TRAJECTORY:"+trajectory,stackTrace);

        //System.out.println("---- result---");
        LOGGER.info("Result:"+distances[paramdestid],stackTrace);


        LOGGER.info("POLLCOUNT="+pollCount,stackTrace);
        return shortestDistanceFound;

    }//findShortestDistance

    public static void main(String[] args) {

        int[][][] sample = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.PLANAR_GRAPH);

        ALogger.TIMER t = new ALogger.TIMER();
        t.startTimer();

        BreadthFirstSearch d = new BreadthFirstSearch();
        d.findShortestDistances(sample, 0, 7);

        t.getBenchmark(t);
    }
}//class
