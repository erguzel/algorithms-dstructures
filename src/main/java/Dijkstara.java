import java.util.*;

public class Dijkstara {

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

        long startTime = System.currentTimeMillis();


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
       // Queue<MinMinDistanceTo> priorityQueue = new LinkedList<>();
        Queue<MinMinDistanceTo> priorityQueue = new PriorityQueue<MinMinDistanceTo>((a, b) -> a.minDist > b.minDist ? 1 : -1);
        MinMinDistanceTo localExtremum = results[paramSourceId];
        localExtremum.id = paramSourceId;
        localExtremum.minDist = 0;
        priorityQueue.add(localExtremum);
        results[paramSourceId] = localExtremum;
        visited[paramSourceId] = true;


        double distanceSoFar = 0;

        int popCount = 0;

        while (!priorityQueue.isEmpty()) {

            MinMinDistanceTo nextLocalExtremum = priorityQueue.poll();

//            if (visited[nextLocalExtremum.id]) continue;
//            visited[nextLocalExtremum.id] = true;

          //  System.out.println("Traversing->" + nextLocalExtremum.id);
            popCount++;

            distanceSoFar = results[nextLocalExtremum.id].minDist;

            for (int i = 0; i < paramGraph[nextLocalExtremum.id].length; i++) {

                int neighbourIndex = paramGraph[nextLocalExtremum.id][i][0];
            //    if(visited[neighbourIndex])continue;

                double weight = paramGraph[nextLocalExtremum.id][i][1];
                double newDist = distanceSoFar + weight;
                if (newDist < results[neighbourIndex].minDist) {
                    results[neighbourIndex].minDist = newDist;
                    results[neighbourIndex].id = neighbourIndex;
                    results[neighbourIndex].previous = nextLocalExtremum.id;
                    if(!visited[neighbourIndex]){
                        visited[neighbourIndex] = true;
                        priorityQueue.add(results[neighbourIndex]);
                    }
                }
            }

        }
        System.out.println("PollCount: " + popCount);

        int i = paramDestId;
        List path = new ArrayList();
        while (results[i].previous != -1) {



                path.add(results[i].previous);
                i = results[i].previous;


        }


//        Arrays.stream(results).forEach(a -> System.out.println(paramSourceId + "->" + a.id + ":" + a.minDist));


        System.out.println("------------");
        Collections.reverse(path);
        System.out.println(path);

        System.out.println("---- result---");

        System.out.println(results[paramDestId].minDist);

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");

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
        double res = d.getShortestPath(SampleInputs.get18weightedShortestPath(), 0, 9);


    }
}
