package model;

import java.util.*;

public class ABinarySearchTree {

    private ABinarySearchTreeNode root = null;

    public ABinarySearchTree() {
    }

    public ABinarySearchTree(int rootVal) {
        this.root = new ABinarySearchTreeNode(rootVal);
    }

    public void sorted(boolean descending) {
        if (this.root != null) this.root.sorted(descending);
    }


    public void max() {
        this.root.max();
    }

    public void min() {
        this.root.min();
    }

    public boolean insert(int val) {
        if (this.root == null) {
            this.root = new ABinarySearchTreeNode(val);
            return true;
        }
        return this.root.insert(val);
    }

    public boolean contains(int val){
        return this.root.contains(val);
    }

    public void remove(int val){
       this.root = this.root.remove(val);
    }
    /**
     * CLASS Inner
     */
    private static class ABinarySearchTreeNode {
        public ABinarySearchTreeNode() {
        }

        @Override
        public String toString() {
            return "->" + this.value + "<-";
        }

        private int value = Integer.MAX_VALUE;
        private ABinarySearchTreeNode left = null;
        private ABinarySearchTreeNode right = null;

        private ABinarySearchTreeNode remove(int value){

            Queue<ABinarySearchTreeNode> queue = new LinkedList<>();
            ABinarySearchTree tree = new ABinarySearchTree();
            
            ABinarySearchTreeNode node = this;
            queue.add(node);
            while (!queue.isEmpty()){
                node = queue.poll();
                if(node.value != value){
                    tree.insert(node.value);
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            return tree.root;
        }

        private void sorted(boolean descending) {
            Queue<ABinarySearchTreeNode> queue = new LinkedList<>();
            TreeSet<Integer> sorted = new TreeSet<>((a, b) -> descending ? a > b ? -1 : 1 : a > b ? 1 : -1);
            ABinarySearchTreeNode node = this;
            queue.add(node);
            while (!queue.isEmpty()) {
                node = queue.poll();
                sorted.add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
            System.out.println(sorted);
        }

        private void min() {
//            Queue<ABinarySearchTreeNode> queue = new LinkedList<>();
//            TreeSet<Integer> sorted = new TreeSet<>((a,b)->ismax?a>b?-1:0:a<b?-1:0);
//            ABinarySearchTreeNode node = this;
//            queue.add(node);
//            while (!queue.isEmpty()){
//                node = queue.poll();
//                sorted.add(node.value);
//                if(!ismax){
//                    if(node.left != null){
//                        queue.add(node.left);
//                    }
//                }else {
//                    if(node.right != null){
//                        queue.add(node.right);
//                    }
//                }
//
//
//            }

            if (this.left == null) {
                System.out.println(this.value);
                return;
            } else {
                this.left.min();
            }


        }

        private void max() {
//            Queue<ABinarySearchTreeNode> queue = new LinkedList<>();
//            TreeSet<Integer> sorted = new TreeSet<>((a,b)->ismax?a>b?-1:0:a<b?-1:0);
//            ABinarySearchTreeNode node = this;
//            queue.add(node);
//            while (!queue.isEmpty()){
//                node = queue.poll();
//                sorted.add(node.value);
//                if(!ismax){
//                    if(node.left != null){
//                        queue.add(node.left);
//                    }
//                }else {
//                    if(node.right != null){
//                        queue.add(node.right);
//                    }
//                }
//
//
//            }

            if (this.right == null) {
                System.out.println(this.value);
                return;
            } else {
                this.right.max();
            }


        }

        private boolean insert(int value) {
            if (this.value > value) {
                if (this.left == null) {
                    this.left = new ABinarySearchTreeNode(value);
                    return true;
                } else {
                    return this.left.insert(value);
                }
            } else if (this.value < value) {
                if (this.right == null) {
                    this.right = new ABinarySearchTreeNode(value);
                    return true;
                } else {
                    return this.right.insert(value);
                }
            } else if (this.value == value) {
                System.out.println("SAME VALUE EXISTS");
                return false;
            }

            return false;
        }

        private boolean contains(int value) {
            if(this.value == value) return true;

            if (this.value > value) {
                if(this.left != null){
                    if(this.left.value == value){
                        return  true;
                    }else {
                        return this.left.contains(value);
                    }
                }else return false;
            }else if(this.value<value) {
                if(this.right!=null){
                    if(this.right.value == value){
                        return true;
                    }else {
                        return this.right.contains(value);
                    }
                }
            }else return false;

            return false;
        }

        private ABinarySearchTreeNode(int value) {
            this.value = value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setLeft(ABinarySearchTreeNode left) {
            this.left = left;
        }

        public void setRight(ABinarySearchTreeNode right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public ABinarySearchTreeNode getLeft() {
            return left;
        }

        public ABinarySearchTreeNode getRight() {
            return right;
        }


    }

    public void printBSTree() {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<ABinarySearchTreeNode> level = new ArrayList<ABinarySearchTreeNode>();
        List<ABinarySearchTreeNode> next = new ArrayList<ABinarySearchTreeNode>();

        level.add(this.root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (ABinarySearchTreeNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.toString();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<ABinarySearchTreeNode> tmp = level;
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
}
