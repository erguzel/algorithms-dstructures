package graph;

import lib.util.ALogger;
import sort.ReverseArray;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * O(E+V)
 *
 * Use Cases
 * 1) Shortest Path and Minimum Spanning Tree for unweighted graph In an unweighted graph, the shortest path is the path with least number of edges. With Breadth First, we always reach a vertex from given source using the minimum number of edges. Also, in case of unweighted graphs, any spanning tree is Minimum Spanning Tree and we can use either Depth or Breadth first traversal for finding a spanning tree.
 * 2) Peer to Peer Networks. In Peer to Peer Networks like BitTorrent, Breadth First Search is used to find all neighbor nodes.
 * 3) Crawlers in Search Engines: Crawlers build index using Breadth First. The idea is to start from source page and follow all links from source and keep doing same. Depth First Traversal can also be used for crawlers, but the advantage with Breadth First Traversal is, depth or levels of the built tree can be limited.
 * 4) Social Networking Websites: In social networks, we can find people within a given distance ‘k’ from a person using Breadth First Search till ‘k’ levels.
 * 5) GPS Navigation systems: Breadth First Search is used to find all neighboring locations.
 * 6) Broadcasting in Network: In networks, a broadcasted packet follows Breadth First Search to reach all nodes.
 * 7) In Garbage Collection: Breadth First Search is used in copying garbage collection using Cheney’s algorithm. Refer this and for details. Breadth First Search is preferred over Depth First Search because of better locality of reference:
  * 8) Cycle detection in undirected graph: In undirected graphs, either Breadth First Search or Depth First Search can be used to detect cycle. We can use graph.BFS to detect cycle in a directed graph also,
 * 9) Ford–Fulkerson algorithm In Ford-Fulkerson algorithm, we can either use Breadth First or Depth First Traversal to find the maximum flow. Breadth First Traversal is preferred as it reduces worst case time complexity to O(VE2).
 * 10) To test if a graph is Bipartite We can either use Breadth First or Depth First Traversal.
 * 11) Path Finding We can either use Breadth First or Depth First Traversal to find if there is a path between two vertices.
 * 12) Finding all nodes within one connected component: We can either use Breadth First or Depth First Traversal to find all nodes reachable from a given node.
 * Many algorithms like Prim’s Minimum Spanning Tree and Dijkstra’s Single Source Shortest Path use structure similar to Breadth First Search.
 * There can be many more applications as Breadth First Search is one of the core algorithms for Graphs.
 */
public class BFS {

    ALogger<BFS> LOGGER = new ALogger<>(BFS.class);

    public static void main(String[] args) {
        int[][][] graph = null;//DataUtil.Convertors._convertAdjmtxToAdjList(SampleData.GraphOnlineRu.DUCK_DIR_WEG);

        System.out.println();

        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        BFS bfsAgain = new BFS();
        bfsAgain.findTrajectoryAndShortestPath(graph, 9, 0);
        timer.getBenchmark(timer);
    }

    public void findTrajectoryAndShortestPath(int[][] graph, int paramsourceid, int paramtargetid) {
        boolean[] visited = new boolean[graph.length];
        Object[] previous = new Object[graph.length]; // default null
        boolean[] pathExists = new boolean[graph.length];
        int[] distances = new int[graph.length];
        List<Integer> trajectory = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }//for defaults
        distances[paramsourceid] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(paramsourceid);

