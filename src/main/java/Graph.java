import java.util.*;

/**
 * Represents any kind of graph
 * Has search and path utilities
 */
public class Graph {

    public static void main(String[] args) {

        int[][][] info = {
                {{1,6},{3,1}},
                {{0,6},{3,2},{4,2},{2,5}},
                {{1,5},{4,5}},
                {{0,1},{1,2},{4,1}},
                {{1,2},{2,5},{3,1}}
        };

        int[][] nonweighted = {{1,3,2},{0},{0,4,5},{0,5},{2},{2,3,6},{5}};


        Graph graph = new Graph();
        graph.initialize(info);

        int [] sd = graph.sortestPathByDijkstara(0,5);

        Arrays.stream(sd).forEach(a-> System.out.println(a));
    }

    //Represents whole graph
    private Map<Integer, Point> GRAPHSET = new HashMap();

    /**
     * Represents a vertex in a graph
     */
    private static class Point implements Comparable<Point> {

        //TODO null check encapsulate

        private int id = Integer.MIN_VALUE;
        private String name = "null";
        private boolean isVisited = false;
        private List<Edge> edgeList = new ArrayList<>();
        private Point previousVertex = null;

        private Map<String,Double> facilityDistances = new HashMap<>();

        private double minMeasure = Integer.MAX_VALUE;

        public Point(int id, String name) {
            this.id = id;
            this.name = name;

        }

        public double gettotalDistance(){

            return this.facilityDistances.values()
                    .stream().mapToDouble(a->a)
                    .sum();
        }

        @Override
        public String toString() {
            return this.name;

        }

        @Override
        public boolean equals(Object o) {

            return (o instanceof Graph.Point && this.id == ((Point) o).id);
        }


        @Override
        public int compareTo(Point o) {
            return Double.compare(this.minMeasure, o.minMeasure);
        }
    }

    /**
     * Represents an edge of 2 points
     */
    private static class Edge {

        private Graph.Point sourcePoint;
        private Graph.Point targetPoint;
        private double weight = 0;

        public Edge(Graph.Point sourcePoint, Graph.Point targetPoint, double weight) {
            this.sourcePoint = sourcePoint;
            this.targetPoint = targetPoint;
            this.weight = weight;
        }

        @Override
        public String toString() {

            return this.sourcePoint.name+"->"+this.targetPoint.name+"::"+this.weight;

        }


    }
    public void initialize(int[][] graph) {

        for( int i = 0; i < graph.length; i ++){

            Point pt = new Point(i,Integer.toString(i));

            GRAPHSET.put(i,pt);
        }

        for (int i = 0; i < graph.length;i++){

            Point inMap = GRAPHSET.get(i);

            for (int j = 0; j < graph[i].length; j++){

                Point to = GRAPHSET.get(graph[i][j]);

                Edge ed = new Edge(inMap,to,1);

                inMap.edgeList.add(ed);

            }
            GRAPHSET.put(i,inMap);


        }

        System.out.println("dir");
    }

    public void initializeOneLoop(int[][] graph) {

        Point inMap = null;

        for (int i = 0; i < graph.length;i++){

            int inMapIndex = i;
            if(!GRAPHSET.containsKey(inMapIndex)){

                inMap = new Point(inMapIndex,Integer.toString(inMapIndex));
                GRAPHSET.put(inMapIndex,inMap);
            }

            inMap = GRAPHSET.get(inMapIndex);

            Point to = null;
            for (int j = 0; j < graph[i].length; j++){

                int refPointIndex = graph[i][j];

                if(!GRAPHSET.containsKey(refPointIndex)){

                    to = new Point(refPointIndex,Integer.toString(refPointIndex));
                    GRAPHSET.put(refPointIndex,to);
                }

                to = GRAPHSET.get(refPointIndex);

                Edge ed = new Edge(inMap,to,1);

                inMap.edgeList.add(ed);

            }

            GRAPHSET.put(inMapIndex,inMap);


        }

        System.out.println("dir");
    }

    public void initialize(int[][][] graph) {
        //TODO at the end;
        //Fill the points to the graph in reference relation with each other

        char letter = 'A';

        for (int i = 0; i < graph.length; i++) {

            Point pt = new Point(i, String.valueOf( (char) (letter + i)));
            GRAPHSET.put(i, pt);
        }

        double weight = 0;
        int targetId = -1;
        Point to = null;
        Edge ed =null;
        for (int i = 0; i < graph.length; i++) {

            Point inMap = GRAPHSET.get(i);

            for (int j = 0; j < graph[i].length; j++) {

                targetId = graph[i][j][0];
                weight = graph[i][j][1];
                to = GRAPHSET.get(targetId);
                ed = new Edge(inMap,to,weight);
                inMap.edgeList.add(ed);
                GRAPHSET.put(i, inMap);

            }
        }

        System.out.println("Dur");
    }

