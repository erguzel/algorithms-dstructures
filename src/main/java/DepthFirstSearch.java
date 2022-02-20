import java.util.*;
import java.util.stream.Collectors;

public class DepthFirstSearch {


    public void traverseGraph(int[][] points, int startPoint) {

        // initial validation TODO

        // visited point track
        boolean[] visited = new boolean[points.length];
        // hold candidates
        Stack<Integer> candidatePoints = new Stack<>();
        candidatePoints.add(startPoint);
        visited[startPoint] = true;// mark start point visited

        int currentPoint = 0;
        int[] adjacentPoints = null;
        int nextAdjacent = 0;

        while (!candidatePoints.isEmpty()) {

            currentPoint = candidatePoints.pop();
            System.out.println("Traversing>" + currentPoint);

            adjacentPoints = points[currentPoint];

            for (int i = 0; i < adjacentPoints.length; i++) {

                nextAdjacent = adjacentPoints[i];
                boolean nextAdjacentVisisted = visited[nextAdjacent];
                if (!nextAdjacentVisisted) {

                    visited[nextAdjacent] = true;

                    candidatePoints.push(nextAdjacent);

                }

            }
        }
    }

    public void findAllShortestDistancesToSource(int[][] points, int startPoint, int endPoint) {

        // initial validation TODO

        // visited point track
        boolean[] visited = new boolean[points.length];
        // hold candidates
        PriorityQueue<Integer> candidatePoints = new PriorityQueue<>() {
        };
        candidatePoints.add(startPoint);
        visited[startPoint] = true;// mark start point visited

        int[] minDistanceArray = new int[points.length];
        Object[] previous = new Object[points.length];
        previous[startPoint] = null;

        for (int i = 0; i < minDistanceArray.length; i++) {
            minDistanceArray[i] = Integer.MAX_VALUE;
        }
        ;

        minDistanceArray[startPoint] = 0;

        int currentPoint = 0;
        int[] adjacentPoints = null;
        int nextAdjacent = 0;
        int distanceSoFar = 0;

        int popCount = 0;
        while (!candidatePoints.isEmpty()) {

            currentPoint = candidatePoints.poll();
            popCount++;

            System.out.println("Traversing>" + currentPoint);

            adjacentPoints = points[currentPoint];

            distanceSoFar = minDistanceArray[currentPoint];

            for (int i = 0; i < adjacentPoints.length; i++) {

                nextAdjacent = adjacentPoints[i];

                int newdist = distanceSoFar + 1;

                if (newdist < minDistanceArray[nextAdjacent]) {
                    minDistanceArray[nextAdjacent] = newdist;
                    previous[nextAdjacent] = currentPoint;
                }

                boolean nextAdjacentVisisted = visited[nextAdjacent];
                if (!nextAdjacentVisisted) {

                    visited[nextAdjacent] = true;

                    candidatePoints.add(nextAdjacent);

                }

            }
        }
        System.out.println("--->PopCount::" + popCount);

        int idx = endPoint;
        List path = new ArrayList();
        path.add(endPoint);
        for (int i = endPoint; i != startPoint && previous[i] != null; i = (int) previous[i]) {

            path.add(previous[i]);

        }

        Collections.reverse(path);
        System.out.println("==>Path :" + path);


        Arrays.stream(minDistanceArray).forEach(a -> System.out.println("mindistanceFromSource->" + a));
        System.out.println("stop");


    }

    public void findAllShortestDistancesToSource(int[][][] points, int startPoint, int endPoint) {
        long startTime = System.currentTimeMillis();
        // initial validation TODO

        // visited point track
        boolean[] visited = new boolean[points.length];
        // hold candidates
        Stack<Integer> stack = new Stack<>();
        stack.add(startPoint);
        visited[startPoint] = true;// mark start point visited
        int[] distances = new int[points.length];
        Object[] previous = new Object[points.length];
        previous[startPoint] = null;

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        distances[startPoint] = 0;

        int currentPoint = 0;
        int[][] adjacentPointsFeatures = null;
        int nextAdjacent = 0;
        int distanceSoFar = 0;

        int popCount = 0;
        while (!stack.isEmpty()) {

            currentPoint = stack.pop();
            popCount++;
            // System.out.println("Traversing>" + currentPoint);

            distanceSoFar = distances[currentPoint];
            adjacentPointsFeatures = points[currentPoint];

            for (int i = 0; i < adjacentPointsFeatures.length; i++) {

                nextAdjacent = adjacentPointsFeatures[i][0];
                //int weight = adjacentPointsFeatures[i][1];
                int weight = 1;
                int newdist = distanceSoFar + weight;

                if (newdist < distances[nextAdjacent]) {
                    distances[nextAdjacent] = newdist;
                    previous[nextAdjacent] = currentPoint;
                    if (!visited[nextAdjacent]) {

                        visited[nextAdjacent] = true;
                        stack.add(nextAdjacent);
                    }

                }

            }
        }
        System.out.println("--->PopCount::" + popCount);

//        int idx = endPoint;
//        List path = new ArrayList();
//        path.add(endPoint);
//        for (int i = endPoint; i != startPoint && previous[i] != null; i = (int) previous[i]) {
//
//            path.add(previous[i]);
//
//        }

        int i = endPoint;
        List path = new ArrayList();

        while (previous[i] != null) {


            path.add(previous[i]);
            i = (int) previous[i];
        }

        Collections.reverse(path);
        System.out.println("==>Path :" + path);

//        for(int i = 0; i < distances.length; i++){
//
//            System.out.println(startPoint+"->"+i+":"+distances[i]);
//
//        }
        System.out.println("---- result---");
        System.out.println(distances[endPoint]);
        System.out.println("stop");
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");


    }

    public static void main(String[] args) {

//        int [][] input = {
//                {1},
//                {0,3},
//                {3,4,5},
//                {1,2,4},
//                {2,3},
//                {2}
//        };

        int[][] input = {
                {5, 3, 6, 1},
                {6, 0, 3, 4, 2},
                {4, 1},
                {5, 0, 1, 4},
                {2, 1, 3},
                {0, 3},
                {0, 1}
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

        DepthFirstSearch debthFirstSearch = new DepthFirstSearch();
        debthFirstSearch.findAllShortestDistancesToSource(SampleInputs.get18weightedShortestPath(), 9, 0);

    }
}
