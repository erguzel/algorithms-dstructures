package graph;

import lib.util.ALogger;
import lib.util.DataUtil;
import lib.util.SampleData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All sources shortest path
 *
 * DynnamicProgramming - O(Vˆ3)
 * Used on negative cycles as well
 *
 */
public class FloydWarshall {

    static ALogger<FloydWarshall> LOGGER = new ALogger<>(FloydWarshall.class);
    public static void main(String[] args) {

        int[][] edges = SampleData.Csacademy.DIRECTED_CYCLED_7_WEIGHTEDNEG;
        LOGGER.info("\n" + DataUtil.Printers.stringifyEdgeList(edges));
        int[][] adjmtx = null;//DataUtil.Convertors._convertEdgelistToAdjMtx(edges);
        LOGGER.info("\n" + DataUtil.Printers.stringifyAdjacencyMatrix(adjmtx));

        FloydWarshall floydWarshall = new FloydWarshall();
        floydWarshall.findAllDistances(adjmtx, 3, 5);


    }

    public void findAllDistances(int [][] admtx, int paramsourceid, int paramdestid){
        Map<Object,Object> runReport = new HashMap<>();
        int vertexNo = admtx.length;
//distances
        int[][] distances = new int[vertexNo][vertexNo];
        int [][]  next = new int [vertexNo][vertexNo];

// fill initial distances
        for(int i =0; i < vertexNo; i++){
            for(int j = 0; j<vertexNo; j++){

                int val = admtx[i][j];

                if(val == 0){
                    val = Integer.MAX_VALUE;
                }else{
                    val = admtx[i][j];
                    next[i][j] = j;
                }
                distances[i][j] = val;
            }//for iniital dist inner
        }//for initial dist

// for V-1 times
        for(int k =0; k<vertexNo; k++){
            for(int i = 0; i<vertexNo; i++){
                for(int j = 0; j<vertexNo; j++){
                    int dij = distances[i][j];
                    int dik = distances[i][k];
                    int dkj = distances[k][j];
                    boolean validNumber =dik != Integer.MAX_VALUE && dkj != Integer.MAX_VALUE;
                    boolean needRelax = validNumber & dik + dkj <dij;
                    if(needRelax){
                        distances[i][j] = dik + dkj;
                        next[i][j] = next[i][k];
                    }//if need relax
                }//for vertex inner
            }//for vertex
        }//for k-1 times

// find path

        int target = next[paramsourceid][paramdestid];
        List<Integer> path = new ArrayList<>();
        path.add(paramsourceid);

        while(target != paramdestid){
            path.add(target);
            target = next[target][paramdestid];
        }//while path
        path.add(paramdestid);

        runReport.put(paramsourceid+"->"+paramdestid,distances[paramsourceid][paramdestid]);
        runReport.put("PATH",path);
        DataUtil.Printers.PrintReport(LOGGER,runReport);

    }// findAll…
}//class
