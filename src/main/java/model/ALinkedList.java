package model;

import java.util.LinkedList;

public class ALinkedList<T> {

    private static class ALinkedListNode<T> {

        @Override
        public String toString() {
            return this.data.toString();
        }

        private T data = null;
        private ALinkedListNode<T> next = null;

        public ALinkedListNode(T data) {
            this.data = data;
        }

        public void setNext(ALinkedListNode<T> next) {
            this.next = next;
        }

        public ALinkedListNode<T> getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public ALinkedListNode<T> reverse() {
            if (this == null) return this;
            if (this.next == null) return this;

            ALinkedListNode<T> cyrr = this.next.reverse();
            this.next.next = this;
            this.next = null;
            return cyrr;
        }

    }
    @Override
    public String toString() {
        return "ALinkedList{" +
                "root=" + root +
                '}';
    }

    private ALinkedList(ALinkedListNode<T> root) {
        this.root = root;
    }

    public ALinkedList() {
    }

    public void reverse() {

        this.root = this.root.reverse();

    }


    private ALinkedListNode<T> root = null;


    public void append(T element) {

        if (root == null) {
            this.root = new ALinkedListNode<>(element);
            return;
        }

        ALinkedListNode<T> current = root;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(new ALinkedListNode<>(element));

    }

    public T poll() {

        if (this.root == null) return null;
        T res = this.root.getData();
        this.root = this.root.getNext();
        return res;
    }

    public void push(T element) {
        if (this.root == null) {
            this.root = new ALinkedListNode<>(element);
            return;
        }
        ALinkedListNode<T> current = new ALinkedListNode<>(element);
        current.next = root;
        root = current;
    }
}
