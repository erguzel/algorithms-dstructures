package lib.model.abstraction.graph;

public interface IGraph {

    int getSize();
    int getOrder();
    boolean isMaxSize();
    IVertex getMinimumIndegreeVertex();
}
