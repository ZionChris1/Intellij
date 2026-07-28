import java.text.NumberFormat;
import java.util.Objects;

public abstract class CaffeinatedBeverage {

    protected String mName;
    protected int mOunces;
    protected double mPrice;
    protected NumberFormat currency = NumberFormat.getCurrencyInstance();

    protected CaffeinatedBeverage(String name, int ounces, double price) {
        mName = name;
        mOunces = ounces;
        mPrice = price;
    }

    public String getName() {
        return mName;
    }

    public int getOunces() {
        return mOunces;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setOunces(int ounces) {
        mOunces = ounces;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaffeinatedBeverage that = (CaffeinatedBeverage) o;
        return mOunces == that.mOunces && Double.compare(that.mPrice, mPrice) == 0 && Objects.equals(mName, that.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mOunces, mPrice);
    }
}
