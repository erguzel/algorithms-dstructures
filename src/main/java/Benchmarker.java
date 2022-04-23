
import lib.util.ALogger;
import lib.util.DataUtil;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

public class Benchmarker {

    static ALogger<Benchmarker> LOGGER = new ALogger<>(Benchmarker.class);


    static class EmploInfo{
        int id ;
        int rank ;
        int salary;

        public EmploInfo(int id, int rank, int salary) {
            this.id = id;
            this.rank = rank;
            this.salary = salary;
        }
        @Override
        public boolean equals(Object o){
            if(this ==o) return true;
            if(o == null|| this.getClass() != o.getClass()) return false;
            EmploInfo e = (EmploInfo) o;

            return this.id == e.id;
        }

        @Override
        public int hashCode(){
            return Objects.hash(this.id);
        }

        @Override
        public String toString() {
            return "EmploInfo{" +
                    "id=" + id +
                    ", rank=" + rank +
                    ", salary=" + salary +
                    '}';
        }
    }

    static class Emplo {
        public int id;
        public int age;
        public String name;

        public Emplo(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }


        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o==null|| this.getClass() != o.getClass())return false;
            Emplo e = (Emplo)o;
            return this.id == e.id && this.name.equals( e.name) && this.age == e.age;
        }//equals

        @Override
        public int hashCode(){
            return Objects.hash(this.id, this.name, this.age);
        }//hashcode



        @Override
        public String toString() {
            return "Emplo{" +
                    "id=" + id +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}'+"\nhashCode:"+this.hashCode();
        }
    }

    static class ArrayElement {
        public int index = -1;
        public int value = Integer.MAX_VALUE;


        public ArrayElement(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "ArrayElement{" +
                    "index=" + index +
                    ", value=" + Character.valueOf((char) value) +
                    '}';
        }


        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayElement that = (ArrayElement) o;
            return this.value == that.value && this.index == that.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, value);
        }
    }

    static class WordLadder{
        String word;
        int numOfSteps;

        public WordLadder(String word, int numOfSteps) {
            this.word = word;
            this.numOfSteps = numOfSteps;
        }
    }

    private static boolean isPalindrom(String text){
        boolean result = true;
        char[] txt = text.toCharArray();
        int length  = txt.length;
        for(int i = 0,j = length-1; i < length && j>0; i++,j--){
            if(i>=j)break;
            if(txt[i]!=txt[j]){
                return false;
            }
        }

        return result;
    }
    public static void main(String[] args) {



        Predicate<Integer> predicate = a->a.equals(0);
        int [][] sample1 = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 4},
                {3, 5},
                {1, 6},
                {2, 7},

        };

        OptionalInt op = IntStream.range(100,200).filter(a->a<100).max();

        var streamHelper = new Object(){
            int val = -1;
        };

        op.ifPresentOrElse(a-> streamHelper.val=a+1,()->{
            streamHelper.val = 12;
        });


        System.out.println(streamHelper.val);

//        int[][] input1 = DataUtil.Convertors.convertAdjMatrixToEdgeList(DataUtil.Convertors.convertEdgeListToAdjMatrix(sample1,false),false);
//
//        System.out.println("\n"+DataUtil.Printers.stringifyEdgeList(input1));;

    }


}