        int pollCount = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            pollCount++;
            if (visited[current]) {
                continue;
            }// if visited
            visited[current] = true;
            trajectory.add(current);
            int distanceSoFar = distances[current];
// visit nbours
            for (int i = 0; i < graph[current].length; i++) {
                if (graph[current][i] != 0) {
                    int nbid = i;
                    int weight = 1;// default nonweighted graph
                    int newDistance = distanceSoFar + weight;
                    if (newDistance < distances[nbid]) {
                        distances[nbid] = newDistance;
                        previous[nbid] = current;
                        pathExists[nbid] = true;
                        if (!visited[nbid]) {
                            queue.add(nbid);
                        }//if add queue
                    }//if compare dist
                }// skip 0 s
            }// for nbours
        }//while queue

        int target = paramtargetid;
        List<Object> path = new ArrayList<>();
        path.add(paramtargetid);
        while (previous[target] != null) {
            path.add(previous[target]);
            target = (int) previous[target];
        }

        if (pathExists[paramtargetid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        } else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramtargetid);

        LOGGER.info("Tajectory:" + trajectory);
        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + distances[paramtargetid]);

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));

    }//findTrajectory..

    public void findTrajectoryAndShortestPath(int[][][] graph, int paramsourceid, int paramtargetid) {
        boolean[] visited = new boolean[graph.length];
        Object[] previous = new Object[graph.length]; // default null
        boolean[] pathExists = new boolean[graph.length];
        int[] distances = new int[graph.length];
        List<Integer> trajectory = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }//for defaults
        distances[paramsourceid] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(paramsourceid);

        int pollCount = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            pollCount++;
            if (visited[current]) {
                continue;
            }// if visited
            visited[current] = true;
            trajectory.add(current);
            int distanceSoFar = distances[current];
