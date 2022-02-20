import java.util.Arrays;


public class EmptyMain {


    public static void main(String[] args) {

       int[][][] sample = SampleInputs.get18weightedShortestPath();

        System.out.println("=====BFS======");
        BreadtFirstSearch breadtFirstSearch = new BreadtFirstSearch();
        breadtFirstSearch.findShortestDistances(sample,3,8);
        System.out.println("=====DFS======");
        DepthFirstSearch debthFirstSearch = new DepthFirstSearch();
        debthFirstSearch.findAllShortestDistancesToSource(sample, 3, 8);
        System.out.println("=====DIJKS======");
        Dijkstara d = new Dijkstara();
        d.getShortestPath(sample, 3, 8);




    }
}
