import java.util.PriorityQueue;

public class Dijkstara {


    public void mapMinimumDistanceDistribution(int[][][] graph, int startId) throws Exception {

        boolean badData = startId >= graph.length;

        if (badData) throw new Exception("Given data inconsistent"); // implement exception handler util
        int referenceId = startId;
        int cumulativeTotalDistance = 0; // u//source distance to itself;

        Integer[] currentDistances = new Integer[graph.length];
        currentDistances[referenceId] = cumulativeTotalDistance;

        // initial validation TODO

        // visited point track
        boolean[] visited = new boolean[graph.length];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> a > b ? 1 : -1);// min prior via min distance/use nulls for inf

        // for(int i = 0; i<distanceDistribution.length;i++){distanceDistribution[i] = Integer.MAX_VALUE;}; //initial distances are infinity
        priorityQueue.add(cumulativeTotalDistance);

        while (!priorityQueue.isEmpty()) {


            cumulativeTotalDistance = priorityQueue.poll(); // minimum distance for reference idå
            if (visited[referenceId]) continue;


            int[][] neighboursOf = graph[referenceId];

            int neighbourId = -1;
            for (int i = 0; i < neighboursOf.length; i++) {

                neighbourId = neighboursOf[i][0];
                int neighbourWeight = neighboursOf[i][1];
                int newDistance = cumulativeTotalDistance + neighbourWeight;

                boolean updateMinDistance = currentDistances[neighbourId] == null || newDistance < currentDistances[neighbourId];
                if (updateMinDistance) {
                    currentDistances[neighbourId] = newDistance;

                    priorityQueue.add(newDistance);
                    visited[referenceId] = true;
                    referenceId = neighbourId;
                }
            }

        }


        System.out.println("asd");
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
                {{1,6},{3,1}},
                {{0,6},{3,2},{4,2},{2,5}},
                {{1,5},{4,5}},
                {{0,1},{1,2},{4,1}},
                {{1,2},{2,5},{3,1}}
        };


        Dijkstara dijkstara = new Dijkstara();
        dijkstara.mapMinimumDistanceDistribution(info, 0);

    }
}
