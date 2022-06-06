package deprecated;

import lib.util.ALogger;
import lib.util.DataUtil;
import lib.util.SampleData;

import java.util.*;

/**
 * O(E.V)
 * Single source shortest path
 * Dynnamic programming O(E.V)
 * Negative cycles detectable
 * No LinkedList required
 */
public class BellmanFordDeprecated {

    public static class NestedMinDistance{
        public int id = -1;
        public double value = Double.MAX_VALUE;
        public boolean found = false;
        public NestedMinDistance previous = null;

    }

    ALogger<BellmanFordDeprecated> LOGGER = new ALogger<>(BellmanFordDeprecated.class);

    public static void main(String[] args) {
        int[][] sample = SampleData.Csacademy.DIRECTED_CYCLED_7_WEIGHTEDNEG;

        new Thread(() -> {
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            BellmanFordDeprecated ford1 = new BellmanFordDeprecated();
            ford1.findShortestPathAndDistanceNormalMap(sample, 3, 5);
            timer.getBenchmark(timer, "Normal Map");
        }).start();

        new Thread(() -> {
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            BellmanFordDeprecated ford1 = new BellmanFordDeprecated();
            ford1.findShortestPathAndDistanceNormalArray(sample, 3, 5);
            timer.getBenchmark(timer, "Normal Array");
        }).start();


//        new Thread(() -> {
//            ALogger.TIMER timer = new ALogger.TIMER();
//            timer.startTimer();
//            deprecated.BellmanFordDeprecated ford1 = new deprecated.BellmanFordDeprecated();
//            ford1.findShortestPathAndDistanceSolidMap(sample, 3, 5);
//            timer.getBenchmark(timer, "SolidMap");
//        }).start();

//        new Thread(() -> {
//            ALogger.TIMER timer = new ALogger.TIMER();
//            timer.startTimer();
//            deprecated.BellmanFordDeprecated ford1 = new deprecated.BellmanFordDeprecated();
//            ford1.findShortestPathAndDistanceSolidArray(sample, 3, 5);
//            timer.getBenchmark(timer, "Solid Array");
//        }).start();

        new Thread(() -> {
            ALogger.TIMER timer = new ALogger.TIMER();
            timer.startTimer();
            BellmanFordDeprecated ford1 = new BellmanFordDeprecated();
            ford1.findShortestPathAndDistanceNestedArrayNoEncapsulation(sample, 3, 5);
            timer.getBenchmark(timer, "Nested Array No Enc");
        }).start();
    }

//    /**
//     * @param graph         as as custom edgelist
//     * @param paramsourceid
//     * @param paramdestid
//     */
//    public void findShortestPathAndDistanceSolidMap(int[][] graph, int paramsourceid, int paramdestid) {
//
//        Map<Object, Object> runReport = new HashMap<>();
//        Map<Integer,IResultAsset> distances = new HashMap<>();
//
////find num of vertexes
//
//        int src = 0;
//        int dest = 0;
//        double weight = 0.0;
//        int NUM_OF_VTX = 0;
//
//        for (int i = 0; i < graph.length; i++) {
//            src = graph[i][0];
//            dest = graph[i][1];
//
//            if (src >= NUM_OF_VTX) {
//                NUM_OF_VTX = src + 1;
//            }//if comp
//
//            if (dest >= NUM_OF_VTX) {
//                NUM_OF_VTX = dest + 1;
//            }//if comp2
//
//        }//for num of vtx
//
//        for (int i = 0; i < NUM_OF_VTX; i++) {
//            distances.put(i,new MinDistance());
//        }//init map
//        distances.get(paramsourceid).setId(paramsourceid);
//        distances.get(paramsourceid).setValue(0.0);
//
////for each relax
//        for (int j = 0; j < NUM_OF_VTX; j++) {
//
//// foreach edges
//            for (int i = 0; i < graph.length; i++) {
//                src = graph[i][0];
//                dest = graph[i][1];
//                weight = graph[i][2];
//                boolean needRelax = distances.get(src).getValue() != Double.MAX_VALUE && distances.get(src).getValue() + weight < distances.get(dest).getValue();
//                if (needRelax) {
//                    IResultAsset asset = distances.get(dest);
//                    asset.setId(dest);
//                    asset.setFound(true);
//                    asset.setValue(distances.get(src).getValue()+weight);
//                    asset.setPrevious(distances.get(src));
//                    distances.put(dest,asset);
//                }//if need relax
//            }//foreach edges
//        }//for each relax
//
////for check cycles negative
//        for (int i = 0; i < graph.length; i++) {
//
//            src = graph[i][0];
//            dest = graph[i][1];
//            weight = graph[i][2];
//            boolean needRelax = distances.get(src).getValue() != Double.MAX_VALUE && distances.get(src).getValue() + weight < distances.get(dest).getValue();
//            if (needRelax) {
//                System.out.println("Negaative cycles !!");
//                return;
//            }//if cycle ngt
//
//        }// for check cycles negative
//
//        IResultAsset target = distances.get(paramdestid);
//        List<Object> path = new ArrayList<>();
//
//        while (target.getPrevious() != null) {
//            path.add(target.getId());
//            target = target.getPrevious();
//        }// while previous
//        Collections.reverse(path);
//
//
//        runReport.put("path", path);
//        runReport.put("minDist", distances.get(paramdestid).getValue());
//        DataUtil.Printers.PrintReport(LOGGER, runReport);
//
//
//    }//findShortest..


