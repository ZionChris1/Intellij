import java.io.Serializable;
import java.util.Objects;

public class Meat extends PaleoFood implements Serializable {
    private int mType;
    private int mCookingTemp;

    public Meat(String name, int calories, int type, int cookingTemp) {
        super(name, calories, 0);
        mType = type;
        mCookingTemp = cookingTemp;
    }

    public int getType() {
        return mType;
    }

    public int getCookingTemp() {
        return mCookingTemp;
    }

    public void setType(int type) {
        mType = type;
    }

    public void setCookingTemp(int cookingTemp) {
        mCookingTemp = cookingTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meat meat = (Meat) o;
        return mCookingTemp == meat.mCookingTemp && Objects.equals(mType, meat.mType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mCookingTemp, mType);
    }

    @Override
    public String toString() {
        String header = mType == 1 ? "Meat: " : "Seafood: ";
        return header+ mName + ", "
                + mCalories + " calories, "
                + mCarbs + "g carbs, "
                + mCookingTemp + " degrees F";
    }
}
