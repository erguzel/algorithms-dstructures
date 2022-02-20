import java.util.*;

public class BreadthFirstSearch{

    ALogger<BreadthFirstSearch> LOGGER = new ALogger<>(BreadthFirstSearch.class);

    public int findShortestDistances(int[][][] graph, int paramsourceid, int paramdestid){

        Throwable stackTrace = new Throwable();

        int[] distances = new int [graph.length];
        Object[] previous = new Object [graph.length] ;//defaults are null
        for(int i = 0;  i < distances.length; i++){distances[i]=Integer.MAX_VALUE;}//for fill defaults
        Stack<Integer> queue = new Stack<>();
        //Queue<Integer> queue = new LinkedList<>();
        distances[paramsourceid] = 0 ;//distance to self
        queue.add (paramsourceid);
        List<Object> trajectory = new ArrayList<>();
        boolean [] visited = new boolean[graph.length];

        int pollCount = 0;
        while(!queue.isEmpty()){
            int currentVertex = queue.pop();
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

        int[][][] sample = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.RS_0001);

        ALogger.TIMER t = new ALogger.TIMER();
        t.startTimer();

        BreadthFirstSearch d = new BreadthFirstSearch();
        d.findShortestDistances(sample, 0, 9);

        t.getBenchmark(t);
    }
}//class
