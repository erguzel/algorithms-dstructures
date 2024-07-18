package com.egzel.lib.model.abstraction.graph;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

class Vertex  implements IVertex {

    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    @Override
    public void setExplored(boolean explored) {
        this.explored = explored;
    }
    @Override
    public Set<IEdge> getNbours() {
        return nbours;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public boolean isVisited() {
        return visited;
    }
    @Override
    public boolean isExplored() {
        return explored;
    }
    @Override
    public int getDegree() {
        return this.nbours.size();
    }
    @Override
    public int getIndegree() {
        return this.indegree;
    }
    @Override
    public void setIndegree(int indegree) {
        this.indegree = indegree;
    }
    private int id ;
    private int indegree = -1;
    private boolean visited;
    private boolean explored;
    private Set<IEdge> nbours = new HashSet<>();

    public Vertex(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
       return String.format(getId()+"->"+nbours.toString());
    }



    // todo: equals and hashcode logic use cases
}
