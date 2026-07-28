import java.io.Serializable;
import java.util.Objects;

public abstract class NaturalDisaster implements Serializable {
    protected String mName;
    protected int mYear;
    protected String mLocation;
    protected double mCost;

    protected NaturalDisaster(String name, int year, String location, double cost) {
        mName = name;
        mYear = year;
        mLocation = location;
        mCost = cost;
    }

    public String getName() {
        return mName;
    }

    public int getYear() {
        return mYear;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getCost() {
        return mCost;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public void setCost(double cost) {
        mCost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaturalDisaster that = (NaturalDisaster) o;
        return mYear == that.mYear && Double.compare(that.mCost, mCost) == 0 && Objects.equals(mName, that.mName) && Objects.equals(mLocation, that.mLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mLocation, mYear, mCost);
    }
}
