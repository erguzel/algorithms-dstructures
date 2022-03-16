package lib.model;

import java.util.*;

public class MyBSTree <T extends Comparable<T>> {
    private MyBSTreeNode root;

    public MyBSTree() {
    }

    public MyBSTree(T val) {
        this.root = new MyBSTreeNode(val);
    }
    //
    // publics
    //

    //
    //delete element if exists
    //
    //
    public boolean delete(T val){
        if(!contains(val))return false;
        this.root = this.delete(this.root,val);
        return true;

    }
    private MyBSTreeNode delete(MyBSTreeNode root, T val){
        if(root == null) return null;
        boolean isleft=root.val.compareTo(val)>0;
        boolean isRight = root.val.compareTo(val)<0;
        if(isleft){
            root.left = this.delete(root.left,val);
        }else if(isRight){
            root.right = this.delete(root.right,val);
        }else{
            //found target to delete
            if(root.left == null){
                //replace with right
                return root.right;
            }else if(root.right == null){
                //replace with left
                return root.left;
            }else {
                //leftest node in right subtree
                MyBSTreeNode temp = this.min(this.root.right,this.root);
           //     MyBSTreeNode temp = this.min(this.root.right,this.root);
                //swap data
                root.val = temp.val;
                // Go into the right subtree and remove the leftmost node we
                // found and swapped data with. This prevents us from having
                // two nodes in our tree with the same value.
                root.right = delete(root.right,temp.val);
            }
        }
        return root;
    }

    // traverses order, 0,1 preorder, 2 inorder, 3 postorder, 4 depthorder
    //
    public void traverseOrder(int orderType){
            if(orderType<4){
                this.traverseOrder(this.root,orderType);

            }else {
                traverseOrderNR(this.root);
            }

    }
    private void traverseOrderNR(MyBSTreeNode root){
        Queue<MyBSTreeNode> traverseStack = new LinkedList<>();
        traverseStack.add(root);
        while (!traverseStack.isEmpty()){
            MyBSTreeNode od = traverseStack.poll();
            System.out.println(od.val);
            if(od.left!=null){
                traverseStack.add(od.left);
            }
            if(od.right!=null){
                traverseStack.add(od.right);
            }

        }
    }
    private void traverseOrder(MyBSTreeNode root,int orderType){
        if(root==null)return;
        if(orderType>4){
            System.out.println("Bad order type");
            return;
        }
        if(orderType<=1){
            System.out.println(root.val);
        }
        traverseOrder(root.left,orderType);
        if(orderType==2){
            System.out.println(root.val);
        }
        traverseOrder(root.right,orderType);
        if(orderType == 3){
            System.out.println(root.val);
        }
    }
    //
    // finds min value
    //
    public T minVal(){
        return min(this.root,null).val;
    }
    private MyBSTreeNode min(MyBSTreeNode root,MyBSTreeNode parent){
        if(root == null)return parent;
        return this.min(root.left,root);
    }
    // Helper method to find the leftmost node (which has the smallest value)
    private MyBSTreeNode findMin(MyBSTreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
    //
    // finds max val
    //
    public T maxVal(){
        return this.max(this.root,null).val;
    }
    private MyBSTreeNode max(MyBSTreeNode root, MyBSTreeNode parent){
        if(root==null)return parent;
        return this.max(root.right,root);
    }
    //
    //inserts
    //
    public void insert(T val){
        this.root = this.insert(this.root,val);

    }
    private MyBSTreeNode insert(MyBSTreeNode root, T val){
        if(root==null) return  new MyBSTreeNode(val);
        if(root.val.compareTo(val)==1){
            root.left = this.insert(root.left,val);
            return root;
        }else {
            if(root.val.compareTo(val)==-1){
                root.right = this.insert(root.right,val);
                return root;
            }else {
                System.out.println("Duplicated not allowed "+val.toString());
                return root;
            }
        }
    }
    //
    //checks contains
    //
    //
    public boolean contains(T val){
        return this.contains(this.root,val);
    }
    private boolean contains(MyBSTreeNode root, T val){
        if(root ==null) return false;
        int comparation = root.val.compareTo(val);
        if(comparation>0){
            return contains(root.left,val);
        }else if(comparation<0){
            return contains(root.right,val);
        }else return true;
    }
    //
    // finds height
    //
    public int height (){
        return this.height(this.root);
    }
    private int height(MyBSTreeNode root){
        if(root==null)return 0;
        return Math.max(height(root.left),height(root.right))+1;
    }
    //
    // Utils
    //
    public void printBSTree() {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<MyBSTreeNode> level = new ArrayList<MyBSTreeNode>();
        List<MyBSTreeNode> next = new ArrayList<MyBSTreeNode>();

        level.add(this.root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (MyBSTreeNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<MyBSTreeNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
    //
    // Nested
    //
    private class MyBSTreeNode {
        private T val;
        private MyBSTreeNode left = null;
        private MyBSTreeNode right=null;

        public MyBSTreeNode(T val) {
            this.val = val;
        }
        //
        // Overrides nested
        //
        @Override
        public String toString() {
            return "->" + this.val + "<-";
        }

    }


}
