package lib.model.abstraction;

public interface IBSTreeNodeMethods {
    double getExtremumRecursive(boolean isMax);
    double sumRecursiveTernary();
    double sumRecursiveNormal();
    boolean contains(IBinaryTreeNode node);
    String stringifySubtree();
    boolean containsbfs(IBinaryTreeNode node);
    IBinaryTreeNode getExtremum(boolean min);
    void insert (IBinaryTreeNode binaryTreeNode);
}
