
import lib.model.abstraction.graph.*;
import lib.util.ALogger;
import lib.util.DataUtil;
import lib.util.SampleData;
import lib.util.exception.BaseException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Benchmarker {

    static ALogger<Benchmarker> LOGGER = new ALogger<>(Benchmarker.class);
    private static Predicate<IVertex> higherVerticeIdThanNumberOfVertex = a->a.getId()>-1;

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

        int[][] input1 = {
                {0, 1},
                {0, 2},
                {1, 2},
                {1, 4},
                {4, 5},
                {4, 6},
                {3, 2},
        };

        int asd [][] = DataUtil.Convertors.convertAdjListToAdjMatrix(          input1,false);


        var myHelper = new Object(){
          int id = 0;
          ConcurrentHashMap<Object,Object> data = new ConcurrentHashMap<>();
          boolean visited = false;
        };



    }

    public static void  traverse(int [][] graph, int startId){
        ListyGraph lg = new ListyGraph(graph, graph.length, IGraph.GraphTypes.ADJLIST);
        Stack<IVertex> vertexStack = new Stack<>();
        vertexStack.add(lg.get(startId));

        while (!vertexStack.isEmpty()){
            IVertex current = vertexStack.pop();
            System.out.println(current.getId());
            if(!current.isVisited()){
                System.out.println(current.getId()); // sour vertexid // O(1)
                current.setVisited(true);
            }
            for(IEdge e : current.getNbours()){

                if(!lg.get(e.getId()).isVisited()){
                    vertexStack.add(lg.get(e.getId()));
                }
            }

        }

    }

    public static int pollCount = 0;
    public static void testMethod(int[][] graph, int nofver, int source){

        


    }

}
