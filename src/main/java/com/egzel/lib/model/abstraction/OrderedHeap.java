package com.egzel.lib.model.abstraction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Another version of heap structure
 */
public class OrderedHeap {

    public void insert (int val){
        this.root= this.root.insert(val);
        this.root = this.root.shiftUp();

    }

    private OrderedHeapNode root = null;
    public OrderedHeap(int val) {
        if(this.root == null)this.root =new OrderedHeapNode(val);
    }

    private static class OrderedHeapNode {
        private int val;
        private OrderedHeapNode left;
        private OrderedHeapNode right;
        private OrderedHeapNode parent = null;

        public void setParent(OrderedHeapNode parent) {
            this.parent = parent;
        }

        public OrderedHeapNode getParent() {
            return parent;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }


        public void setLeft(OrderedHeapNode left) {
            this.left = left;
        }

        public void setRight(OrderedHeapNode right) {
            this.right = right;
        }

        public OrderedHeapNode getLeft() {
            return left;
        }

        public OrderedHeapNode getRight() {
            return right;
        }

        public OrderedHeapNode(int val) {
            this.val = val;
        }

        private OrderedHeapNode insert(int val) {

            Queue<OrderedHeapNode> queue = new LinkedList<>();
            OrderedHeapNode node = this;
            queue.add(node);
            while (!queue.isEmpty()){
                OrderedHeapNode current = queue.poll();
                if(current.left == null){
                    current.left = new OrderedHeapNode(val);
                    current.left.parent = current;
                    return node;
                }else {
                    queue.add(current.left);
                }

                if(current.right == null){
                    current.right = new OrderedHeapNode(val);
                    current.right.parent = current;
                    return node;
                }else {
                    queue.add(current.right);
                }
            }
            return node;

        }

        private void swapNodes(OrderedHeapNode node1, OrderedHeapNode node2){

        }
        private OrderedHeapNode shiftUp(){

            Queue<OrderedHeapNode> queue = new LinkedList<>();
            OrderedHeapNode node = this;
            queue.add(node);

            while (!queue.isEmpty()){
                OrderedHeapNode current = queue.poll();
                if(current.getParent() != null){
                    if(current.getParent().val < current.val){
                        OrderedHeapNode temp = current;
                        OrderedHeapNode tempParent = current.parent;

                        tempParent.right = temp.right;
                        tempParent.left = temp.left;
                        current.parent = tempParent;

                        temp.right = tempParent.right;
                        temp.left = tempParent.left;
                        current = temp;


                        if(temp.left != null){
                            queue.add(temp.left);
                        }
                        if(temp.right != null){
                            queue.add(temp.right);
                        }
                    }
                }else{
                    if(current.right!=null){
                        queue.add(current.right);
                    }
                    if(current.left != null){
                        queue.add(current.left);
                    }
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

    public void printOrderedHeap() {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<OrderedHeapNode> level = new ArrayList<OrderedHeapNode>();
        List<OrderedHeapNode> next = new ArrayList<OrderedHeapNode>();

        level.add(this.root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (OrderedHeapNode n : level) {
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

            List<OrderedHeapNode> tmp = level;
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
