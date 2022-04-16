import lib.util.ALogger;
import java.util.*;
import java.util.stream.Collectors;


public class CycleInGraph {

    public static void main(String[] args) {
        CycleInGraph cycleInGraph = new CycleInGraph() ;

        int[][] graph= {
                {0, 1, 10},
                {2, 0,  1},
                {1, 4,  0},
                {3, 2,  2},
                {5, 2,  8},
                {3, 5,  2},
                {3, 6,  7},
                {4, 5,  1},
                {4, 7,  8},
                {5, 6,  6},
                {5, 7,  9},
                {6, 7, 12},
        };
        boolean ss = cycleInGraph.test1(graph,8);
        System.out.println(ss);

        String[] asd = {"a","b","c","d","e"};

        int[] ints = new int[10];
        Arrays.setAll(ints,a->a+2);

        List strings = Arrays.stream(asd).collect(Collectors.toList());

        List sblist = strings.subList(1,4);

        Collections.rotate(strings.subList(1,4),-1);

        System.out.println(strings);


    }

    public boolean test1(int[][] graph, int nofvertices){
        Stack<Integer> callqueue = new Stack<>();
        boolean[] visited = new boolean[nofvertices];
        boolean[]inprogress=new boolean[nofvertices];
        for(int i = 0; i <nofvertices;i++)callqueue.add(i);

        while (!callqueue.isEmpty()){
            int current = callqueue.peek();
            if(!visited[current]){
                visited[current] = true;
                inprogress[current]= true;
                System.out.println(String.format("visited:%d=%b - inprogress: %d=%b",current,visited[current],current,inprogress[current]));
            }else {
                inprogress[current]= false;
                int removed =  callqueue.pop();
                System.out.println(String.format("inprogress: %d=%b - removed:%d -- visited:%d=%b",current,inprogress[current],removed,current,visited[current]));
                continue;
            }
            for(int i =0; i < graph.length;i++){
                int nbid = -1;
                if(graph[i][0]==current){
                    nbid = graph[i][1];
                }else continue;
                int weight = graph[i][2];
                if(!visited[nbid]){
                    System.out.println(String.format("adding nbour %d for %d",nbid,current));
                    callqueue.add(nbid);
                }else if(inprogress[nbid])return true;

            }
            visited[current] = true;
        }
        return false;
    }

