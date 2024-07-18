package com.egzel.problem.lcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * <p>
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * <p>
 * Example 2:
 * <p>
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * <p>
 * Example 3:
 * <p>
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */
public class CourseScheduleIIProblem {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        ArrayDeque<Integer> callqueue = new ArrayDeque<>();
        List<Integer> resultPath = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];

        // fill indegrees -> {0,1} 0 dependendent 1

        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
        }

//        IntStream.range(0,prerequisites.length).forEach(v->{
//            indegree[prerequisites[v][0]]++;
//        });
//
//        IntStream.range(0,indegree.length).filter(d->indegree[d]==0)
//                .forEach(x->{
//                    callqueue.offer(x);
//                });

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                callqueue.offer(i);
            }
        }

        while (!callqueue.isEmpty()) {
            int vtx = callqueue.poll();
            if (visited[vtx]) continue;
            visited[vtx] = true;
            resultPath.add(vtx);
//            IntStream.range(0,prerequisites.length).filter(v->prerequisites[v][1]==vtx)
//                    .forEach(vx->{
//                        int nbidx = prerequisites[vx][0];
//                        if(!visited[nbidx]){
//                            if(indegree[nbidx]>0){
//                                indegree[nbidx]--;
//                            }
//                            if(indegree[nbidx]==0){
//                                callqueue.add(nbidx);
//                            }
//                        }
//                    });


            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] != vtx) continue;
                int nbidx = prerequisites[i][0];
                if (!visited[nbidx]) {
                    if (indegree[nbidx] > 0) {
                        indegree[nbidx]--;
                    }
                    if (indegree[nbidx] == 0) {
                        callqueue.add(nbidx);
                    }
                }
            }
        }


        if (resultPath.size() != numCourses) {
            resultPath.clear();
        }
        return resultPath.stream().mapToInt(a -> a).toArray();
    }
}
