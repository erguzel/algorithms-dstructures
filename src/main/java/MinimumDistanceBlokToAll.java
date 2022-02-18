import java.util.*;

//finds the closest block to others in given conditions
public class MinimumDistanceBlokToAll {

    private static class DistancePoint {

        private int from;
        private double totalDistance;

        public DistancePoint(boolean gym, boolean store, boolean school) {
            this.gym = gym;
            this.store = store;
            this.school = school;
        }

        private boolean gym = false;
        private boolean store = false;
        private boolean school = false;
    }

    public static void main(String[] args) {

        boolean[] bools = new boolean[3];



        //school=0, store = 1 gym = 2

//        Map<String,Integer > storeCodes = Map.of(
//
//                "school",0,
//                "store",1,
//                "gym",2
//        );
//
//
//        int [][] blocks = {
//                {1,0,1},//A
//                {0,1,0},//B
//                {1,0,1},//C
//                {1,1,0},//D
//                {1,0,1}//E
//        };

        int[][] blockCoordinates = {
                {0,1,0},
                {1,0,0},
                {1,1,0},
                {0,1,0},
                {0,1,1}
        };

        findDistanceDistributionTo(blockCoordinates,new int[]{1,1,1});
    }

    public static int findAdvantageousPoint(int[][] blocks){

        // I have 4 points in a 3d space. Need shortest distance to all

        SortedSet<DistancePoint> points = new TreeSet<>((a,b)-> a.totalDistance >b.totalDistance ?1:-1);

        for(int i = 0; i < blocks.length; i++){

            //0,1,2 coords

            double totalDistance = 0;
            for (int j = 0; j < blocks.length; j++){
                if(i==j) {
                    continue;
                }
                int x1,x2,y1,y2,z1,z2=0;

                x1 = blocks[i][0];
                y1 = blocks[i][1];
                z1 = blocks[i][2];

                x2 = blocks[j][0];
                y2 = blocks[j][1];
                z2 = blocks[j][2];

                double distance = Math.sqrt(Math.pow((x2-x1),2) + Math.pow(y2-y1,2) + Math.pow((z2-z1),2));
                totalDistance = totalDistance+distance;


            }
           // points.add(new DistancePoint(i,totalDistance));

        }

        return points.first().from;

    }

    //find total distance of each point to all others, including reference point
    //find the point which have the minimum total distance
    // return id of that point
    public static void findDistanceDistributionTo(int[][] blockCoords, int[] referencePoint){

        //each node is neighbour to each other
        // each nodes weight will be calculated on fly
        double[] totalDistances = new double[blockCoords.length+1];
        double[][]distancesToEachPoint = new double [blockCoords.length+1][2];//{pointId, pointDistance}


        //loop each point in space


        for (int pointIndex = 0; pointIndex < blockCoords.length;pointIndex++){//1 momre point due to given reference

            int[] currentPoint = blockCoords[pointIndex];
            double totalDist= 0;
            double distance = 0;
            boolean[][] completed=new boolean[blockCoords.length+1][blockCoords.length+1];
            for(int neighbourPointIndex = 0; neighbourPointIndex<blockCoords.length+1; neighbourPointIndex++){

                if(pointIndex == neighbourPointIndex){
                    totalDistances[pointIndex] = 0;
                    continue;
                }

                if(completed[neighbourPointIndex][pointIndex ]||completed[pointIndex][neighbourPointIndex]){
                    continue;
                }

                int [] neighboringPoint =  null;

                if(neighbourPointIndex>=blockCoords.length){

                    neighboringPoint = referencePoint;

                }else {
                    neighboringPoint = blockCoords[neighbourPointIndex];
                }



                // get coordinate distance
                //|d| = √(x2-x1)^2 + ...

                for(int i = 0; i < neighboringPoint.length; i ++){

                     distance = distance + Math.pow(neighboringPoint[i]-currentPoint[i],2);
                }

                 distance = Math.pow(distance,0.5);

                distancesToEachPoint[neighbourPointIndex]= new double[]{neighbourPointIndex,distance};
                totalDist = totalDist + distance;
                distance = 0;

                completed[pointIndex][neighbourPointIndex] = true;
                completed[neighbourPointIndex][pointIndex ] = true;
            }

            totalDistances[pointIndex] = totalDist;
            totalDist = 0;
        }

        System.out.println("dur..");
    }
}
