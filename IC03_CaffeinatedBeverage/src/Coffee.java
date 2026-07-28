import java.text.NumberFormat;
import java.util.Objects;

public class Coffee extends CaffeinatedBeverage{

    private String mRoastType;

    public Coffee(String name, int ounces, double price, String roastType) {
        super(name, ounces, price);
        mRoastType = roastType;
    }

    public String getRoastType() {
        return mRoastType;
    }

    public void setRoastType(String roastType) {
        mRoastType = roastType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(mRoastType, coffee.mRoastType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mRoastType);
    }

    @Override
    public String toString() {
        return "Coffee: " + mName
                + ", " + mOunces + " ounces"
                + ", " + mRoastType + " roast"
                + ", " + currency.format(mPrice);
    }
}
