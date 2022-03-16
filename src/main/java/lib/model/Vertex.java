package lib.model;

import lib.model.abstraction.IGraphEdge;
import lib.model.abstraction.IGraphVertex;

import java.util.*;

public class Vertex implements IGraphVertex {

    public static void main(String[] args) {
        Vertex v = new Vertex("first",18);
        Vertex v1 = new Vertex("sec",18);
        Vertex v2 = new Vertex("trr",2);
        System.out.println(v.equals(v1));

        HashMap<Vertex,Integer> map = new HashMap();
        map.put(v,1);
        map.put(v1,11);
        map.put(v2,111);

        Vertex v3 = null;

        Set set = new HashSet();
        set.add(v);
        set.add(v1);
        System.out.println(set);



    }

    private boolean isVisited = false;
    private String name = "";
    private int id;
    private List<IGraphEdge> edges = new ArrayList<>();

    public Vertex(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setEdges(List<IGraphEdge> edges) {
        this.edges = edges;
    }

    @Override
    public List<IGraphEdge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null)return false;
        if(o instanceof Vertex){
            Vertex test = (Vertex) o;
            if(this.id == test.id){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id);
    }

    @Override
    public String toString(){
        return this.name;
    }
}