import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstaraWithOnlyBuildInTypes {

    ALogger<DijkstaraWithOnlyBuildInTypes> LOGGER = new ALogger<>(DijkstaraWithOnlyBuildInTypes.class);

    public double getShortestPathAndDistance(int[][][] graph, int paramsourceid, int paramtargetid){
        Throwable stackTrace = new Throwable();

        boolean isValidGraph = graph == null ? false : graph.length == 0 ? false : true;
        if (!isValidGraph)
            return -1;

        boolean istargetoverlaps = paramsourceid == paramtargetid;
        if (istargetoverlaps)
            return 0;


        double[] distances = new double[graph.length] ;//track min distances
        for(int i = 0; i<distances.length; i++){distances[i]=Double.MAX_VALUE ;} // for fillint the default distances

        distances[paramsourceid] = 0 ;//distance to self
        double [] initialPointIdxWithMinDistance = {paramsourceid,0};
        List<Object> trajectory = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];

        PriorityQueue<double[]> queue = new PriorityQueue<>((a, b)->a[1]>b[1]?1:-1) ;// keeps candidate vertexes with corresponding min distances
        queue.add(initialPointIdxWithMinDistance) ;//add 1st starting point with ist min dist

        double distanceSoFar = 0 ;// distance variable
        double newDist = 0 ;//Overriding distance
        double weight = 0 ;// weight of adjacent
        double[] currentMinDist = {paramsourceid,0};


        Object[] previous = new Object[graph.length];

// start looping untill
        int pollCount = 0;

        while(!queue.isEmpty()){

            currentMinDist = queue.poll();
            if(visited[(int) currentMinDist[0]]){

                LOGGER.info("AlreadyVisited:"+currentMinDist[0]);
                continue;
            }
            pollCount++;
            trajectory.add(currentMinDist[0]);

            int currentId = (int)currentMinDist[0];
            distanceSoFar = distances[currentId];

// loop through nbours (features) for shortest dist

            for(int i = 0; i < graph[currentId].length;i++){

                int nbidx = graph[currentId][i][0];
                weight = graph[currentId][i][1];
                newDist = weight + distanceSoFar;

                if(newDist < distances[nbidx]){

                    previous[nbidx] = currentId;
                    distances[nbidx] = newDist;
                    double[] next = new double[2];
                    next[0] = nbidx;
                    next[1] = newDist;
                    //double[] next = {nbidx,newDist};
                    queue.add(next);
                }//if

            }//for

            visited[currentId] = true;



        }//while


        LOGGER.info("POLLCOUNT="+pollCount,stackTrace);

        int i = paramtargetid;
        List path = new ArrayList();
        while (previous[i] != null) {


            path.add(previous[i]);
            i = (int)previous[i];


        }


        Collections.reverse(path);
        LOGGER.info("PATH:"+path,stackTrace);
      //  LOGGER.info("TRAJECTORY:"+trajectory,stackTrace);

        //System.out.println("---- result---");
        LOGGER.info("Result:"+distances[paramtargetid],stackTrace);

        return distances[paramtargetid];

    }// getShortestPathAndDistance

    public static void main(String[] args) {

        int[][][] sample = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.WEIGHTED_GRAPH);
        int[][][] summer = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.SUMMER);
        int[][][] brown = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.BROWN);
        int[][][] rs001 = SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.RS_0001);
        int[][][] random = SampleInputs.generateRandomGraph(15000,true);

        ALogger.TIMER t = new ALogger.TIMER();

        t.startTimer();
        DijkstaraWithOnlyBuildInTypes db = new DijkstaraWithOnlyBuildInTypes();
        db.getShortestPathAndDistance(rs001, 8, 0);
        t.getBenchmark(t);

    }
}//class