// visit nbours
            for (int i = 0; i < graph[current].length; i++) {

                int nbid = graph[current][i][0];
                int weight = 1;// default nonweighted graph
                int newDistance = distanceSoFar + weight;
                if (newDistance < distances[nbid]) {
                    distances[nbid] = newDistance;
                    previous[nbid] = current;
                    pathExists[nbid] = true;
                    if (!visited[nbid]) {
                        queue.add(nbid);
                    }//if add queue
                }//if compare dist

            }// for nbours
        }//while queue

        int target = paramtargetid;
        List<Object> path = new ArrayList<>();
        path.add(paramtargetid);
        while (previous[target] != null) {
            path.add(previous[target]);
            target = (int) previous[target];
        }

        if (pathExists[paramtargetid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        } else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramtargetid);

        LOGGER.info("Tajectory:" + trajectory);
        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + distances[paramtargetid]);

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));

    }//findTrajectory..


    public void traverse_EDLIST(int[][] edlist, int sourceid, boolean isDirected) {
// validate

        boolean[] visited = new boolean[edlist.length];
        Queue<Integer> callQueue = new LinkedList<>();
        callQueue.add(sourceid);
        var streamHelper = new Object() {
            int nbid = Integer.MAX_VALUE;
            int count = 0;

            void reset() {
                nbid = Integer.MAX_VALUE;
            }

            ;
        };

        while (!callQueue.isEmpty()) {
            int current = callQueue.poll();
            streamHelper.count++;
            LOGGER.info(current + "-->" + streamHelper.count);

            if (!isDirected) {
                IntStream.range(0, edlist.length).filter(x -> edlist[x][0] == current || edlist[x][1] == current)
                        .forEach(nbouredge -> {
                            streamHelper.nbid = edlist[nbouredge][0] == current ? edlist[nbouredge][1] : edlist[nbouredge][0];
                            if (!visited[streamHelper.nbid]) {
                                visited[streamHelper.nbid] = true;
                                callQueue.add(streamHelper.nbid);
                                streamHelper.reset();
                            }
                        });
            } else {
                IntStream.range(0, edlist.length).filter(x -> edlist[x][0] == current)
                        .forEach(nbouredge -> {
                            streamHelper.nbid = edlist[nbouredge][1];

                            if (!visited[streamHelper.nbid]) {
                                visited[streamHelper.nbid] = true;
                                callQueue.add(streamHelper.nbid);
                                streamHelper.reset();
                            }
                        });
            }


        }//while callqueue
    }//traverse

    public boolean pathExists_EDLIST(int [][] edlist, int sourceid, int destid, boolean isDirected){
// validate

        boolean[] visited = new boolean[edlist.length];
        Queue<Integer> callQueue = new LinkedList<>();
        callQueue.add(sourceid);
        var streamHelper = new Object(){
            boolean pathExists = false;
            int nbid = Integer.MAX_VALUE;
            void reset ( ) { nbid = Integer.MAX_VALUE;};
        };

        while(!callQueue.isEmpty()){
            int current = callQueue.poll();

            if(!isDirected){
                IntStream.range(0,edlist.length).filter(x->edlist[x][0] == current || edlist[x][1]==current)
                        .forEach(nbouredge ->{
                            streamHelper.nbid = edlist[nbouredge][0] == current?edlist[nbouredge][1]:edlist[nbouredge][0];
                            if(streamHelper.nbid==destid) {
                                streamHelper.pathExists = true;
                                return;
                            };
                            if(!visited[streamHelper.nbid]){
                                visited[streamHelper.nbid] = true;
                                callQueue.add(streamHelper.nbid);
                                streamHelper.reset();
                            }
                        });
            }else{
                IntStream.range(0,edlist.length).filter(x->edlist[x][0]==current)
                        .forEach(nbouredge->{
                            streamHelper.nbid = edlist[nbouredge][1];
                            if(streamHelper.nbid==destid) {
                                streamHelper.pathExists = true;
                                return;
                            };
                            if (!visited[streamHelper.nbid]){
                                visited[streamHelper.nbid] = true;
                                callQueue.add(streamHelper.nbid);
                                streamHelper.reset();
                            }
                        });
            }



        }//while callqueue

        return streamHelper.pathExists;
    }//pathExists

    public int[] getPath_EDLIST(int[][] edlist, int numberofvertexes, int sourceid, int destid, boolean isDirected) {
// validate

        boolean[] visited = new boolean[edlist.length];
        Object[] previous = new Object[numberofvertexes];
        Queue<Integer> callQueue = new LinkedList<>();
        callQueue.add(sourceid);
        var streamHelper = new Object() {
            boolean pathExists = false;
            int nbid = Integer.MAX_VALUE;

            void reset() {
                nbid = Integer.MAX_VALUE;
            };
        };

        while (!callQueue.isEmpty()) {
            int current = callQueue.poll();

            if (!isDirected) {
                IntStream.range(0, edlist.length).filter(x -> edlist[x][0] == current || edlist[x][1] == current)
                        .forEach(nbouredge -> {
                            streamHelper.nbid = edlist[nbouredge][0] == current ? edlist[nbouredge][1] : edlist[nbouredge][0];
                            if (!visited[streamHelper.nbid]) {
                                visited[streamHelper.nbid] = true;
                                callQueue.add(streamHelper.nbid);
                                previous[streamHelper.nbid] = current;
                                if (streamHelper.nbid == destid) {
                                    streamHelper.pathExists = true;
                                    return;
                                }
                            }
                            streamHelper.reset();
                        });
            } else {
                IntStream.range(0, edlist.length).filter(x -> edlist[x][0] == current)
                        .forEach(nbouredge -> {
                            streamHelper.nbid = edlist[nbouredge][1];
                            if (!visited[streamHelper.nbid]) {
                                visited[streamHelper.nbid] = true;
                                callQueue.add(streamHelper.nbid);
                                previous[streamHelper.nbid] = current;
                                if (streamHelper.nbid == destid) {
                                    streamHelper.pathExists = true;
                                    return;
                                }
                                ;
                            }
                            streamHelper.reset();
                        });
            }


        }//while callqueue

        //Path construction
        List<Integer> path = new ArrayList<>();

        if (streamHelper.pathExists) {
            int src = sourceid;
            int dst = destid;

            while (previous[dst] != null) {
                path.add((int) previous[dst]);
                dst = (int) previous[dst];
                if(dst==src)break;
            }

        }
        Collections.reverse(path);
        return path.stream().mapToInt(a -> a).toArray();
    }//getPath_EDLIST


}//class
