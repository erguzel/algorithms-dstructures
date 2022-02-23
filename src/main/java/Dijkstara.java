import model.abstraction.MinDistance;

import java.util.*;
import java.util.stream.Collectors;


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

    public static void main(String[] args) {
        //int[][][] graph = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.DUCK_DIR_WEG);
        int[][] graph = SampleData.GraphOnlineRu.DUCK_DIR_WEG;

        System.out.println();

        Dijkstara dj = null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                ALogger.TIMER timer = new ALogger.TIMER();
                timer.startTimer();
                Dijkstara dj = new Dijkstara();
                dj.findTrajectoryAndShortestPath(graph, 3, 6);
                timer.getBenchmark(timer, "Static Struct");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ALogger.TIMER timer = new ALogger.TIMER();
                timer.startTimer();
                Dijkstara dj = new Dijkstara();
                dj.findTrajectoryAndShortestPathWithArray(graph, 3, 6);
                timer.getBenchmark(timer,"Array Queue");
            }
        }).start();

         new Thread(new Runnable() {
            @Override
            public void run() {
                ALogger.TIMER timer = new ALogger.TIMER();
                timer.startTimer();
                Dijkstara dj = new Dijkstara();
                dj.findTrajectoryAndShortestPathWithSOLID(graph, 3, 6);
                timer.getBenchmark(timer,"SOLID");
            }
        }).start();

    }

    ALogger<DFS> LOGGER = new ALogger<>(DFS.class);
    private static class VertexDistance{
        // todo encapsulate fields
        public int id = -1; //vertex id in array
        public double distance = Double.MAX_VALUE;//default infinity
        public Object previous = null;// when no previous exists
    }// class static inner

    public void findTrajectoryAndShortestPath(int[][] graph, int paramsourceid, int paramtargetid){
        boolean[] visited = new boolean[graph.length];
        VertexDistance[] vertexDistances = new VertexDistance[graph.length]; //
        boolean [] pathExists = new boolean[graph.length];
        List<Integer> trajectory = new ArrayList<>();

        for(int i =0; i < vertexDistances.length;i++){vertexDistances [i] = new VertexDistance();}//for fill def

        vertexDistances [paramsourceid].distance = 0;
        vertexDistances [paramsourceid].id = paramsourceid;

        PriorityQueue<VertexDistance> queue = new PriorityQueue<>((a,b)->a.distance>b.distance?1:-1);// smaller first
        queue.add(vertexDistances [paramsourceid]);

        int pollCount = 0;
        while(!queue.isEmpty()){

            VertexDistance current = queue.poll();
            pollCount++;
            if(visited[current.id]){continue;}// if visited
            visited[current.id] = true;
            trajectory.add(current.id);
            double distanceSoFar = current.distance;
            for(int i =0; i < graph[current.id].length; i++){
                int nbid = i;
                if(graph[current.id][nbid]!=0){
                    double weight =graph[current.id][nbid];
                    double newDistance =  distanceSoFar + weight;
                    if(newDistance<vertexDistances[nbid].distance){
                        vertexDistances[nbid].distance = newDistance;
                        vertexDistances[nbid].previous = current.id;
                        vertexDistances[nbid].id = nbid;
                        if(!visited[vertexDistances[nbid].id]){
                            pathExists[vertexDistances[nbid].id] = true;
                            queue.add(vertexDistances[nbid]);
                        }// if visited
                    }//if smaller relax new dist
                }//if nonzero
            }// for nbour explore
        }// while queue

        int target = paramtargetid;
        List<Object> path  = new ArrayList<>();
        path.add(paramtargetid);
        while(vertexDistances[target].previous != null){
            path.add(vertexDistances[target].previous);
            target  = (int)vertexDistances[target].previous;
        }// while nonnull prev

        if (pathExists[paramtargetid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        }else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramtargetid);

        LOGGER.info("Tajectory:" + trajectory);
        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + vertexDistances[paramtargetid].distance);

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));

    }//findTrajectoryAndShortestPath

    // using build in type as min distances
    public void findTrajectoryAndShortestPathWithArray(int[][] graph, int paramsourceid, int paramtargetid){


        boolean[] visited = new boolean[graph.length];
        double[][] vertexDistances = new double[graph.length][2];// represents the index and min distance of a candidate
        Object [] previous = new Object[graph.length];//default null
        boolean [] pathExists = new boolean[graph.length];
        List<Integer> trajectory = new ArrayList<>();

        for(int i =0; i < vertexDistances.length;i++){
            double [] candidate = {-1, Integer.MAX_VALUE};
            vertexDistances [i] =  candidate;
        }//for fill def

        vertexDistances[paramsourceid][0] = paramsourceid;
        vertexDistances[paramsourceid][1] = 0;


        PriorityQueue<double[]> queue = new PriorityQueue<>((a,b)->a[1]>b[1]?1:-1);// smaller valiue first
        queue.add(vertexDistances [paramsourceid]);

        int pollCount = 0;
        while(!queue.isEmpty()){

            double[] current = queue.poll();
            pollCount++;
            if(visited[(int) current[0]]){continue;}// if visited
            visited[(int)current[0]] = true;
            trajectory.add((int)current[0]);
            double distanceSoFar = current[1];
            for(int i =0; i < graph[(int)current[0]].length; i++){
                int nbid = i;
                if(graph[(int)current[0]][nbid]!=0){
                    double weight =graph[(int)current[0]][nbid];
                    double newDistance =  distanceSoFar + weight;
                    if(newDistance<vertexDistances[nbid][1]){
                        vertexDistances[nbid][1] = newDistance;
                        previous[nbid] = current[0];
                        vertexDistances[nbid][0] = nbid;
                        if(!visited[nbid]){
                            pathExists[nbid] = true;
                            queue.add(vertexDistances[nbid]);
                        }// if visited
                    }//if smaller relax new dist
                }//if nonzero
            }// for nbour explore
        }// while queue

        int target = paramtargetid;
        List<Object> path  = new ArrayList<>();
        path.add(paramtargetid);
        while(previous[ target] != null){
            path.add(((Double)previous[target]).intValue());
            target  = ((Double)previous[target]).intValue();
        }// while nonnull prev

        if (pathExists[paramtargetid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        }else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramtargetid);

        LOGGER.info("Tajectory:" + trajectory);
        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + vertexDistances[paramtargetid][1]);

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));

    }//findTrajectoryAndShortestPath

    // using abstraction types with interface sergeration principle
    public void findTrajectoryAndShortestPathWithSOLID(int[][] graph, int paramsourceid, int paramtargetid){
        Throwable stackTrace = new Throwable();

        MinDistance[] vertexDistances = new MinDistance[graph.length];// represents the index and min distance of a candidate
        List<MinDistance> trajectory = new ArrayList<>();
        for(int i = 0; i < vertexDistances.length; i++){vertexDistances[i] = new MinDistance();}
        vertexDistances[paramsourceid].setId(paramsourceid);
        vertexDistances[paramsourceid].setValue(0);
        vertexDistances[paramsourceid].setPrevious(null);
        PriorityQueue<MinDistance> queue = new PriorityQueue<>((a,b)->a.getValue()>b.getValue()?1:-1);// smaller valiue first
        queue.add(vertexDistances [paramsourceid]);

        int pollCount = 0;
        while(!queue.isEmpty()){

            MinDistance current = queue.poll();
            pollCount++;
            if(current.isVisited()){continue;}// if visited
            current.setVisited(true);
            trajectory.add(current);

            double distanceSoFar = vertexDistances[current.getId()].getValue();
            for(int i =0; i < graph[current.getId()].length; i++){
                int nbid = i;
                if(graph[current.getId()][nbid]!=0){
                    double weight =graph[current.getId()][nbid];
                    double newDistance =  distanceSoFar + weight;
                    if(newDistance<vertexDistances[nbid].getValue()){
                        vertexDistances[nbid].setId(nbid);
                        vertexDistances[nbid].setValue(newDistance);
                        vertexDistances[nbid].setPrevious(current);
                        if(!vertexDistances[nbid].isVisited()){
                            vertexDistances[nbid].setFound(true);
                            queue.add(vertexDistances[nbid]);
                        }// if visited
                    }//if smaller relax new dist
                }//if nonzero
            }// for nbour explore
        }// while queue

        int target = paramtargetid;
        List<Object> path  = new ArrayList<>();
        path.add(paramtargetid);
        while(vertexDistances[target]!= null && vertexDistances[target].getPrevious() != null){
            path.add(vertexDistances[target].getPrevious().getId());
            target  = vertexDistances[target].getPrevious().getId();
        }// while nonnull prev

        if (vertexDistances[paramtargetid].isFound()) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid,stackTrace);

        }else
            LOGGER.info("there exists NO path " + paramsourceid + "->" + paramtargetid,stackTrace);

        LOGGER.info("Tajectory:" + trajectory.stream().map(a->a.getId()).collect(Collectors.toList()),stackTrace);
        LOGGER.info("PopCount:" + pollCount);
        LOGGER.info("Shortest Distance:" + vertexDistances[paramtargetid].getValue(),stackTrace);

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()),stackTrace);

    }//findTrajectoryAndShortestPath


}//class Dijkstara
