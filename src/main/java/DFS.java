import java.util.*;
import java.util.stream.Collectors;

public class DFS {

    ALogger<DFS> LOGGER = new ALogger<>(DFS.class);

    public static void main(String[] args) {
        // int[][][] graph = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.DUCK_DIR_WEG);
        int[][] graph = SampleData.GraphOnlineRu.DUCK_DIR_WEG;

        System.out.println();

        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        DFS df = new DFS();
        df.findTrajectoryAndPathExists(graph, 9, 2);
        timer.getBenchmark(timer);
    }

    /**
     *
     * @param graph adj mtx
     * @param paramsourceid
     * @param paramtargetid
     */
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
        ReverseArray.reverse(reversed);


        if (pathExists[paramtargetid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        }
        LOGGER.info("Tajectory:" + trajectory);
        LOGGER.info("PopCount:" + popcount);
        LOGGER.info("Found Path:" + Arrays.stream(reversed).collect(Collectors.toList()));


    }//findTrajectoryAndPathExists


    /**
     * @param graph         as adj lists
     * @param paramsourceid
     * @param paramtargetid
     * @return
     */
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
                int weight = graph[current][i][1]; // weight no of use in DFS

                pathExists[nbid] = true;

                if (!visited[nbid]) {
                    previous[nbid] = current;
                    stack.add(nbid);
                }


            }//for
        }//while

        if (pathExists[paramtargetid]) {

            LOGGER.info("there exists path " + paramsourceid + "->" + paramtargetid);

        }

        LOGGER.info("Tajectory:" + trajectory);
        LOGGER.info("PopCount:" + pollcount);

        int target = paramtargetid;
        List<Object> path = new ArrayList<>();
        path.add(paramtargetid);

        while (previous[target] != null) {

            path.add(previous[target]);
            target = (int) previous[target];
        }

        Object[] reversed = path.toArray();
        ReverseArray.reverse(reversed);

        LOGGER.info("Path:" + Arrays.stream(reversed).collect(Collectors.toList()));


        return pathexsist;

    }//findPathExists
}//class
