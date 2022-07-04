package deprecated;

import java.util.*;
import java.util.stream.Collectors;

public class DFSDeprecated {
//
//    lib.util.ALogger<DFSDeprecated> LOGGER = new lib.util.ALogger<>(DFSDeprecated.class);

    public static void main(String[] args) {
        // int[][][] graph = lib.util.SampleData.convertToAdjacencyList(lib.util.SampleData.GraphOnlineRu.DUCK_DIR_WEG);
       // int[][] graph = lib.util.SampleData.GraphOnlineRu.DIRECTED_CYCLED1;

        DFSDeprecated dfs = new DFSDeprecated();
      //  dfs.findTrajectoryAndPathExists(graph,4,5);
        //System.out.println(res);
//        int[] track1 = dfs.findTopSortRecursive(graph);
//        System.out.println(Arrays.stream(track1).boxed().collect(Collectors.toList()));
//        System.out.println();
//
//        lib.util.ALogger.TIMER timer = new lib.util.ALogger.TIMER();
//        timer.startTimer();
//        graph.DFS df = new graph.DFS();
//        df.findTrajectoryAndPathExists(graph, 9, 2);
//        timer.getBenchmark(timer);
    }

    public int count= 0;


   // adjmtx
    public void findTrajectoryAndPathExists(int[][] graph, int paramsourceid, int paramtargetid) {

        boolean[] visited = new boolean[graph.length];
        boolean[] pathExists = new boolean[graph.length];
        Object[] previous = new Object[graph.length];

        Stack<Integer> stack = new Stack<>();
        stack.add(paramsourceid);
        List<Integer> trajectory = new ArrayList<>();
        int popcount = 0;
        while (!stack.isEmpty()) {
            int currentPoint = stack.pop();
            popcount++;
            if (visited[currentPoint]) {
                continue;
            }//if visited
            trajectory.add(currentPoint);
            visited[currentPoint] = true;
// explore nbours
            for (int i = 0; i < graph[currentPoint].length; i++) {
                if (graph[currentPoint][i] != 0) {
                    int nbidx = i;
                    pathExists[nbidx] = true;
                    if (!visited[nbidx]) {
                        previous[nbidx] = currentPoint;
                        stack.add(nbidx);
                        //visited[nbidx] = true;
                    }// if visited
                }//if
            }//for point
        }//while stack isempty

        int tgt = paramtargetid;
        List<Object> pathFound = new ArrayList<>();
        pathFound.add(paramtargetid);

        while (previous[tgt] != null) {
            pathFound.add(previous[tgt]);
            tgt = (int) previous[tgt];
        }//while

        Object[] reversed = pathFound.toArray();
   //     sort.ReverseArray.reverse(reversed);


        if (pathExists[paramtargetid]) {
//
   //         LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);
//
        }
   //     LOGGER.info("Tajectory:" + trajectory);
  //     LOGGER.info("PopCount:" + popcount);
   //     LOGGER.info("Found Path:" + Arrays.stream(reversed).collect(Collectors.toList()));


    }//findTrajectoryAndPathExists
    //adjlist
    public boolean findTrajectoryAndPathExists(int[][][] graph, int paramsourceid, int paramtargetid) {

        List<Integer> trajectory = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(paramsourceid);

        Object[] previous = new Object[graph.length];
        boolean[] visited = new boolean[graph.length];
        boolean[] pathExists = new boolean[graph.length];

        int pollcount = 0;
        boolean pathexsist = false;

        while (!stack.isEmpty()) {

            int current = stack.pop();
            pollcount++;

            if (visited[current]) {
                continue;
            }
            trajectory.add(current);
            visited[current] = true;

            for (int i = 0; i < graph[current].length; i++) {

                int nbid = graph[current][i][0];
                int weight = graph[current][i][1]; // weight no of use in graph.DFS

                pathExists[nbid] = true;

                if (!visited[nbid]) {
                    previous[nbid] = current;
                    stack.add(nbid);
                }


            }//for
        }//while

        if (pathExists[paramtargetid]) {

       //     LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        }

     //   LOGGER.info("Tajectory:" + trajectory);
    //    LOGGER.info("PopCount:" + pollcount);

        int target = paramtargetid;
        List<Object> path = new ArrayList<>();
        path.add(paramtargetid);

        while (previous[target] != null) {

            path.add(previous[target]);
            target = (int) previous[target];
        }

        Object[] reversed = path.toArray();
   //     sort.ReverseArray.reverse(reversed);

  //      LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));
//

        return pathexsist;

    }//findPathExists


    public boolean pathFound(int source, int target, int[][] adjmtx) {
        int startVertex = source;
        boolean[] visited = new boolean[adjmtx.length];
        visited[startVertex] = true;
        return pathFoundUtil(startVertex, target, adjmtx, visited);
    }// path found

    private boolean pathFoundUtil(int vtx, int data, int[][] mtx, boolean[] visited) {
        if (vtx == data) return true;

        for (int i = 0; i < mtx[vtx].length; i++) {
            if (mtx[vtx][i] == 0) continue;//no edge
            if (!visited[i]) {
                visited[i] = true;
                if (pathFoundUtil(i, data, mtx, visited)) {
                    return true;
                }//if visited
            }//for nbours
        }
        return false;
    }//dfs util

}//class
