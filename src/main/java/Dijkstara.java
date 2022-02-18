import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstara {

    private static class MinMinDistanceTo {

        public int id = -1;
        public MinMinDistanceTo from;
        public double minDist = Integer.MAX_VALUE;

    }

    public void doDijkstara(int [][][] paramGraph, int paramSourceId){

        MinMinDistanceTo[] results = new MinMinDistanceTo[paramGraph.length];
        for(int i = 0; i < paramGraph.length; i++){
            results[i] = new MinMinDistanceTo();
        }

        MinMinDistanceTo localExtremum = results[paramSourceId];
        localExtremum.id = paramSourceId;
        localExtremum.minDist = 0;
        GlobalExtremum.Minimum.value = 0;
        results[paramSourceId] = localExtremum;

        boolean [] visited = new boolean[paramGraph.length];

        PriorityQueue<MinMinDistanceTo> priorityQueue = new PriorityQueue<MinMinDistanceTo>((a, b)->a.minDist>b.minDist?1:-1);
        priorityQueue.add(localExtremum);


        double distanceSoFar = 0;

        while (!priorityQueue.isEmpty()){

            MinMinDistanceTo nextLocalExtremum = priorityQueue.poll();

            distanceSoFar = results[nextLocalExtremum.id].minDist;
            if(visited[nextLocalExtremum.id])continue;
            for(int currentPointPropertyCounter=0; currentPointPropertyCounter < paramGraph[nextLocalExtremum.id].length;currentPointPropertyCounter++){

                int neighbourIndex = paramGraph[nextLocalExtremum.id][currentPointPropertyCounter][0];
                double weight = paramGraph[nextLocalExtremum.id][currentPointPropertyCounter][1];
                double newDist = distanceSoFar + weight;

                if(newDist<results[neighbourIndex].minDist){
                    results[neighbourIndex].minDist = newDist;
                    results[neighbourIndex].id = neighbourIndex;
                    results[neighbourIndex].from = nextLocalExtremum;
                    priorityQueue.add(results[neighbourIndex]);

                    // GlobalExtremum.Maximum.value = GlobalExtremum.Minimum.value - weight;
                }
            }
            visited[nextLocalExtremum.id] = true;
        }

        Arrays.stream(results).forEach(a-> System.out.println(paramSourceId+"->"+a.id+":"+a.minDist));

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
                {{1,6},{3,1},{5,1},{6,5}},
                {{0,6},{3,2},{4,2},{2,5},{6,2}},
                {{1,5},{4,5}},
                {{0,1},{1,2},{4,1},{5,7}},
                {{1,2},{2,5},{3,1}},
                {{0,1},{3,7}},
                {{1,5},{1,2}}
        };



        Dijkstara d = new Dijkstara();
        d.doDijkstara(info,6);



    }
}
