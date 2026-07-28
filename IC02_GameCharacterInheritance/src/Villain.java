import java.util.Random;

public class Villain extends GameCharacter{
    public Villain(String name) {
        super(name);
    }

    public Villain(String name, int level, int healthPoints, int magic, int gold) {
        super(name, level, healthPoints, magic, gold);
    }

    @Override
    public void attack(GameCharacter other) {
        Random rng = new Random();
        int rand = rng.nextInt(12);
        int damage = this.mLevel * rand;
        if(rand == 0) {      //Critical miss
            this.mHealthPoints -= this.mLevel;
        } if(rand < 11) {    //Normal attack
            other.mHealthPoints -= damage;
        } else {             //Critical hit
            other.mHealthPoints -= mLevel * 20;
        }


        //Don't let health go below 0
        if(other.mHealthPoints <= 0) {
            other.mHealthPoints = 0;
            System.out.println("The character: " + other.mName + " has perished.");
        }
    }

    @Override
    public void assist(GameCharacter other) {
        Random rng = new Random();
        boolean choice = rng.nextBoolean();

        //Restore 10 * rand health and subtract 0 * rand from either magic or gold
        int rand = rng.nextInt(10);
        other.mHealthPoints += rand * mLevel;
        if(choice) {
            mMagic -= rand * mLevel;
        } else {
            mGold -= rand * mLevel;
        }
    }

    @Override
    public void rest() {
        System.out.println("Villains never rest! Are you kidding me? We have too many nefarious things to do!");
    }

    public void perish() {
        System.out.println("Humanity has been restored!  The villain has perished.");
    }

    @Override
    public String toString() {
        return "Villain{" +
                " Name='" + mName + '\'' +
                ", Level=" + mLevel +
                ", Health Points=" + mHealthPoints +
                ", Magic=" + mMagic +
                ", Gold=" + mGold +
                '}';
    }
}
