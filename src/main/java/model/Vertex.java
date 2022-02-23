package model;

import model.abstraction.*;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements IGraphVertex {

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
}