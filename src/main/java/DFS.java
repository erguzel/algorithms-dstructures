import java.util.*;
import java.util.stream.Collectors;

public class DFSAgain {

    ALogger<DFSAgain> LOGGER = new ALogger<>(DFSAgain.class);

    public static void main(String[] args) {
        int[][] graph = SampleData.GraphOnlineRu.DUCK_DIR_WEG;

        System.out.println();

        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        DFSAgain bfsAgain = new DFSAgain();
        bfsAgain.findPathExists(graph, 9, 2);
        timer.getBenchmark(timer);
    }

    public boolean findPathExists(int[][] graph, int paramsourceid, int paramtargetid) {

        List<Integer> trajectory = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.add(paramsourceid);

        Object[] previous = new Object[graph.length];
        boolean[] visited = new boolean[graph.length];

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
                if (graph[current][i] != 0) {
                    int nbid = i;

                    if (nbid == paramtargetid) {

                        pathexsist = true;
                    }

                    if (!visited[nbid]) {
                        previous[nbid] = current;
                        stack.add(nbid);
                    }

                }
            }//for
        }//while

        if (pathexsist) {

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
