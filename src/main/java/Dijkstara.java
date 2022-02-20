import java.util.*;

/**
 * Dijkstra’s algorithm is one of the most popular algorithms for solving many single-source shortest path problems having non-negative edge weight in the graphs i.e., it is to find the shortest distance between two vertices on a graph. It was conceived by computer scientist Edsger W. Dijkstra in 1956 and published three years later.
 *
 * Dijkstra’s Algorithm has several real-world use cases, some of which are as follows:
 *
 * Digital Mapping Services in Google Maps: Many times we have tried to find the distance in G-Maps, from one city to another, or from your location to the nearest desired location. There encounters the Shortest Path Algorithm, as there are various routes/paths connecting them but it has to show the minimum distance, so Dijkstra’s Algorithm is used to find the minimum distance between two locations along the path. Consider India as a graph and represent a city/place with a vertex and the route between two cities/places as an edge, then by using this algorithm, the shortest routes between any two cities/places or from one city/place to another city/place can be calculated.
 * Social Networking Applications: In many applications you might have seen the app suggests the list of friends that a particular user may know. How do you think many social media companies implement this feature efficiently, especially when the system has over a billion users. The standard Dijkstra algorithm can be applied using the shortest path between users measured through handshakes or connections among them. When the social networking graph is very small, it uses standard Dijkstra’s algorithm along with some other features to find the shortest paths, and however, when the graph is becoming bigger and bigger, the standard algorithm takes a few several seconds to count and alternate advanced algorithms are used.
 * Telephone Network: As we know, in a telephone network, each line has a bandwidth, ‘b’. The bandwidth of the transmission line is the highest frequency that that line can support. Generally, if the frequency of the signal is higher in a certain line, the signal is reduced by that line. Bandwidth represents the amount of information that can be transmitted by the line. If we imagine a city to be a graph, the vertices represent the switching stations, and the edges represent the transmission lines and the weight of the edges represents ‘b’. So as you can see it can fall into the category of shortest distance problem, for which the Dijkstra is can be used.
 * IP routing to find Open shortest Path First: Open Shortest Path First (OSPF) is a link-state routing protocol that is used to find the best path between the source and the destination router using its own Shortest Path First. Dijkstra’s algorithm is widely used in the routing protocols required by the routers to update their forwarding table. The algorithm provides the shortest cost path from the source router to other routers in the network.
 * Flighting Agenda: For example, If a person needs software for making an agenda of flights for customers. The agent has access to a database with all airports and flights. Besides the flight number, origin airport, and destination, the flights have departure and arrival time. Specifically, the agent wants to determine the earliest arrival time for the destination given an origin airport and start time. There this algorithm comes into use.
 * Designate file server: To designate a file server in a LAN(local area network), Dijkstra’s algorithm can be used. Consider that an infinite amount of time is required for transmitting files from one computer to another computer. Therefore to minimize the number of “hops” from the file server to every other computer on the network the idea is to use Dijkstra’s algorithm to minimize the shortest path between the networks resulting in the minimum number of hops.
 * Robotic Path: Nowadays, drones and robots have come into existence, some of which are manual, some automated. The drones/robots which are automated and are used to deliver the packages to a specific location or used for a task are loaded with this algorithm module so that when the source and destination is known, the robot/drone moves in the ordered direction by following the shortest path to keep delivering the package in a minimum amount of time.
 */
public class Dijkstara {

    ALogger<Dijkstara> LOGGER = new ALogger<>(Dijkstara.class);

    private static class MinMinDistanceTo {

      public int id = -1;
        public MinMinDistanceTo from;
        public double minDist = Integer.MAX_VALUE;
        public int previous = -1;
    }

    public double getShortestPathAndDistance(int[][][] paramGraph, int paramSourceId, int paramDestId) {

        Throwable stackTrace = new Throwable();


        boolean isValidGraph = paramGraph == null ? false : paramGraph.length == 0 ? false : true;
        if (!isValidGraph)
            return -1;

        boolean istargetoverlaps = paramSourceId == paramDestId;
        if (istargetoverlaps)
            return 0;


        MinMinDistanceTo[] results = new MinMinDistanceTo[paramGraph.length];

        for (int i = 0; i < paramGraph.length; i++) {
            results[i] = new MinMinDistanceTo();
        }

        Queue<MinMinDistanceTo> priorityQueue = new PriorityQueue<MinMinDistanceTo>((a, b) -> a.minDist > b.minDist ? 1 : -1);
        MinMinDistanceTo startPoint = results[paramSourceId];
        startPoint.id = paramSourceId;
        startPoint.minDist = 0;
        priorityQueue.add(startPoint);
        results[paramSourceId] = startPoint;
        List<Object> trajectory = new ArrayList<>();

        boolean[] visited = new boolean[paramGraph.length];

        double currentPointdistance = 0;

        int popCount = 0;

        while (!priorityQueue.isEmpty()) {

            MinMinDistanceTo currentPoint = priorityQueue.poll();
            if(visited[currentPoint.id]){
                LOGGER.info("AlreadyVisited:"+currentPoint.id);
                continue;
            }

            trajectory.add(currentPoint.previous+"->"+currentPoint.id+"="+currentPoint.minDist);

           popCount++;

            currentPointdistance = results[currentPoint.id].minDist;

            for (int i = 0; i < paramGraph[currentPoint.id].length; i++) {

                int neighbourIndex = paramGraph[currentPoint.id][i][0];

                double weight = paramGraph[currentPoint.id][i][1];
                double newDist = currentPointdistance + weight;

                if (newDist < results[neighbourIndex].minDist) {
                    results[neighbourIndex].minDist = newDist;
                    results[neighbourIndex].id = neighbourIndex;
                    results[neighbourIndex].previous = currentPoint.id;
                    priorityQueue.add(results[neighbourIndex]);
                    priorityQueue.remove(currentPoint);
                }
            }
            visited[currentPoint.id] = true;

        }
        LOGGER.info("POLLCOUNT="+popCount,stackTrace);


        int i = paramDestId;
        List path = new ArrayList();
        while (results[i].previous != -1) {


            path.add(results[i].previous);
            i = results[i].previous;


        }



//        Arrays.stream(results).forEach(a -> System.out.println(paramSourceId + "->" + a.id + ":" + a.minDist));


        Collections.reverse(path);
        LOGGER.info("PATH:"+path,stackTrace);
      //  LOGGER.info("TRAJECTORY:"+trajectory,stackTrace);

        //System.out.println("---- result---");
        LOGGER.info("Result:"+results[paramDestId].minDist,stackTrace);

        return results[paramDestId].minDist;

    }

    public static void main(String[] args) {
        int[][][] sample = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.WEIGHTED_GRAPH);
        int[][][] summer = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.SUMMER);
        int[][][] brown = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.BROWN);
        int[][][] rs001 = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.RS_0001);
        int[][][] random = SampleInputs.generateRandomGraph(15000,true);

        ALogger.TIMER t = new ALogger.TIMER();

        t.startTimer();

        Dijkstara d = new Dijkstara();
        d.getShortestPathAndDistance(rs001, 8, 0);

        t.getBenchmark(t);

    }

}
