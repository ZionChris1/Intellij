public class Main {
    public static void main(String[] args) {
        LinkedList<String> groceries = new LinkedList<>();

        System.out.println("~~~Two items added~~~");
        groceries.add("Reed avocados");
        groceries.add("Durian fruit");
        System.out.println(groceries);

        System.out.println("\n~~~Two items added at index 0~~~");
        groceries.add(0, "Kobe Filet Mignon");
        groceries.add(0, "Miracle berries");
        System.out.println(groceries);

        System.out.println("\n~~~Durian fruit removed~~~");
        groceries.remove("Durian fruit");
        System.out.println(groceries);

        System.out.println("\n~~~Item at index 0 removed~~~");
        groceries.remove(0);
        System.out.println(groceries);

        System.out.println("\n~~~First index set to Guacamole~~~");
        groceries.set("Guacamole", 1);
        System.out.println(groceries);
    }
}
