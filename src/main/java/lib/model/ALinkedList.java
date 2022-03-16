package lib.model;

public class ALinkedList<T> {

    @Override
    public String toString() {
        return "ALinkedList{" +
                "root=" + root +
                '}';
    }
    private ALinkedListNode<T> root = null;
    private ALinkedList(T val) {
        this.root = new ALinkedListNode<>(val);
    }
    public ALinkedList() {
    }

    public void mergeSorted(ALinkedList<T> sortedLinkedList){
        this.root.merge(this.root,sortedLinkedList.root);

    }

    public void reverse() {

        this.root = this.root.reverse();

    }

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

    public void print(){
        if(this.root == null)return;
        this.root.print();
    }

    /**
     * INNER CLASS
     * @param <T>
     */
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
            if (this.next == null) return this;


            ALinkedListNode<T> cyrr = this.next.reverse();
            this.next.next = this;
            this.next = null;
            return cyrr;
        }

        public ALinkedListNode<T> merge(ALinkedListNode<T> node1, ALinkedListNode<T>node2){
            if(node1 == null) return node2;
            if(node2 == null) return node1;

            if((Integer)node1.data<=(Integer) node2.data){
                node1.next = merge(node1.next,node2);
              //  System.out.println("dataN1:"+node1.data);
                return node1;
            }else {
                node2.next = merge(node1,node2.next);
              //  System.out.println("dataN2:"+node2.data);
                return node2;
            }

        }

        public void print(){
            System.out.println(this.data);
            if(this.next != null){
                this.next.print();
            }
        }
    }

}
