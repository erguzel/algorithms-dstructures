import model.Edge;
import model.Vertex;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SSSP
 */
public class BellmanFord {

    static ALogger<BellmanFord> LOGGER = new ALogger<>(BellmanFord.class);

    public static void main(String[] args) {

        int[][] input = SampleData.Csacademy.CSA001;

        new Thread(()->{
            LOGGER.info(SampleData.Csacademy.stringfyGraphForOnlineVisualisation(input));
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            BellmanFord bellmanFord = new BellmanFord();
            bellmanFord.findShortestPathsAndDistancesWithMap(input, 0, 4);
            timer.getBenchmark(timer,"MAP");
        }).start();


        new Thread(()->{
            LOGGER.info(SampleData.Csacademy.stringfyGraphForOnlineVisualisation(input));
            ALogger.TIMER timer1 = new ALogger.TIMER();
            timer1.startTimer();
            BellmanFord bellmanFord1 = new BellmanFord();
            bellmanFord1.findShortestPaths(input, 0, 4);
            timer1.getBenchmark(timer1,"Normal");
        }).start();




    }

    /**
     * USES MAP
     * @param graph
     * @param paramsourceid
     * @param paramdestid
     */
    public void findShortestPathsAndDistancesWithMap(int[][] graph, int paramsourceid, int paramdestid){
        Map<Integer,Double> distances = new HashMap<>();
        boolean [] pathExists = new boolean[graph.length];
        boolean hasNegativeCycle = false;
        for(int i = 0; i < graph.length; i++){distances.put(i,Double.MAX_VALUE);}//for initials
        distances.put(paramsourceid,0.0);
        Object[] previous = new Object[graph.length+1];// shorter than edge list

        int dynnamicNumberOfVertexes = 1;
        double distanceSoFar = 0;


        for(int i =0; i < dynnamicNumberOfVertexes; i++){

            for(int j = 0; j < graph.length;j++){

                int src = graph[j][0];
                if(src>dynnamicNumberOfVertexes){
                    dynnamicNumberOfVertexes = src+1;
                }// if vertex num update
                int dest = graph[j][1];
                if(dest>dynnamicNumberOfVertexes){
                    dynnamicNumberOfVertexes = dest;
                }//if vertex num update


                double weight = graph[j][2];
                distanceSoFar =  distances.get(src);


                if(distanceSoFar == Double.MAX_VALUE){
                    distanceSoFar = weight;
                    distances.put(src,distanceSoFar);
                }else{
                    double newDist = distanceSoFar + weight;
                    if(newDist<distances.get(dest)){
                        distances.put(dest,newDist);
                        pathExists[dest] = true;
                        previous[dest] = src;

                    }//if compare

                }//if else
            }//for all edges
        }//for all vertexes


        //detect - cycle

        for (int j = 0; j < graph.length; j++) {

            int source = graph[j][0];
            int target = graph[j][1];
            double weight = graph[j][2];

            distanceSoFar = distances.get(source);
            if (distanceSoFar == Integer.MAX_VALUE) {
                distances.put( source,weight);
            } else {
                double newValue = distanceSoFar + weight;
                if (newValue < distances.get(target)) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }


        if(hasNegativeCycle){
            LOGGER.info("Graph has negative cycle");
            return;
        }

        int target = paramdestid;
        List<Object> path = new ArrayList<>();
        path.add(paramdestid);
        while (previous[target] != null) {
            path.add((previous[target]));
            target = (int)previous[target];
        }// while nonnull prev



        if (pathExists[paramdestid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramdestid);

        } else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramdestid);

//        LOGGER.info("Tajectory:" + trajectory);
//        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + distances.get(paramdestid));

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));

    }//findShortestPathsAndDistances

    /**
     * USES ARRAY
     * @param graph
     * @param paramsourceid
     * @param paramdestid
     */
    public void findShortestPaths(int[][] graph, int paramsourceid, int paramdestid) {
        //  relax edges n-1 times
        // prepare a collection of vertexes
        // mark inf for initial vertexe dists
        // select one by one each edge
        // relax each edge
        // if negative cycle exists BellmanFord useless

        int numberOfVertexes = 0;

        for (int i = 0; i < graph.length; i++) {

            if (graph[i][0] > numberOfVertexes) {
                numberOfVertexes = graph[i][0] + 1;
            }
            if (graph[i][1] > numberOfVertexes) {
                numberOfVertexes = graph[i][1] + 1;
            }
        }

        double[] distances = new double[numberOfVertexes];
        for (int i = 0; i < numberOfVertexes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[paramsourceid] = 0;
        Object[] previous = new Object[numberOfVertexes];
        previous[paramsourceid] = null;
        distances[paramsourceid] = 0;
        boolean[] pathExists = new boolean[numberOfVertexes];
        boolean hasNegativeCycle=false;


        double distanceSoFar = 0;
        boolean hasCycle = false;
        for (int i = 0; i < numberOfVertexes; i++) {



            for (int j = 0; j < graph.length; j++) {

                int source = graph[j][0];
                int target = graph[j][1];
                double weight = graph[j][2];

                distanceSoFar = distances[source];
                if (distanceSoFar == Integer.MAX_VALUE) {
                    distances[source] = weight;
                } else {
                    double newValue = distanceSoFar + weight;
                    if (newValue < distances[target]) {
                        distances[target] = newValue;
                        previous[target] = source;
                        pathExists[target]= true;
                    }
                }
            }
        }


        //detect - cycle

        for (int j = 0; j < graph.length; j++) {

            int source = graph[j][0];
            int target = graph[j][1];
            double weight = graph[j][2];

            distanceSoFar = distances[source];
            if (distanceSoFar == Integer.MAX_VALUE) {
                distances[source] = weight;
            } else {
                double newValue = distanceSoFar + weight;
                if (newValue < distances[target]) {
                    hasNegativeCycle = true;
                    break;
                }
            }
        }

        if(hasNegativeCycle){
            LOGGER.info("Graph has negative cycle");
            return;
        }


        int target = paramdestid;
        List<Object> path = new ArrayList<>();
        path.add(paramdestid);
        while (previous[target] != null) {
            path.add((previous[target]));
            target = (int)previous[target];
        }// while nonnull prev




        if (pathExists[paramdestid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramdestid);

        } else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramdestid);

//        LOGGER.info("Tajectory:" + trajectory);
//        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + distances[paramdestid]);

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));

    }

}