    //    public void initializeBuildingsTest(Object[][][] graph){
//
//        int pointId = -1;
//        int targetPointId = -1;
//        Object[] facilities = null;
//        Object[] adjacentIndices = null;
//
//        for(int i = 0; i < graph.length; i++){
//
//            pointId = i;
//            facilities = graph[i][1];
//            adjacentIndices = graph[i][0];
//            Point inMap = new Point(pointId,Integer.toString(pointId));
//            //Add facilities
//            for(int k = 0 ; k < facilities.length; k++){
//                inMap.facilities.add(facilities[k].toString());
//            }
//
//            for (int k = 0; k < adjacentIndices.length; k++){
//
//                Point to = null;
//                targetPointId = (int)adjacentIndices[k];
//                if(!GRAPHSET.containsKey(targetPointId)){
//                    to = new Point(targetPointId,Integer.toString(targetPointId));
//                }else {
//                    to = GRAPHSET.get(targetPointId);
//                }
//                Edge ed = new Edge(inMap,to,0);
//
//                inMap.edgeList.add(ed);
//
//            }
//
//            GRAPHSET.put(pointId,inMap);
//        }
//
//        System.out.println("asd");
//    }
//
    public void initializeBuildings(Object[][][] graph){

        int pointId = -1;
        int targetPointId = -1;
        Object[] facilities = null;
        Object[] adjacentIndices = null;

        for(int i = 0; i < graph.length; i++){

            pointId = i;
            facilities = graph[i][1];
            adjacentIndices = graph[i][0];
            Point point = new Point(pointId,Integer.toString(pointId));
            //Add facilities
            for(int k = 0 ; k < facilities.length; k++){
                point.facilityDistances.put(facilities[k].toString(),0.0);
            }

            GRAPHSET.put(pointId,point);
        }
        for(int i = 0; i < graph.length;i++){

            Point inMap = GRAPHSET.get(i);

            for(int j = 0; j < graph[i][0].length; j++){

                targetPointId = (int)graph[i][0][j];
                Point to = GRAPHSET.get(targetPointId);
                Edge ed =  new Edge(inMap,to,0);
                inMap.edgeList.add(ed);
            }

            GRAPHSET.put(i,inMap);
        }

        System.out.println("Jsadl;k");
    }
    /**
     * Computes the shortest path from given node to target node
     *
     * @param sourceId source id
     * @param targetId target id
     * @return shortes path array
     */
    public int[] sortestPathByDijkstara(int sourceId, int targetId) {
        // 1st get the starting node
        Point startPoint = GRAPHSET.get(sourceId);
        startPoint.minMeasure = 0; // min value
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(startPoint);
        double minDistance = 0;

        Point targetPointOfCurrentEdge = null;
        // visit all candidate points
        while (!priorityQueue.isEmpty()) {

            Point currentPoint = priorityQueue.poll();

            for (Edge edge : currentPoint.edgeList) {

                targetPointOfCurrentEdge = edge.targetPoint;
                minDistance = currentPoint.minMeasure + edge.weight;

                if (minDistance < targetPointOfCurrentEdge.minMeasure) {

                    targetPointOfCurrentEdge.minMeasure = minDistance;
                    targetPointOfCurrentEdge.previousVertex = currentPoint;
                    priorityQueue.remove(currentPoint);
                    priorityQueue.add(targetPointOfCurrentEdge);

                }

            }
        }

        Point targetVertex = GRAPHSET.get(targetId);

        List<Point> path = new ArrayList<>();

        for (Point point = targetVertex; point != null; point = point.previousVertex) {

            path.add(point);
        }

        Collections.reverse(path);
        System.out.println("lengtg:"+path.get(path.size()-1).minMeasure);
        return path.stream().mapToInt(a -> a.id).toArray();
    }

    public int closesPathByDijkstara(String[] preferedFacilities) {
        // 1st get the starting node
        Point startPoint = GRAPHSET.get(0);
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        while (true){

            Point currentPoint = queue.poll();


        }

        //return -1;
    }

    /**
     * Traverses the graph with BFS algorithm
     * @param startIndex
     * @return graph traverse array
     */
    public Object[] traverseGraphBFS(int startIndex){

        // get starting point from map

        //candidate point queue
        ArrayDeque<Point> arrayDeque = new ArrayDeque<>();
        List<String> resultList = new ArrayList<>();
        Point pt = GRAPHSET.get(startIndex);
        pt.isVisited = true;

        arrayDeque.add(pt);

        while (!arrayDeque.isEmpty()){

            Point currentPoint = arrayDeque.poll();
            resultList.add(currentPoint.name);

            for(Edge ed : currentPoint.edgeList){

                Point targetPt = ed.targetPoint;

                if(!targetPt.isVisited){
                    targetPt.isVisited = true;
                    arrayDeque.add(targetPt);
                }

            }
        }

        return resultList.stream().toArray();

    }

    public Object[] traverseGraphDFS(int startIndex){

        // get starting point from map

        //candidate point queue
        Stack<Point> arrayDeque = new Stack<>();
        List<String> resultList = new ArrayList<>();
        Point pt = GRAPHSET.get(startIndex);
        pt.isVisited = true;

        arrayDeque.add(pt);

        while (!arrayDeque.isEmpty()){

            Point currentPoint = arrayDeque.pop();
            resultList.add(currentPoint.name);

            for(Edge ed : currentPoint.edgeList){

                Point targetPt = ed.targetPoint;

                if(!targetPt.isVisited){
                    targetPt.isVisited = true;
                    arrayDeque.add(targetPt);
                }

            }
        }

        return resultList.stream().toArray();

    }


}