package com.egzel.lib.model.abstraction.graph;

public interface IGraph {

     enum GraphTypes{
        ADJMTX,
        ADJLIST,
        EDGELIST
    }

    int getSize();
    int getOrder();
    boolean isMaxSize();
}
