import java.io.Serializable;
import java.util.Objects;

public abstract class Cycle implements Serializable{
    protected String mManufacturer;
    protected double mPrice;

    protected Cycle(String manufacturer, double price) {
        this.mManufacturer = manufacturer;
        this.mPrice = price;
    }

    public String getManufacturer() {
        return mManufacturer;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setManufacturer(String manufacturer) {
        this.mManufacturer = manufacturer;
    }

    public void setPrice(double price) {
        this.mPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cycle cycle = (Cycle) o;
        return Double.compare(cycle.mPrice, mPrice) == 0 && Objects.equals(mManufacturer, cycle.mManufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mManufacturer, mPrice);
    }
}
