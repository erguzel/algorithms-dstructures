package lib.model.abstraction.graph;

import java.util.Set;
import java.util.TreeSet;

public interface IVertex extends IIdentity,IVisited, IExplored , IDegree, IIndegree{


    Set<IEdge> getNbours();

}
