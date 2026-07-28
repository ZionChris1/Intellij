import java.util.Arrays;

public class CatDemo {
    public static void main(String[] args) {
        // Let's make an array of Cats (our CS 112 feline friends)
        Cat[] clowder = new Cat[5];
        clowder[0] = new Cat("Russell", "Snowshoe", 13);
        clowder[1] = new Cat("Euler", "British Shorthair", 1);
        clowder[2] = new Cat("Imbi", "Unknown", 3);
        clowder[3] = new Cat("Smokey", "Russian Blue", 8);
        clowder[4] = new Cat("Jack", "American Shorthair", 13);

        System.out.println("\n~~~Unsorted Cats~~~\n");
        for (int i = 0; i < clowder.length; i++) {
            System.out.println(clowder[i]);
        }

        Arrays.sort(clowder);

        System.out.println("\n~~~Sorted Cats~~~\n");
        for (int i = 0; i < clowder.length; i++) {
            System.out.println(clowder[i]);
        }

    }
}