    public void findShortestPathAndDistanceNestedArrayNoEncapsulation(int[][] graph, int paramsourceid, int paramdestid) {

        Map<Object, Object> runReport = new HashMap<>();

//find num of vertexes

        int src = 0;
        int dest = 0;
        double weight = 0.0;
        int NUM_OF_VTX = 0;

        for (int i = 0; i < graph.length; i++) {
            src = graph[i][0];
            dest = graph[i][1];

            if (src >= NUM_OF_VTX) {
                NUM_OF_VTX = src + 1;
            }//if comp

            if (dest >= NUM_OF_VTX) {
                NUM_OF_VTX = dest + 1;
            }//if comp2

        }//for num of vtx
        NestedMinDistance[] distances = new NestedMinDistance[NUM_OF_VTX];
        for (int i = 0; i < NUM_OF_VTX; i++) {
            distances[i] = new NestedMinDistance();
        }//init map

        distances[paramsourceid].id = paramsourceid;

        distances[paramsourceid].value = 0.0;


//for each relax
        for (int j = 0; j < NUM_OF_VTX; j++) {

// foreach edges
            for (int i = 0; i < graph.length; i++) {
                src = graph[i][0];
                dest = graph[i][1];
                weight = graph[i][2];
                boolean needRelax = distances[src].value != Double.MAX_VALUE && distances[src].value + weight < distances[dest].value;
                if (needRelax) {
                    distances[dest].value = distances[src].value + weight;
                    distances[dest].id = dest;
                    distances[dest].found = true;
                    distances[dest].previous = distances[src];
                }//if need relax
            }//foreach edges
        }//for each relax

//for check cycles negative
        for (int i = 0; i < graph.length; i++) {

            src = graph[i][0];
            dest = graph[i][1];
            weight = graph[i][2];
            boolean needRelax = distances[src].value != Double.MAX_VALUE && distances[src].value + weight < distances[dest].value;
            if (needRelax) {
                System.out.println("Negaative cycles !!");
                return;
            }//if cycle ngt

        }// for check cycles negative

        NestedMinDistance target = distances[paramdestid];
        List<Object> path = new ArrayList<>();
        while (target.previous != null) {
            path.add(target.id);
            target = target.previous;
        }// while previous
        Collections.reverse(path);


        runReport.put("path", path);
        runReport.put("minDist", distances[paramdestid].value);
        DataUtil.Printers.PrintReport(LOGGER, runReport);


    }//findShortest..

