package com.egzel.graph;

import java.util.*;

import com.egzel.lib.util.ALogger;
import com.egzel.lib.util.SampleData;

/**
 * Prims
 *  O(E.log V)
 */
public class MinimumCostSpanningTree {

    private static class BestEdge{
        private int weight = 0;
        private int id = 0;
        private int parent =0;

        public BestEdge(int weight, int id, int parent) {
            this.weight = weight;
            this.id = id;
            this.parent = parent;
        }
        @Override
        public String toString(){
            return String.format("[%d,%d,%d]", parent, id,weight);
        }
        public List toList(){
            return new ArrayList(Arrays.asList(parent,id,weight));
        }
    }

    ALogger<MinimumCostSpanningTree> LOGGER = new ALogger<>(MinimumCostSpanningTree.class);

    public List PrimsAlgorithm(int[][] graph, int nofvertices) {
        PriorityQueue<BestEdge> priorityQueue = new PriorityQueue<>((a,b)->Double.compare(a.weight,b.weight));
        List<BestEdge> result = new ArrayList<>();

        int[] visitstate = new int[nofvertices];
        boolean completelyVisited = false;
        boolean beingInvestigated=false;
        boolean unvisited=false;

        priorityQueue.add(new BestEdge(0,0,0));//start from any edge
        while (!priorityQueue.isEmpty()){
            BestEdge current = priorityQueue.peek();
            completelyVisited = visitstate[current.id]==2;
            if(completelyVisited){
                priorityQueue.poll();
            }else {
                current = priorityQueue.poll();
                //to skip 1st element
                if(current.id != current.parent){
                    result.add(current);
                }

            }

            int vertex =  current.id;
            visitstate[vertex]=1;
            for(int i = 0; i < graph.length;i++){
                int nbid = -1;
                if(graph[i][0]==current.id){
                    nbid = graph[i][1];
                }else if(graph[i][1]==current.id){
                    nbid = graph[i][0];
                }else continue;
                int weight = graph[i][2];

                completelyVisited = visitstate[nbid]==2;
                beingInvestigated = visitstate[nbid] == 1;
                unvisited         = visitstate[nbid] == 0;
                if(unvisited){
                    BestEdge bestEdge = new BestEdge(weight,nbid,current.id);
                    priorityQueue.add(bestEdge);
                }
            }

            visitstate[current.id] = 2;

        }

       if(result.size()!=nofvertices-1){
           System.out.println("ERR spanning tree node number wrong");
           System.exit(-1);
       }


        System.out.println(result);

       return result;

    }

    public static void main(String[] args) {
        int[][] edges = SampleData.Csacademy.NONDIRECTED_MULTICYCLED_WEIGHTED_12_15;



        MinimumCostSpanningTree minimumCostSpanningTree = new MinimumCostSpanningTree();
        minimumCostSpanningTree.PrimsAlgorithm(edges, 12);

    }

}