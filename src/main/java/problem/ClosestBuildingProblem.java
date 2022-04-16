package problem;

import lib.model.ProblemBase;
import lib.util.ALogger;

import java.util.*;
import java.util.stream.IntStream;

public class ClosestBuildingProblem extends ProblemBase {

    public static void main(String[] args) {
        boolean[][] facilities = new boolean[5][3];
        IntStream.range(0, facilities.length).forEach(a -> {
            facilities[a] = new boolean[3];
        });

        facilities[0][0] = false;
        facilities[0][1] = true;
        facilities[0][2] = false;

        facilities[1][0] = true;
        facilities[1][1] = false;
        facilities[1][2] = false;

        facilities[2][0] = true;
        facilities[2][1] = true;
        facilities[2][2] = false;

        facilities[3][0] = false;
        facilities[3][1] = true;
        facilities[3][2] = false;


        facilities[4][0] = false;
        facilities[4][1] = true;
        facilities[4][2] = true;

        int[] desiredFacilityIds = {0, 1, 2};
        ClosestBuildingProblem closestBuildingProblem = new ClosestBuildingProblem();
        closestBuildingProblem.findBestBuilding(facilities,desiredFacilityIds);

    }

    public void findBestBuilding(boolean[][] facilities, int[] desiredFacilityIds){
        int[][] totalDistances = new int[facilities.length][desiredFacilityIds.length];
        IntStream.range(0, totalDistances.length).forEach(x -> {
            Arrays.fill(totalDistances[x], Integer.MAX_VALUE);
        });


        // represents the labels of building that contains desired facility
        Map<Integer, List<Integer>> distancemap = new HashMap<>();

        ALogger.TIMER timer = new ALogger.TIMER();
        timer.startTimer();
        //prepare distances map
        IntStream.range(0,desiredFacilityIds.length).forEach(desiredFacilityId->{
            IntStream.range(0,facilities[0].length).forEach(facility->{
                IntStream.range(0,facilities.length).forEach(building->{
                    if(desiredFacilityId == facility){
                        if(facilities[building][desiredFacilityId]){
                            if(distancemap.get(desiredFacilityId)==null){
                                distancemap.put(desiredFacilityId,new ArrayList<>());
                            }
                            distancemap.get(desiredFacilityId).add(building);
                        }
                    }
                });
            });
        });

        // calculate total distances to desired facilities

        IntStream.range(0,desiredFacilityIds.length).forEach(desiredFacilityId->{
            IntStream.range(0,facilities[0].length).forEach(facility->{
                IntStream.range(0,facilities.length).forEach(building->{
                    if(desiredFacilityId == facility){
                        if(!facilities[building][desiredFacilityId]){
                            //check which building has this facility and which one is closer
                            List<Integer> candidateBuildings = distancemap.get(desiredFacilityId);
                            int minDistance = Integer.MAX_VALUE;
                            for(Integer candidate: candidateBuildings){
                                boolean distanceSmaller = Math.abs(building-candidate)<minDistance;
                                if(distanceSmaller){
                                    minDistance = Math.abs(building-candidate);
                                }
                            }
                            totalDistances[building][desiredFacilityId] = minDistance;
                        }else {
                            totalDistances[building][desiredFacilityId] = 0;
                        }
                    }
                });
            });
        });

        TreeMap<int[], Integer> bestBuilding = new TreeMap<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                double eucthis1 = Math.sqrt(Math.pow(o1[0],2)+Math.pow(o1[1],2)+Math.pow(o1[2],2));
                double eucthis2 = Math.sqrt(Math.pow(o2[0],2)+Math.pow(o2[1],2)+Math.pow(o2[2],2));
                return  eucthis1>eucthis2?1:-1;
            }
        });

        IntStream.range(0,totalDistances.length).forEach(rank->{
            bestBuilding.put(totalDistances[rank],rank);
        });

        int result = bestBuilding.pollFirstEntry().getValue();
        System.out.println(result);
        timer.getBenchmark(timer);
    }
}