    /**
     * @param graph         as as custom edgelist
     * @param paramsourceid
     * @param paramdestid
     */
//    public void findShortestPathAndDistanceSolidArray(int[][] graph, int paramsourceid, int paramdestid) {
//
//        Map<Object, Object> runReport = new HashMap<>();
//
////find num of vertexes
//
//        int src = 0;
//        int dest = 0;
//        double weight = 0.0;
//        int NUM_OF_VTX = 0;
//
//        for (int i = 0; i < graph.length; i++) {
//            src = graph[i][0];
//            dest = graph[i][1];
//
//            if (src >= NUM_OF_VTX) {
//                NUM_OF_VTX = src + 1;
//            }//if comp
//
//            if (dest >= NUM_OF_VTX) {
//                NUM_OF_VTX = dest + 1;
//            }//if comp2
//
//        }//for num of vtx
//        MinDistance[] distances = new MinDistance[NUM_OF_VTX];
//        for (int i = 0; i < NUM_OF_VTX; i++) {
//            distances[i] = new MinDistance();
//        }//init map
//        distances[paramsourceid].setId(paramsourceid);
//        distances[paramsourceid].setValue(0.0);
//
////for each relax
//        for (int j = 0; j < NUM_OF_VTX; j++) {
//
//// foreach edges
//            for (int i = 0; i < graph.length; i++) {
//                src = graph[i][0];
//                dest = graph[i][1];
//                weight = graph[i][2];
//                boolean needRelax = distances[src].getValue() != Double.MAX_VALUE && distances[src].getValue() + weight < distances[dest].getValue();
//                if (needRelax) {
//                    distances[dest].setValue(distances[src].getValue() + weight);
//                    distances[dest].setId(dest);
//                    distances[dest].setFound(true);
//                    distances[dest].setPrevious(distances[src]);
//                }//if need relax
//            }//foreach edges
//        }//for each relax
//
////for check cycles negative
//        for (int i = 0; i < graph.length; i++) {
//
//            src = graph[i][0];
//            dest = graph[i][1];
//            weight = graph[i][2];
//            boolean needRelax = distances[src].getValue() != Double.MAX_VALUE && distances[src].getValue() + weight < distances[dest].getValue();
//            if (needRelax) {
//                System.out.println("Negaative cycles !!");
//                return;
//            }//if cycle ngt
//
//        }// for check cycles negative
//
//        IResultAsset target = distances[paramdestid];
//        List<Object> path = new ArrayList<>();
//        while (target.getPrevious() != null) {
//            path.add(target.getId());
//            target = target.getPrevious();
//        }// while previous
//        Collections.reverse(path);
//
//
//        runReport.put("path", path);
//        runReport.put("minDist", distances[paramdestid].getValue());
//        DataUtil.Printers.PrintReport(LOGGER, runReport);
//
//
//    }//findShortest..

