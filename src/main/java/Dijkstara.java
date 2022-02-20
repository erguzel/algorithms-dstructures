import java.util.*;

public class Dijkstara {

    ALogger<Dijkstara> LOGGER = new ALogger<>(Dijkstara.class);

    private static class MinMinDistanceTo {

      public int id = -1;
        public MinMinDistanceTo from;
        public double minDist = Integer.MAX_VALUE;
        public int previous = -1;
    }


    public double getShortestDistance(int[][][] paramGraph, int paramSourceId, int paramDestId) {

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

        MinMinDistanceTo localExtremum = results[paramSourceId];
        localExtremum.id = paramSourceId;
        localExtremum.minDist = 0;
        results[paramSourceId] = localExtremum;


        boolean[] visited = new boolean[paramGraph.length];


        PriorityQueue<MinMinDistanceTo> priorityQueue = new PriorityQueue<MinMinDistanceTo>((a, b) -> a.minDist > b.minDist ? 1 : -1);
        priorityQueue.add(localExtremum);


        double distanceSoFar = 0;


        while (!priorityQueue.isEmpty()) {

            MinMinDistanceTo nextLocalExtremum = priorityQueue.poll();

            distanceSoFar = results[nextLocalExtremum.id].minDist;


            if (visited[nextLocalExtremum.id]) continue;
            for (int currentPointPropertyCounter = 0; currentPointPropertyCounter < paramGraph[nextLocalExtremum.id].length; currentPointPropertyCounter++) {

                int neighbourIndex = paramGraph[nextLocalExtremum.id][currentPointPropertyCounter][0];
                double weight = paramGraph[nextLocalExtremum.id][currentPointPropertyCounter][1];
                double newDist = distanceSoFar + weight;

                if (newDist < results[neighbourIndex].minDist) {
                    results[neighbourIndex].minDist = newDist;
                    results[neighbourIndex].id = neighbourIndex;
                    priorityQueue.add(results[neighbourIndex]);

                }
            }
            visited[nextLocalExtremum.id] = true;
        }

        Arrays.stream(results).forEach(a -> System.out.println(paramSourceId + "->" + a.id + ":" + a.minDist));


        System.out.println("------------");

        return results[paramDestId].minDist;

    }

    public double getShortestPath(int[][][] paramGraph, int paramSourceId, int paramDestId) {

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


        boolean[] visited = new boolean[paramGraph.length];
        Queue<MinMinDistanceTo> priorityQueue = new PriorityQueue<MinMinDistanceTo>((a, b) -> a.minDist > b.minDist ? 1 : -1);
        MinMinDistanceTo startPoint = results[paramSourceId];
        startPoint.id = paramSourceId;
        startPoint.minDist = 0;
        priorityQueue.add(startPoint);
        results[paramSourceId] = startPoint;
        List<String> trajectory = new ArrayList<>();


        double currentPointdistance = 0;

        int popCount = 0;

        while (!priorityQueue.isEmpty()) {

            MinMinDistanceTo currentPoint = priorityQueue.poll();

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
//                    if (!visited[neighbourIndex]) {
//                        visited[neighbourIndex] = true;
//                        priorityQueue.add(results[neighbourIndex]);
//                    }
                }
            }

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
        LOGGER.info("TRAJECTORY:"+trajectory,stackTrace);

        //System.out.println("---- result---");
        LOGGER.info("Result:"+results[paramDestId].minDist,stackTrace);

        return results[paramDestId].minDist;

    }


    public static void main(String[] args) throws Exception {


        int[][][] input = {
                {{1, 1}},
                {{0, 1}, {3, 6}},
                {{3, 4}, {4, 3}, {5, 2}},
                {{1, 6}, {2, 4}, {4, 1}},
                {{3, 1}, {2, 3}},
                {{2, 2}}
        };

        int[][][] info = {
                {{1, 6}, {3, 1}, {5, 1}, {6, 5}},
                {{0, 6}, {3, 2}, {4, 2}, {2, 5}, {6, 2}},
                {{1, 5}, {4, 5}},
                {{0, 1}, {1, 2}, {4, 1}, {5, 7}},
                {{1, 2}, {2, 5}, {3, 1}},
                {{0, 1}, {3, 7}},
                {{1, 5}, {1, 2}}
        };



        Dijkstara d = new Dijkstara();
        double res = d.getShortestPath(SampleInputs.GraphOnlineRu.convertToAdjacencyList(SampleInputs.GraphOnlineRu.GO0001_ADJMTX), 3, 7);


    }
}
