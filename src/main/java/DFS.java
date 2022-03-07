import java.util.*;
import java.util.stream.Collectors;

public class DFS {

    ALogger<DFS> LOGGER = new ALogger<>(DFS.class);

    public static void main(String[] args) {
        // int[][][] graph = SampleData.convertToAdjacencyList(SampleData.GraphOnlineRu.DUCK_DIR_WEG);
        int[][] graph = SampleData.GraphOnlineRu.TOPSORT1;

        DFS dfs = new DFS();
       int [] track =  dfs.findTopSortNonRecursive(graph);
        System.out.println(Arrays.stream(track).boxed().collect(Collectors.toList()));
        int [] track1 =  dfs.findTopSortRecursive(graph);
        System.out.println(Arrays.stream(track1).boxed().collect(Collectors.toList()));
//        System.out.println();
//
//        ALogger.TIMER timer = new ALogger.TIMER();
//        timer.startTimer();
//        DFS df = new DFS();
//        df.findTrajectoryAndPathExists(graph, 9, 2);
//        timer.getBenchmark(timer);
    }

    public int[] findTopSortNonRecursive(int[][] graph){

        Stack<Integer> callstack = new Stack<Integer>();//for next dfs call
        Queue<Integer> path = new LinkedList<>();// for path
        boolean[] visited = new boolean[graph.length];//for visited track
        int[] cache = new int[graph.length]; // for tracking unconnected degree of vertexes

// fill cache with number of connections whicht towards them

        for(int i =0; i<graph.length; i++){
            for(int j =0; j<graph.length;j++){
                if(graph[j][i] != 0){
                    cache[i]++;
                }// means there is a connection to that vertex
            }//foreach neightbour
        }//f0r each vertex

// add vertexes which has no dependen cies to call stack

        for(int i = 0; i < cache.length;i++){
            if(cache[i] ==0)callstack.add(i);
        }// fill stack

// traverse from nondependent vertexes

        while(!callstack.isEmpty()){
            int current = callstack.pop();//add to path queue
            path.add(current);
            if(visited[current])continue;
            visited[current] = true;

// explore neighbours (not a visit !)
            for(int i = 0; i < graph[current].length; i++){
                if(graph[current][i]==0)continue;
                if(!visited[i]){
                    if(cache[i]>0){
                        cache[i]--;//reduce 1 for priority
                    }
                    if(cache[i] == 0){
                        callstack.add(i);
                    }//add callstack if reaches priority
                }//if not visited
            }//for nbours
        }//traverse

        return path.stream().mapToInt(a->a).toArray();
    }//find topsortnonrecursinve

    public int[] findTopSortRecursive(int[][] graph){

        boolean[] visited = new boolean[graph.length];// track if visited
        Queue<Integer> path = new LinkedList<>();// for path
// for each vertex, we call dfs if necessary
        for(int i = 0; i<graph.length; i++){
            int current = i;
            if(!visited[current]){
                findTopSortRecursiveUtil(graph,current,visited,path);
            }//call dfs
        }//for each vertex

        Integer[] as = path.toArray(new Integer[0]);
        ReverseArray.reverse(as);

        return  Arrays.stream(as).mapToInt(a->a).toArray();
    }// find topsort recursive

    private void findTopSortRecursiveUtil(int[][] graph, int current, boolean[] visited, Queue<Integer> path){
        visited[current] = true;
// explore nbours
        for(int i =0; i < graph[current].length;i++){
            if(graph[current][i]==0)continue;
            int currentnbour = i;
            if(!visited[i]){
                findTopSortRecursiveUtil(graph,currentnbour,visited,path);
            }//call deeper stack
        }//for nbours

//add call back value to path
        path.add(current);
    }// topsort recursive util
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


    public boolean pathFound(int source, int target, int[][] adjmtx) {
        int startVertex = source;
        boolean[] visited = new boolean[adjmtx.length];
        visited[startVertex] = true;
        return pathFoundUtil(startVertex,target,adjmtx,visited);
    }// path found

    private boolean pathFoundUtil(int vtx, int data, int[][] mtx, boolean[]visited){
        if(vtx == data)return true;

        for(int i = 0; i < mtx[vtx].length;i++){
            if(mtx[vtx][i]==0)continue;//no edge
            if(!visited[i]){
                visited[i] = true;
                if(pathFoundUtil(i,data,mtx,visited)){
                    return true;
                }//if visited
            }//for nbours
        }
        return false;
    }//dfs util

}//class
