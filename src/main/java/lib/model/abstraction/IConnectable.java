package lib.model.abstraction;

import lib.model.Vertex;

public interface IConnectable extends IWeightable {

    Vertex getSourcePoint();
    void setSourcePoint(Vertex sourcePoint);


    Vertex getTargetPoint();
    void setTargetPoint(Vertex targetPoint);
}
