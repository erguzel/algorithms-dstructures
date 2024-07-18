package com.egzel.lib.model;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

import com.egzel.lib.model.abstraction.IBSTreeNode;
import com.egzel.lib.model.abstraction.IBinaryTreeNode;


public class BSTreeNode implements IBSTreeNode {

    public IBinaryTreeNode getExtremum(boolean min) {

        IBSTreeNode extremum = this;
        IBSTreeNode nod = null;


        Stack<IBSTreeNode> stack = new Stack<>();
        stack.add(this);

        while (!stack.isEmpty()) {

            nod = stack.pop();

            if (min) {

                if (nod.getValue() < extremum.getValue()) {
                    extremum = nod;
                }

                if (nod.getLeft() != null) {
                    stack.add((IBSTreeNode) nod.getLeft());
                }

            } else {
                if (nod.getValue() > extremum.getValue()) {
                    extremum = nod;
                }

                if (nod.getRight() != null) {
                    stack.add( (IBSTreeNode) nod.getRight());
                }

            }

        }

        return extremum;


    }

    public boolean containsbfs(IBinaryTreeNode node) {

        Queue<IBSTreeNode> queue = new LinkedList<>();
        queue.add((IBSTreeNode) node);

        while (!queue.isEmpty()) {
            IBSTreeNode current =  queue.poll();
            if (current.getValue() == node.getValue()) {
                return true;
            } else {

                boolean currentNull = current == null;
                boolean currentLess = current.getValue() < node.getValue();

                if (!currentNull) {
                    if (currentLess) {
                        if (current.getLeft() != null) {
                            queue.add( (IBSTreeNode) current.getLeft());
                        }
                    } else {
                        if (current.getRight() != null) {
                            queue.add((IBSTreeNode) current.getRight());
                        }
                    }
                }


            }

        }

        return false;
    }

    public void insert(IBinaryTreeNode node) {

        if (node.getValue() < this.value) {
            if (this.left == null) {
                this.left = (IBSTreeNode) node;
            } else {
                this.left.insert(node);
            }
        } else {
            if (this.right == null) {
                this.right = (IBSTreeNode) node;
            } else {
                this.right.insert(node);
            }
        }

    }

    private IBSTreeNode left = null;
    private IBSTreeNode right = null;
    private double value;

    private int hashcode;
    public BSTreeNode(double value) {
        this.value = value;
        this.hashcode = Objects.hash(value);

    }

    public int fibotest(int till){


        callCount++;
        if(till == 0){
            return 0;
        }else  if(till == 1){
            return 1;
        }
        else {
            return fibotest(till-1)+fibotest(till-2);
        }
    }

    public  static int callCount = 0;
    static int printPos = 10;

    public double getExtremumRecursive(boolean isMax){

        callCount++;
        if(isMax){
            return Math.max(this.value,
                    (this.right == null?Double.MIN_VALUE:this.right.getExtremumRecursive(isMax)));
        }else {
            return Math.min(this.value,
                    (this.left == null?Double.MAX_VALUE:this.left.getExtremumRecursive(isMax)));
        }

    }

    public double sumRecursiveTernary() {
        callCount++;

        return this.value + (this.left == null ? 0.0 : this.left.sumRecursiveTernary()) +
                (this.right == null ? 0.0 : this.right.sumRecursiveTernary());
    }

    public double sumRecursiveNormal(){

        callCount++;

        if(this.left != null ){
            this.value = this.value + this.left.sumRecursiveNormal();
        }else if(this.right != null){
            this.value = this.value + this.right.sumRecursiveNormal();
        }
        else {
            return sumRecursiveNormal();
        }

        return this.value;
    }

    public boolean contains(IBinaryTreeNode node) {
        if (node == null) return false;
        if (this.value == node.getValue()) return true;

        if (this.left != null) return this.left.contains(node);
        if (this.right != null) return this.right.contains(node);
        return false;

    }



    @Override
    public void setLeft(IBinaryTreeNode left) {
        this.left = (IBSTreeNode) left;
    }


    @Override
    public void setRight(IBinaryTreeNode right) {
        this.right = (IBSTreeNode) right;
    }


    @Override
    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public IBSTreeNode getLeft() {
        return left;
    }

    @Override
    public IBSTreeNode getRight() {
        return right;
    }


    public double getValue() {
        return value;
    }

    public String stringifySubtree(){

        return this.toString()+"\n"+
                (this.left == null? "":String.valueOf(this.left.stringifySubtree()))+
                (this.right == null? "":String.valueOf(this.right.stringifySubtree()));




    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
        IBSTreeNode that = (IBSTreeNode) o;
        return this.value == that.getValue();
    }

    @Override
    public int hashCode() {
        int val = (int) this.value ;
       return val;
    }

}
