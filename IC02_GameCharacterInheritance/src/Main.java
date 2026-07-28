public class Main {
    public static void main(String[] args) {
        //Create game characters
        Hero me = new Hero("Nathanael Dovahkiin", 5, 500, 500, 500);
        GameCharacter tree = new GameCharacter("Tall Tree");
        Villain villain = new Villain("Alduin", 6, 600, 600, 600);

        //Print initial character states
        System.out.println("~~~The game has begun~~~");
        System.out.println(me);
        System.out.println(tree);
        System.out.println(villain);

        //Call attack and print states
        System.out.println("\n~~~" + me.getName() + " is attacking " + villain.getName() + "~~~");
        me.attack(villain);
        System.out.println(me);
        System.out.println(tree);
        System.out.println(villain);

        //Call assist and print states
        System.out.println("\n~~~" + villain.getName() + " is assisting Himself~~~");
        villain.assist(villain);
        System.out.println(me);
        System.out.println(tree);
        System.out.println(villain);

        //Call rest for everyone and print states
        System.out.println("\n~~~Everyone has rested~~~");
        me.rest();
        tree.rest();
        villain.rest();
        System.out.println(me);
        System.out.println(tree);
        System.out.println(villain);
    }
}