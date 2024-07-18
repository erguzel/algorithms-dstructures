package com.egzel.lib.model.abstraction.graph;


import java.util.Objects;

class Edge  implements IEdge{

    @Override
    public int getId() {
        return targetVertexId;
    }
    @Override
    public void setId(int targetVertexId) {
        this.targetVertexId = targetVertexId;
    }
    @Override
    public Object getWeight() {
        return weight;
    }
    @Override
    public void setWeight(Object weight) {
        this.weight = weight;
    }

    private int targetVertexId;
    private Object weight;

    public Edge(int targetVertexId, Object weight) {
        this.targetVertexId = targetVertexId;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(this.getClass() != o.getClass() || o == null) return false;
        Edge that = (Edge) o;

        return this.targetVertexId == that.targetVertexId && this.weight == that.weight;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.targetVertexId,this.weight);
    }

    @Override
    public String toString(){
        return String.format("to "+getId()+"="+getWeight());
    }
}
