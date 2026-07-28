import java.util.Random;

public class Hero extends GameCharacter{

    public Hero(String name) {
        super(name);
    }

    public Hero(String name, int level, int healthPoints, int magic, int gold) {
        super(name, level, healthPoints, magic, gold);
    }

    @Override
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

    @Override
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

    @Override
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

    public void perish() {
        System.out.println("All is lost, our hero has perished :(");
    }

    @Override
    public String toString() {
        return "Hero{" +
                " Name='" + mName + '\'' +
                ", Level=" + mLevel +
                ", Health Points=" + mHealthPoints +
                ", Magic=" + mMagic +
                ", Gold=" + mGold +
                '}';
    }
}
