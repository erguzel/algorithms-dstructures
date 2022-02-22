package model;

import model.abstraction.IGraphEdge;

public class Edge implements IGraphEdge {

    private Vertex sourcePoint;
    private Vertex targetPoint;
    private double weight = 0;

    @Override
    public void setSourcePoint(Vertex sourcePoint) {
        this.sourcePoint = sourcePoint;
    }

    @Override
    public void setTargetPoint(Vertex targetPoint) {
        this.targetPoint = targetPoint;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public Vertex getSourcePoint() {
        return sourcePoint;
    }

    @Override
    public Vertex getTargetPoint() {
        return targetPoint;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
