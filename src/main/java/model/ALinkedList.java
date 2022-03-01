package model;

public class ALinkedList<T> {

    private static class ALinkedListNode<T> {
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
    }

    private ALinkedListNode<T> root = null;

    private ALinkedList(ALinkedListNode<T> root) {
        this.root = root;
    }

    public ALinkedList() {
    }

    public void append(T element) {

        if(root == null){
            this.root = new ALinkedListNode<>(element);
            return;
        }

        ALinkedListNode<T> current = root;

        while (current.getNext() != null){
            current = current.getNext();
        }

        current.setNext(new ALinkedListNode<>(element));

    }

    public T poll(){

        if(this.root == null)return null;
        T res = this.root.getData();
        this.root = this.root.getNext();
        return res;
    }

    public void push(T element){
        if(this.root == null){
            this.root = new ALinkedListNode<>(element);
            return;
        }
        ALinkedListNode<T> current = new ALinkedListNode<>(element);
        current.next = root;
        root = current;

    }
}
