package graph;

import lib.model.abstraction.graph.IEdge;
import lib.model.abstraction.graph.IVertex;
import lib.model.abstraction.graph.ListyGraph;

import java.util.*;
import java.util.stream.IntStream;


/**
 * Single source shortest path
 * Works on graphs with has no negative weights for can not detect - cycles.
 *
 * Greedy- O(V log E)
 * Uses PriorityQueue
 */
public class Dijkstara {

    public int[] shortestPath(int[][] graph, int numberofvertex, int src, int dest, boolean isDirected) {
        Comparator<int[]> newEntrySmallerTop = (a, b) -> Integer.compare(a[0], b[0]);
        Object[] previous = new Object[numberofvertex];
        int[] distances = new int[numberofvertex];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        // int{vertexid, shortestdistancetosource}
        TreeSet<int[]> treeSet = new TreeSet<>(newEntrySmallerTop);
        // add source
        treeSet.add(new int[]{src, 0});

        while (!treeSet.isEmpty()) {
            int[] current = treeSet.pollFirst();
            int vtx = current[0];
            //directed graph
            if (isDirected) {
                IntStream.range(0, graph.length).filter(x -> graph[x][0] == vtx).forEach(edgePairId -> {
                    int nbid = graph[edgePairId][1];
                    int weight = graph[edgePairId][2];
                    int currentDist = distances[nbid];
                    int futureDist = distances[vtx] + weight;
                    if (futureDist < currentDist) {
                        distances[nbid] = futureDist;
                        previous[nbid] = vtx;
                        treeSet.add(new int[]{nbid, futureDist});
                    }
                });
            } else {
                IntStream.range(0, graph.length).filter(x -> graph[x][0] == vtx || graph[x][1] == vtx).forEach(edgePairId -> {//check bot sides for neighbour
                    int nbid = graph[edgePairId][0] == vtx ? graph[edgePairId][1] : graph[edgePairId][0];
                    int weight = graph[edgePairId][2];
                    int currentDist = distances[nbid];
                    int futureDist = distances[vtx] + weight;
                    if (futureDist < currentDist) {
                        distances[nbid] = futureDist;
                        previous[nbid] = vtx;
                        treeSet.add(new int[]{nbid, futureDist});
                    }
                });
            }
        }

        //
        //Path reconstruct

        Integer navigationvertex = dest;
        List<Integer> path = new ArrayList<>();
        while (previous[navigationvertex] != null) {
            navigationvertex = (Integer) previous[navigationvertex];
            path.add(navigationvertex);
            if (navigationvertex == src)
                break;
        }

        Collections.reverse(path);

        return path.stream().mapToInt(a -> a).toArray();

    }

    public int shortestDistance(int[][] graph, int numberofvertex, int src, int dest, boolean isDirected) {
        Comparator<int[]> newEntrySmallerTop = (a, b) -> Integer.compare(a[0], b[0]);
        int[] distances = new int[numberofvertex];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        // int{vertexid, shortestdistancetosource}
        TreeSet<int[]> treeSet = new TreeSet<>(newEntrySmallerTop);
        // add source
        treeSet.add(new int[]{src, 0});

        while (!treeSet.isEmpty()) {
            int[] current = treeSet.pollFirst();
            int vtx = current[0];
            //nondirected
            if (isDirected) {
                IntStream.range(0, graph.length).filter(x -> graph[x][0] == vtx).forEach(edgePairId -> {
                    int nbid = graph[edgePairId][1];
                    int weight = graph[edgePairId][2];
                    int currentDist = distances[nbid];
                    int futureDist = distances[vtx] + weight;

                    if (futureDist < currentDist) {
                        distances[nbid] = futureDist;
                        treeSet.add(new int[]{nbid, futureDist});
                    }

                });
            } else {
                IntStream.range(0, graph.length).filter(x -> graph[x][0] == vtx || graph[x][1] == vtx).forEach(edgePairId -> {
                    int nbid = graph[edgePairId][0] == vtx ? graph[edgePairId][1] : graph[edgePairId][0];
                    int weight = graph[edgePairId][2];
                    int currentDist = distances[nbid];
                    int futureDist = distances[vtx] + weight;
                    if (futureDist < currentDist) {
                        distances[nbid] = futureDist;
                        treeSet.add(new int[]{nbid, futureDist});
                    }
                });
            }
        }

        return distances[dest] == Integer.MAX_VALUE ? -1 : distances[dest];

    }

}