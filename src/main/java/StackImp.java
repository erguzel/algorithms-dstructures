import java.util.Stack;

public class StackImp <T>{

    public T[] val = null;

    public   int size = 0;


    @SuppressWarnings("unchecked")
    public StackImp(int val) {
        this.size = val;
        this.val = (T[]) new Object[size];
    }

    public int size(){

        return  this.val.length;
    }

    public void add(T element){

        this.val[0] = element;
    }

    public T get(){
        return this.val[0];
    }


    public static void main(String[] args) {


        TestClass testClass = new TestClass(1,"asd",true);
        StackImp<TestClass> asd = new StackImp<>(3);

        asd.add(testClass);
        TestClass tt= asd.get();
        System.out.println(tt.getValstring());

    }
}
