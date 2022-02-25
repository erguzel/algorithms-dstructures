import java.util.Arrays;

public class Benchmarker {

    static ALogger<Benchmarker> LOGGER = new ALogger<>(Benchmarker.class);

    public static void main(String[] args) {

        //int [][] mtx1 = SampleData.Generators.generateRandomMatrix(4,4,-2,12);

        //LOGGER.info(SampleData.Printers.stringifyAdjacencyMatrix(mtx1));

        int[][] mtx = {
                {-1, 9, 3, -1},
                {8, 5, 11, 1},
                {8, 6, 9, 7},
                {7, 0, 7, -1}
        };

        LOGGER.info("Mtx\n" + SampleData.Printers.stringifyAdjacencyMatrix(mtx));

//        int [][] other = new int[mtx.length][mtx[0].length];
//
//        int [][] empt = new int[other.length][other[0].length];
//
//        other = Arrays.copyOf(mtx,mtx.length);
//
//        for(int i = 0; i < other.length;i++){
//            for(int j = 0; j<other[i].length; j++){
//                if(i==1 || j==1){
//                    empt[i][j] = other[i][j];
//                }
//
//            }
//        }
//
//       int[][] other2 = Arrays.copyOf(mtx,mtx.length);
//        LOGGER.info("\n" + SampleData.Printers.stringifyAdjacencyMatrix(empt));
//
//        // get colum 2
//        int [] col = new int[other2.length];
//
//        for(int i = 0; i < other2.length; i++){
//            int val = -1;
//            for(int j = 0; j < other2[i].length; j++){
//                if(j==2){
//                    val = other2[i][j];
//                }
//            }
//            col[i] = val;
//        }
//
//        LOGGER.info("\n"+SampleData.Printers.stringifyArray(col));
//
//        // get row 3
//
//        int [] row = mtx[3];
//
//        LOGGER.info("\n"+SampleData.Printers.stringifyArray(row));
//
//        // swap col index 2 and col index 3

        int[][] other1 = new int[mtx.length][mtx[0].length];

        for (int i = 0; i < mtx.length; i++) {
            for (int j = 0; j < mtx[i].length; j++) {
                other1[i][j] = mtx[i][j];
            }
        }

        for (int i = 0; i < other1.length; i++) {
            for (int j = 0; j < other1[i].length; j++) {
                if (j == 2) {

                    int temp = 0;
                    temp = other1[i][2];
                    other1[i][2] = other1[i][3];
                    other1[i][3] = temp;

                }
            }
        }

        LOGGER.info("\n" + SampleData.Printers.stringifyAdjacencyMatrix(other1));


        LOGGER.info("End of main");
    }
}
