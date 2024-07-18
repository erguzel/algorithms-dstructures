package com.egzel.lib.model.abstraction;

public interface IBinaryTreeNode {

    double getValue();
    void setValue(double value);

    IBinaryTreeNode getLeft();
    void setLeft(IBinaryTreeNode node);

    IBinaryTreeNode getRight();

    void setRight(IBinaryTreeNode node);


}
