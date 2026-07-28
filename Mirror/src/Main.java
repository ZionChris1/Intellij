import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        Scanner keyboard = new Scanner(System.in);
        Class<Class> c = Class.class;
        while(true) {
            switch (keyboard.nextLine()) {
                case "a":
                    System.out.println(c);
                case "b":
                    for (Method m : c.getMethods()) {
                        System.out.println(m);
                    }
                    break;
                case "c":
                    Constructor<Class> m = c.getConstructor();
                    System.out.println(m);
                    break;
            }
        }
    }
}