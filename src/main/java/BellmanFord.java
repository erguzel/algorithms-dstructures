
import java.util.Arrays;

/**
 * O(E.V)
 * Single source shortest path
 * Dynnamic programming O(E.V)
 * Negative edges acceptable
 * if the graph unweighted, no negative edge accepted(- cycle occurs)
 * Negative cycles detectable
 * No LinkedList required
 */
public class BellmanFord {

    public int shortestDistance(int[][] edges, int numberofvertex, int source, int dest){

        int[] dp = new int[numberofvertex];
        Arrays.fill(dp,Integer.MAX_VALUE);
        Object [] prev = new Object[numberofvertex];
        dp[source] = 0;

        for(int k =0; k<numberofvertex;k++){

            for(int i = 0; i < edges.length;i++){

                int sc = edges[i][0];
                int dt = edges[i][1];
                int weight = edges[i][2];

                boolean needRelax = dp[sc]!=Integer.MAX_VALUE && dp[sc] + weight < dp[dt];
                if(needRelax){
                    dp[dt] = dp[sc] + weight;
                    prev[dt] = sc;
                }
            }

        }

        //
        // negative cycle

        for(int i =0; i < edges.length;i++){
            int src = edges[i][0];
            int dst = edges[i][1];
            int wt = edges[i][2];

            boolean needRelax = dp[src] != Integer.MAX_VALUE &&
                    dp[src] + wt < dp[dst];
            if(needRelax){
                System.out.println("Negative cycle");
                return Integer.MAX_VALUE;

            }

        }


        return dp[dest];
    }
}
