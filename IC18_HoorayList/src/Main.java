package src;

public class Main {
    public static void main(String[] args) {
    HoorayList<String> names = new HoorayList<>();
    names.add("Sam");       // 0
    names.add("Parisa");    // 1
    names.add("Giselle");   // 2
    names.add("Ashley");    // 3
    // "Shahab"
    names.add("Ben");       // 5
    names.add("Kevin");     // 6
    names.add("Sergio");    // 7
    names.add("Edgar");     // 8
    names.add("Michael");   // 9
    names.add("Carson");    // 10


    // mSize =     10
    // mCapacity = 10

    names.add(4, "Shahab");

    // mSize =     11
    // mCapacity = 20

    System.out.println(names);


    HoorayList<String> words = new HoorayList<>();

    //Print empty
    System.out.println("\n~~~Empty HoorayList~~~");
    System.out.println(words);
    System.out.println();

    //Add to end and print
    words.add("Aloha");
    words.add("Goodbye");
    System.out.println("~~~After adding Aloha and Goodbye~~~");
    System.out.println(words);
    System.out.println();

    //Add at index and print
    words.add(0, "Hola");
    words.add(0, "Hello");
    System.out.println("~~~After adding Hola and Hello at index 0~~~");
    System.out.println(words);
    System.out.println();

    //Remove object and print
    words.remove("Goodbye");
    System.out.println("~~~After removing Goodbye~~~");
    System.out.println(words);
    System.out.println();

    //Remove index and print
    words.remove(0);
    System.out.println("~~~After removing element at index 0~~~");
    System.out.println(words);
    System.out.println();

    //Set and print
    words.set(1, "Adios");
    System.out.println("~~~After setting index 0 to Adios~~~");
    System.out.println(words);
    System.out.println();
    }
}