import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class Motorcycle extends Cycle implements Serializable{
    private int mDisplacement;
    private double mFuelCapacity;

    public Motorcycle(String manufacturer, double price, int displacement, double fuelCapacity) {
        super(manufacturer, price);
        mDisplacement = displacement;
        mFuelCapacity = fuelCapacity;
    }

    public int getDisplacement() {
        return mDisplacement;
    }

    public double getFuelCapacity() {
        return mFuelCapacity;
    }

    public void setDisplacement(int displacement) {
        mDisplacement = displacement;
    }

    public void setFuelCapacity(double fuelCapacity) {
        mFuelCapacity = fuelCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorcycle that = (Motorcycle) o;
        return mDisplacement == that.mDisplacement && Double.compare(that.mFuelCapacity, mFuelCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mDisplacement, mFuelCapacity);
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        DecimalFormat oneDP = new DecimalFormat("0.0");
        return "Motorcycle [" + mManufacturer + ", "
                + currency.format(mPrice) + ", "
                + mDisplacement + " cc, "
                + oneDP.format(mFuelCapacity) + " gallons]";
    }
}
