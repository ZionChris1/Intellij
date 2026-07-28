public class Main {
    public static void main(String[] args) {
        LinkedList<String> courses = new LinkedList<>();
        
        courses.add("CS 111");
        courses.add("CS 112");
        courses.add("CS 113");
        courses.add("CS 220");
        courses.add("CS 226");
        courses.add("CS 292");
        courses.add("Math 126S");
        courses.add("Chem 140");
        
        LinkedList<String>.ListIterator li = courses.new ListIterator();

        //Print list forwards
        System.out.println("~~~List printed forwards~~~");
        while(li.hasNext()) {
            System.out.println(li.next());
        }

        //Print list backwards
        System.out.println("\n~~~List printed backwards~~~");
        while(li.hasPrevious()) {
            System.out.println(li.previous());
        }
    }
}
