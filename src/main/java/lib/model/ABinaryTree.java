package lib.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ABinaryTree {

    public void insert(int val) {
        if (this.root == null)
            this.root = new ABinaryTreeNode(val);

        this.root = this.root.insert(val);
    }

    public boolean isSymmetric() {

        return this.root.isSymmetric();
    }

    private ABinaryTreeNode root = null;

    public ABinaryTree() {
    }

    public ABinaryTree(int rootVal) {
        this.root = new ABinaryTreeNode(rootVal);
    }


    private static class ABinaryTreeNode {
        private int val;
        private ABinaryTreeNode left;
        private ABinaryTreeNode right;

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }


        public void setLeft(ABinaryTreeNode left) {
            this.left = left;
        }

        public void setRight(ABinaryTreeNode right) {
            this.right = right;
        }

        public ABinaryTreeNode getLeft() {
            return left;
        }

        public ABinaryTreeNode getRight() {
            return right;
        }

        public ABinaryTreeNode(int val) {
            this.val = val;
        }

        private ABinaryTreeNode insert(int val) {

            Queue<ABinaryTreeNode> queue = new LinkedList<>();
            ABinaryTreeNode node = this;
            queue.add(node);
            while (!queue.isEmpty()){
                ABinaryTreeNode current = queue.poll();
                if(current.left == null){
                    current.left = new ABinaryTreeNode(val);
                    return node;
                }else {
                    queue.add(current.left);
                }

                if(current.right == null){
                    current.right = new ABinaryTreeNode(val);
                    return node;
                }else {
                    queue.add(current.right);
                }
            }
            return node;

        }

        private boolean isSymmetric() {

            if (this.left != null && this.right != null) {
                return this.left.val == this.right.val;
            } else {
                if (this.left != null) {
                    this.left.isSymmetric();
                } else if (this.right != null) {
                    this.right.isSymmetric();
                }
            }

            return false;
        }
    }

    public void printBSTree() {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<ABinaryTree.ABinaryTreeNode> level = new ArrayList<ABinaryTree.ABinaryTreeNode>();
        List<ABinaryTree.ABinaryTreeNode> next = new ArrayList<ABinaryTree.ABinaryTreeNode>();

        level.add(this.root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (ABinaryTree.ABinaryTreeNode n : level) {
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

            List<ABinaryTree.ABinaryTreeNode> tmp = level;
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
