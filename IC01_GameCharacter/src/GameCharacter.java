import java.util.Random;

public class GameCharacter {
    private String mName;
    private int mLevel;
    private int mHealthPoints;
    private int mMagic;
    private int mGold;

    public GameCharacter(String name) {
        this(name, 1, 100, 100, 100);
    }

    public GameCharacter(String name, int level, int healthPoints, int magic, int gold) {
        mName = name;
        mLevel = level;
        mHealthPoints = healthPoints;
        mMagic = magic;
        mGold = gold;
    }

    public String getName() {
        return mName;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getHealthPoints() {
        return mHealthPoints;
    }

    public int getMagic() {
        return mMagic;
    }

    public int getGold() {
        return mGold;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "Game Character{" +
                "Name='" + mName + '\'' +
                ", Level=" + mLevel +
                ", Health Points=" + mHealthPoints +
                ", Magic=" + mMagic +
                ", Gold=" + mGold +
                '}';
    }

    public void attack(GameCharacter other) {
        Random rng = new Random();
        int rand = rng.nextInt(10);

        int damage = this.mLevel * rand;
        other.mHealthPoints -= damage;

        //Don't let health go below 0
        if(other.mHealthPoints <= 0) {
            other.mHealthPoints = 0;
            System.out.println("The character: " + other.mName + " has perished.");
        }
    }

    public void assist(GameCharacter other) {
        Random rng = new Random();
        int rand = rng.nextInt(5);

        switch(rand) {
            case 0: //Health
                other.mHealthPoints += this.mLevel * 5;
                this.mHealthPoints -= this.mLevel * 5;
                break;
            case 1: //Magic
                other.mMagic += this.mLevel * 5;
                this.mMagic -= this.mLevel * 5;
                break;
            case 2: //Gold
                other.mGold += this.mLevel * 5;
                this.mGold -= this.mLevel * 5;
                break;
            case 3: //Level
                other.mLevel++;
                this.mLevel--;
                other.mHealthPoints += 100;
                this.mHealthPoints -= 100;
                other.mMagic += 100;
                this.mMagic -= 100;
                other.mGold += 100;
                this.mGold -= 100;
                break;
            case 4: //Restore
                other.mHealthPoints = other.getLevel() * 100;
                this.mHealthPoints = this.mLevel * 100;
                break;
        }
    }

    public void rest() {
        Random rng = new Random();
        int rand = rng.nextInt(10);
        if(rand > 1) { //80 percent change character rests successfully
            //Restore characters health and magic
            if(this.mHealthPoints < this.mLevel * 100)
                this.mHealthPoints = this.mLevel * 100;
            if(this.mMagic < this.mLevel * 100)
                this.mMagic = this.mLevel * 100;
        }
        else if(rand > 0) { //10 percent chance character gets attacked
            //Calculate how much health was lost
            rand = rng.nextInt(10) + 1;
            this.mHealthPoints -= rand;
        }
        else { //10 percent chance character gets robbed
            //Calculate how much gold was lost
            rand = rng.nextInt(10) + 1;
            mGold -= rand;
        }
    }
}
