import java.text.NumberFormat;
import java.util.Objects;

public class EnergyDrink extends CaffeinatedBeverage {
    private String mFlavor;

    public EnergyDrink(String name, int ounces, double price, String flavor) {
        super(name, ounces, price);
        mFlavor = flavor;
    }

    public String getFlavor() {
        return mFlavor;
    }

    public void setFlavor(String flavor) {
        mFlavor = flavor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EnergyDrink that = (EnergyDrink) o;
        return Objects.equals(mFlavor, that.mFlavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mFlavor);
    }

    @Override
    public String toString() {
        return "Energy Drink: " + mName
                + ", " + mOunces + " ounces"
                + ", " + mFlavor
                + ", " + currency.format(mPrice);
    }
}