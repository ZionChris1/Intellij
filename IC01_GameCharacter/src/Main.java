public class Main {
    public static void main(String[] args) {
        //Create game characters
        GameCharacter me = new GameCharacter("Nathanael Dovahkiin");
        GameCharacter gandalf = new GameCharacter("Gandalf the Grey", 10, 1000, 1000, 1000);
        GameCharacter villain = new GameCharacter("Monster Bug", 20, 2000, 2000, 2000);

        //Print initial character states
        System.out.println("~~~The game has begun~~~");
        System.out.println(me);
        System.out.println(gandalf);
        System.out.println(villain);

        //Call assist and print states
        me.assist(gandalf);
        System.out.println("\n\n~~~" + me.getName() + " is assisting " + gandalf.getName() + "~~~");
        System.out.println(me);
        System.out.println(gandalf);
        System.out.println(villain);

        //Call attack and print states
        gandalf.attack(villain);
        System.out.println("\n\n~~~" + gandalf.getName() + " is attacking " + villain.getName() + "~~~");
        System.out.println(me);
        System.out.println(gandalf);
        System.out.println(villain);

        //Call rest and print states
        me.rest();
        gandalf.rest();
        System.out.println("\n\n~~~" + me.getName() + " and " + gandalf.getName() + " have rested~~~");
        System.out.println(me);
        System.out.println(gandalf);
        System.out.println(villain);
    }
}