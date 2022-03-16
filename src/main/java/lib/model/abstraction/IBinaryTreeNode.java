package lib.model.abstraction;

public interface IBinaryTreeNode extends IValuable{

    IBinaryTreeNode getLeft();
    void setLeft(IBinaryTreeNode node);

    IBinaryTreeNode getRight();

    void setRight(IBinaryTreeNode node);


}