    private int count = 0;
    ALogger<CycleInGraph> LOGGER = new ALogger<>(CycleInGraph.class);
    public boolean hasCycleDirectedEdgeList(int[][] graph, int nofvertices, boolean isVerbose){
        boolean[] visited = new boolean[nofvertices];
        boolean[] onstack = new boolean[nofvertices];
        Stack<Integer> callstack = new Stack<>();
        // do dfs from every vertex
        // catch a vertex which is already on stack while it is being visited;
        for(int i = nofvertices-1; i>=0; i--){
            if(visited[i])continue;
            callstack.add(i);
            if(isVerbose)LOGGER.info(String.format("dfs traverse starts for vertex %d : ",i));
            while (!callstack.isEmpty()){
                int current = callstack.peek();
                if (!visited[current]) {
                    visited[current] = true;
                    onstack[current] = true;
                } else {
                    onstack[current] = false;
                    callstack.pop();
                    continue;
                }
                if(isVerbose)LOGGER.info(String.format("visiting nbours of %d : ",current));

                for(int j = 0; j<graph.length;j++){
                    if(graph[j][0]!=current)continue;
                    int nbid = graph[j][1];
                    if(isVerbose)LOGGER.info(String.format("nbours of %d : is %d ",current,nbid));
                    if (!visited[nbid]) {
                        callstack.push(nbid);
                    } else if (onstack[nbid]) return true;

                }
            }
        }

        return false;
    }
    public boolean hasCycleDirectedEdgeListRecursive(int[][] graph, int nofvertices,boolean isVerbose){
        int[] visited = new int[nofvertices];
        Stack<Integer> callstack = new Stack<>();

        for(int i = 0; i <nofvertices; i++){
            if(visited[i]==2)continue;
            callstack.add(i);

            if(isVerbose)LOGGER.info(String.format("Starting dfs on %d : ",i));
            while (!callstack.isEmpty()){
                int current = callstack.pop();
                visited[current] =1;
                if(isVerbose)LOGGER.info(String.format("recursion starts for visiting %d : ",current));
                if(hasCycleDirectedEdgeListRecursiveUtil(current,graph,visited,callstack,isVerbose)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean hasCycleDirectedEdgeListRecursiveUtil(int vertex,int[][] graph, int[] visited,Stack<Integer> callstack, boolean isVerbose){
        count++;
        if(isVerbose)LOGGER.info(String.format("%d recursion  for vertex %d: ",count,vertex));
        visited[vertex] = 1;
        for(int i = 0; i < graph.length;i++){
            if (graph[i][0] != vertex)continue;
            int nbid = graph[i][1];
            if(visited[nbid]==0)callstack.add(nbid);
            if(visited[nbid]==1)return true;
            if(hasCycleDirectedEdgeListRecursiveUtil(nbid,graph,visited,callstack,isVerbose)){
                if(isVerbose)LOGGER.info(String.format("cycle found::vertex %d nbid  %d : ",vertex,nbid));
                return true;
            }
        }
        visited[vertex] = 2;
        return false;
    }

    //
    // Undirected
    //
    public boolean hasCycleUnDirectedEdgeListRecursive(int[][] graph,int nofvertices,boolean isVerbose){
        boolean visited[] = new boolean[nofvertices];
        for(int i = 0; i < nofvertices; i++){
            if(visited[i])continue;
            if(hasCycleUndirectedEdgeListRecursiveUtil(i,-1,graph,visited,isVerbose)){
                return true;
            }
        }
        return false;
    }
    private boolean hasCycleUndirectedEdgeListRecursiveUtil(int vertex,int parent, int[][] graph,boolean[] visited, boolean isVerbose){
        visited[vertex] = true;
        for(int i = 0; i < graph.length;i++){
            if(graph[i][0]!=vertex)continue;
            int nbid = graph[i][1];
            if(nbid!=parent){
                if(visited[nbid])return true;
                else return hasCycleUndirectedEdgeListRecursiveUtil(nbid,vertex,graph,visited,isVerbose);
            }
        }
        return false;
    }
    // Disjoint sets
    public boolean isCyclicUndirectedGraphAdjMtx(int[][] graph){
        LinkedList<Set> sets = new LinkedList<>();
        for(int i =0;i<graph.length;i++){
            Set s = new HashSet();
            s.add(i);
            sets.add(i,s);

        }
        boolean [] visited = new boolean[graph.length];
        Stack<Integer> callstack = new Stack<>();
        callstack.add(0);
        while(!callstack.isEmpty()){
            int current = callstack.pop();
            if(visited[current])continue;
            visited[current] = true;
            for(int i =0; i< graph[current].length; i++){
                if(graph[current][i]==0)continue;
                int[] edge = new int[]{current,i};
                int setid1 = -1;
                int setid2 = -1;
                if(!visited[i]){
                    callstack.add(i);
                    for(int m = 0; m < sets.size();m++){
                        if(sets.get(m).contains(edge[0])){
                            setid1 = m;
                            break;
                        }
                    }
                    for(int m = 0; m < sets.size();m++){
                        if(sets.get(m).contains(edge[1])){
                            setid2 = m;
                            break;
                        }
                    }

                    if(setid1==-1 || setid2==-1) {

                        System.out.println("negative index, linked list error");
                        System.exit(-1);
                    }

                    if(setid1 != setid2){
                        Set set1= sets.get(setid1);
                        Set set2 = sets.get(setid2);
                        set1.addAll(set2);
                        sets.remove(set2);
                    }
                    else
                        return true;
                }
            }
        }




        return false;

    }
    public void hasCycleUnDirectedEdgeList(int[][] edgesUndirected, int nofvertices,boolean isVerbose){
        Stack<Integer> callstack = new Stack<>();
        callstack.add(0);

        boolean [] visited = new boolean[nofvertices];

        while (!callstack.isEmpty()) {
            int current = callstack.pop();
            if(visited[current]){
                System.out.println("Cycle here");
                return;
            }
            int nbid = -1;
            for (int i = 0; i < edgesUndirected.length; i++) {

                int on1 = edgesUndirected[i][0];
                int on2 = edgesUndirected[i][1];
                if(on1==current){
                    nbid = on2;
                }else if(on2 == current){
                    nbid = on1;
                }else {
                    continue;
                }
                if(!visited[nbid]){
                    callstack.add(nbid);
                }
            }

            visited[current]=true;

        }
    }
}
