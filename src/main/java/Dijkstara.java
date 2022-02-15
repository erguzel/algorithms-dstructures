import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstara {

    private String asd ="";

    //represents target ids with its distance to the source
    private static class TargetWithDistance{
        private int targetId ;
        private int minDistance = Integer.MAX_VALUE;
        public TargetWithDistance(int targetId, int targetDistance){
            this.targetId = targetId;
            this.minDistance = targetDistance;
        }
    }

    public void execute(int[][][] graph, int sourceId, int targetId) {

        // initial validation TODO

        // visited point track
        boolean[] visited = new boolean[graph.length];
        TargetWithDistance initial = new TargetWithDistance(sourceId,0); /// source distance to itself
        //visited[sourceId] = true;
        PriorityQueue<TargetWithDistance> priorityQueue = new PriorityQueue<>((a, b) -> a.minDistance >b.minDistance ?-1:1);//TODO:Check comparasion logic priority min distance to source
        priorityQueue.add(initial);
        List<TargetWithDistance> result = new ArrayList<>();
        result.add(initial);

        int res = 0;
        int [][] adjacencyPointsWithWeightArray = null;
        int weight = 0;
        int adjacentId = 0;
        while (!priorityQueue.isEmpty()) {

            TargetWithDistance currentTarget = priorityQueue.poll();

            if(visited[currentTarget.targetId])continue;

            adjacencyPointsWithWeightArray = graph[currentTarget.targetId];

            res = currentTarget.minDistance;

            for (int i = 0; i < adjacencyPointsWithWeightArray.length; i++) {

                weight = adjacencyPointsWithWeightArray[i][1];
                adjacentId = adjacencyPointsWithWeightArray[i][0];

                TargetWithDistance adjacencyTarget = new TargetWithDistance(adjacentId,Integer.MAX_VALUE);
                int distanceToAdjacent = currentTarget.minDistance + weight;

                if(distanceToAdjacent < adjacencyTarget.minDistance){

                    adjacencyTarget.minDistance = distanceToAdjacent;
                    priorityQueue.add(adjacencyTarget);
                    if(!visited[currentTarget.targetId]){
                        visited[currentTarget.targetId] = true;
                        result.add(adjacencyTarget);
                    }
                }
            }
        }

        System.out.println("asd");
    }


    public static void main(String[] args) {

        int[][][] input = {
                {{1, 1}},
                {{0, 1}, {3, 6}},
                {{3, 4}, {4, 3}, {5, 2}},
                {{1, 6}, {2, 4}, {4, 1}},
                {{3, 1}, {2, 3}},
                {{2, 2}}
        };


        Dijkstara dijkstara = new Dijkstara();
        dijkstara.execute(input, 0, 5);

    }
}
