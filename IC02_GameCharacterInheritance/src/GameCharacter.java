import java.util.Random;

public class GameCharacter {
    protected String mName;
    protected int mLevel;
    protected int mHealthPoints;
    protected int mMagic;
    protected int mGold;

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
                " Name='" + mName + '\'' +
                ", Level=" + mLevel +
                ", Health Points=" + mHealthPoints +
                ", Magic=" + mMagic +
                ", Gold=" + mGold +
                '}';
    }

    public void attack(GameCharacter other) {
        System.out.println(mName + " does not attack. I'm peaceful :)");
    }

    public void assist(GameCharacter other) {
        System.out.println(mName + " cannot assist.");
    }

    public void rest() {
        System.out.println(mName + " never rests.");
    }
}
