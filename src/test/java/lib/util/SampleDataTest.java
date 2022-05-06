package lib.util;

import lib.model.ProblemBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleDataTest extends ProblemBase {

    //
    //
    @Test
    void testConvertAdjListToAdjMatrixWeighted(){
        int[][][] inputindirected = {
                {{2,1},{4,2},{5,1}},
                {{5,1}},
                {{0,1},{4,3},{5,4}},
                {{4,-1}},
                {{0,2},{2,3},{3,-1}},
                {{0,1},{1,1},{2,4}}
        };

        int[][] wantedindirected = {
                {0,0,1,0,2,1},
                {0,0,0,0,0,1},
                {1,0,0,0,3,4},
                {0,0,0,0,-1,0},
                {2,0,3,-1,0,0},
                {1,1,4,0,0,0},
        };

        int[][][] inputdirected={
                {{2,1},{4,2},{5,1}},
                {{5,1}},
                {{0,1},{4,3},{5,4}},
                {{4,-1}},
                {{0,12},{2,3},{3,-8}},
                {{0,1},{1,1},{2,4}}
        };

        int[][] wanteddirected = {
                {0,0,1,0,2,1},
                {0,0,0,0,0,1},
                {1,0,0,0,3,4},
                {0,0,0,0,-1,0},
                {12,0,3,-8,0,0},
                {1,1,4,0,0,0},
        };



        assertArrayEquals(DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(inputdirected,true),wanteddirected);
        assertArrayEquals(DataUtil.Convertors.convertAdjListToAdjMatrixWeighted(inputindirected,false),wantedindirected);
    }
    //
    //
    @Test
    void testConvertAdjListToAdjMatrix(){

        int[][] input ={
                {2,4,5},
                {5},
                {4,5},
                {4},
                {},
                {}
        };

        int[][] wantdirected = {
                {0,0,1,0,1,1},
                {0,0,0,0,0,1},
                {0,0,0,0,1,1},
                {0,0,0,0,1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][] wantindirected = {
                {0,0,1,0,1,1},
                {0,0,0,0,0,1},
                {1,0,0,0,1,1},
                {0,0,0,0,1,0},
                {1,0,1,1,0,0},
                {1,1,1,0,0,0},
        };

        assertArrayEquals(DataUtil.Convertors.convertAdjListToAdjMatrix(input,true),wantdirected);
        assertArrayEquals(DataUtil.Convertors.convertAdjListToAdjMatrix(input,false),wantindirected);
    }
    //
    //
    @Test
    void testConvertAdjMatrixToAdjListWeighted(){
        int[][] input = {
                {0,0,1,0,2,1},
                {0,0,0,0,0,1},
                {0,0,0,0,3,4},
                {0,0,0,0,-1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][][] wantedindirect={
                {{2,1},{4,2},{5,1}},
                {{5,1}},
                {{0,1},{4,3},{5,4}},
                {{4,-1}},
                {{0,1},{2,1},{3,1}},
                {{0,1},{1,1},{2,1}}
        };

        int[][][] wanteddirect = {
                {{2,1},{4,2},{5,1}},
                {{5,1}},
                {{4,3},{5,4}},
                {{4,-1}},
                {},
                {}
        };

        assertArrayEquals(DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input,false),wantedindirect);
        assertArrayEquals(DataUtil.Convertors.convertAdjMatrixToAdjListWeighted(input,true),wanteddirect);
    }

    @Test
    void testConvertAdjMatrixToAdjList(){
        int[][] input = {
                {0,0,1,0,1,1},
                {0,0,0,0,0,1},
                {0,0,0,0,1,1},
                {0,0,0,0,1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][] wantdirected ={
                {2,4,5},
                {5},
                {4,5},
                {4},
                {},
                {}
        };

        int[][] wantindirected ={
                {2,4,5},
                {5},
                {0,4,5},
                {4},
                {0,2,3},
                {0,1,2}
        };

        assertArrayEquals(DataUtil.Convertors.convertAdjMatrixToAdjList(input,true),wantdirected);
        assertArrayEquals(DataUtil.Convertors.convertAdjMatrixToAdjList(input,false),wantindirected);
    }

    @Test
    void testConvertAdjMatrixToEdgeList(){
        int[][] input = {
                {0,0,-12,0,1,1},
                {0,0,0,0,0,1},
                {0,0,0,0,1,1},
                {0,0,0,0,1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][] wanteddirected = {
                {0,2,-12},
                {0, 4,1},
                {0, 5,1},
                {1, 5,1},
                {2, 4,1},
                {2, 5,1},
                {3, 4,1},
                //{2, 0,24},
        };

        int[][] wantedindirected = {
                {0,2,-12},
                {2,0,-12},
                {0, 4,1},
                {4, 0,1},
                {0, 5,1},
                {5, 0,1},
                {1, 5,1},
                {5, 1,1},
                {2, 4,1},
                {4, 2,1},
                {2, 5,1},
                {5, 2,1},
                {3, 4,1},
                {4, 3,1},
                //{2, 0,24},
        };

        assertArrayEquals(DataUtil.Convertors.convertAdjMatrixToEdgeList(input,true),wanteddirected);
        assertArrayEquals(DataUtil.Convertors.convertAdjMatrixToEdgeList(input,false),wantedindirected);

    }

    @Test
    void testConvertEdgeListToAdjMatrix(){

        int[][] input = {
                {0,2,-12},
                {0, 4},
                {0, 5},
                {3, 4},
                {1, 5},
                {2, 4},
                //{2, 0,24},
        };

        int[][] wantDirected = {
                {0,0,-12,0,1,1},
                {0,0,0,0,0,1},
                {0,0,0,0,1,0},
                {0,0,0,0,1,0},
                {0,0,0,0,0,0},
                {0,0,0,0,0,0},
        };

        int[][] wantindirected = {
                {0,0,-12,0,1,1},
                {0,0,0,0,0,1},
                {-12,0,0,0,1,0},
                {0,0,0,0,1,0},
                {1,0,1,1,0,0},
                {1,1,0,0,0,0}
        };

        assertArrayEquals(DataUtil.Convertors.convertEdgeListToAdjMatrix(input,true),wantDirected);
        assertArrayEquals(DataUtil.Convertors.convertEdgeListToAdjMatrix(input,false),wantindirected);
    }

}