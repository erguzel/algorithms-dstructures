package com.egzel.problem;

import java.util.Stack;

import com.egzel.lib.model.ProblemBase;
import com.egzel.lib.util.SampleData;

/**
 * Given an integer n representing the number of courses labeled 0 to n-1, and an array of prerequistes where prerequiste[i]=[a,b] means
 * that you first need to take the course b before taking the course a. determine if it is possible to finish all the courses
 */
public class CourseScheduleProblem extends ProblemBase {

    public static void main(String[] args) {
        int[][] graph = SampleData.Csacademy.COURSE_SCHEDULE_3;
        CourseScheduleProblem courseScheduleProblem = new CourseScheduleProblem();
        boolean res = courseScheduleProblem.canGraduate(graph,12);
        System.out.println(res);
    }


    //finds a cycle in  a directed graph
// prerequistes [a,b] => b->a


    public boolean canGraduate(int[][] edges, int nofevertices){
        return hasCycle(edges,nofevertices)?false:true;
    }//

    public boolean hasCycle(int[][] edges,int nofvertices){
        int[] visited = new int [nofvertices];
        Stack<Integer> callstack = new Stack<Integer>();
        for(int i = 0; i < nofvertices;i++){
            callstack.add(i);
        }//for fill stack
        while(!callstack.isEmpty()){
            int current = callstack.pop();
            if(visited[current] ==2)continue;
            if(hasCycleUtil(current,edges,visited,callstack)){
                return true;
            }//call recursive
        }//while candidates
        return false;
    }// hasCycle

    public boolean hasCycleUtil(int vertex, int[][]edges,int[] visited, Stack<Integer> callstack){
        visited[vertex]=1;//in progress
        for(int l = 0; l<edges.length;l++){
            if(edges[l][1] != vertex)continue;
            int nbid = edges[l][0];
            if(visited[nbid]==0) callstack.add(nbid);
            if(visited[nbid]==1)return true;
            if(hasCycleUtil(nbid,edges,visited,callstack)){
                return true;
            }//if has cycle in deeper level
        }//all nbours in deeper level
        visited[vertex] = 2;
        return false;
    }//hasCycleUtil


}
