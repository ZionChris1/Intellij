public class MyClass {

    static MyClass single;

    public MyClass getInstance() {
        if(single != null)
            single = new MyClass();
        return single;
    }
    private MyClass() {

    }
}
