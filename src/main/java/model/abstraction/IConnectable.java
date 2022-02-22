package model.abstraction;

import model.Vertex;

public interface IPointable extends IWeightable {

    Vertex getSourcePoint();
    Vertex getTargetPoint();
}