    /**
     * @param graph         as custom edge list
     * @param paramsourceid
     * @param paramdestid
     */
    public void findShortestPathAndDistanceNormalMap(int[][] graph, int paramsourceid, int paramdestid) {

        Map<Object, Object> runReport = new HashMap<>();
        Map<Integer, Double> distances = new HashMap<>();

//find num of vertexes

        int src = 0;
        int dest = 0;
        double weight = 0.0;
        int NUM_OF_VTX = 0;

        for (int i = 0; i < graph.length; i++) {
            src = graph[i][0];
            dest = graph[i][1];

            if (src >= NUM_OF_VTX) {
                NUM_OF_VTX = src + 1;
            }//if comp

            if (dest >= NUM_OF_VTX) {
                NUM_OF_VTX = dest + 1;
            }//if comp2

        }//for num of vtx

        Object[] prev = new Object[NUM_OF_VTX];//def null
        for (int i = 0; i < NUM_OF_VTX; i++) {
            distances.put(i, Double.MAX_VALUE);
        }//init map
        distances.put(paramsourceid, 0.0);

//for each relax
        for (int j = 0; j < NUM_OF_VTX; j++) {

// foreach edges
            for (int i = 0; i < graph.length; i++) {
                src = graph[i][0];
                dest = graph[i][1];
                weight = graph[i][2];
                boolean needRelax = distances.get(src) != Double.MAX_VALUE && distances.get(src) + weight < distances.get(dest);
                if (needRelax) {
                    distances.put(dest, distances.get(src) + weight);
                    prev[dest] = src;
                }//if need relax
            }//foreach edges
        }//for each relax

//for check cycles negative
        for (int i = 0; i < graph.length; i++) {

            src = graph[i][0];
            dest = graph[i][1];
            weight = graph[i][2];
            boolean needRelax = distances.get(src) != Double.MAX_VALUE && distances.get(src) + weight < distances.get(dest);
            if (needRelax) {
                System.out.println("Negaative cycles !!");
                return;
            }//if cycle ngt

        }// for check cycles negative

        int target = paramdestid;
        List<Object> path = new ArrayList<>();
        while (prev[target] != null) {
            path.add(prev[target]);
            target = (int) prev[target];
        }// while previous
        Collections.reverse(path);


        runReport.put("path", path);
        runReport.put("minDist", distances.get(paramdestid));
        DataUtil.Printers.PrintReport(LOGGER, runReport);


    }//findShortest..

    public void findShortestPathAndDistanceNormalArray(int[][] graph, int paramsourceid, int paramdestid) {

        Map<Object, Object> runReport = new HashMap<>();

//find num of vertexes

        int src = 0;
        int dest = 0;
        double weight = 0.0;
        int NUM_OF_VTX = 0;

        for (int i = 0; i < graph.length; i++) {
            src = graph[i][0];
            dest = graph[i][1];

            if (src >= NUM_OF_VTX) {
                NUM_OF_VTX = src + 1;
            }//if comp

            if (dest >= NUM_OF_VTX) {
                NUM_OF_VTX = dest + 1;
            }//if comp2

        }//for num of vtx

        double[] distances = new double[NUM_OF_VTX];
        Object[] prev = new Object[NUM_OF_VTX];//def null
        for (int i = 0; i < NUM_OF_VTX; i++) {
            distances[i]=Double.MAX_VALUE;
        }//init map
        distances[paramsourceid] = 0.0;

//for each relax
        for (int j = 0; j < NUM_OF_VTX; j++) {

// foreach edges
            for (int i = 0; i < graph.length; i++) {
                src = graph[i][0];
                dest = graph[i][1];
                weight = graph[i][2];
                boolean needRelax = distances[src] != Double.MAX_VALUE && distances[src] + weight < distances[dest];
                if (needRelax) {
                    distances[dest] = distances[src] + weight;
                    prev[dest] = src;
                }//if need relax
            }//foreach edges
        }//for each relax

//for check cycles negative
        for (int i = 0; i < graph.length; i++) {

            src = graph[i][0];
            dest = graph[i][1];
            weight = graph[i][2];

            boolean needRelax = distances[src] != Double.MAX_VALUE && distances[src] + weight < distances[dest];

            if (needRelax) {
                System.out.println("Negaative cycles !!");
                return;
            }//if cycle ngt

        }// for check cycles negative

        int target = paramdestid;
        List<Object> path = new ArrayList<>();
        while (prev[target] != null) {
            path.add(prev[target]);
            target = (int) prev[target];
        }// while previous
        Collections.reverse(path);


        runReport.put("path", path);
        runReport.put("minDist", distances[paramdestid]);
        DataUtil.Printers.PrintReport(LOGGER, runReport);


    }//findShortest..
}//class






