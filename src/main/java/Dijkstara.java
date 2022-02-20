import java.util.*;

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
