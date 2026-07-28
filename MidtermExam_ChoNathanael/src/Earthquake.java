import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Earthquake extends NaturalDisaster implements Serializable {
    private double mMagnitude;

    public Earthquake(String name, int year, String location, double cost, double magnitude) {
        super(name, year, location, cost);
        mMagnitude = magnitude;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(double magnitude) {
        mMagnitude = magnitude;
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        DecimalFormat oneDP = new DecimalFormat("0.0");
        return "Earthquake [" + mName + ", "
                + mYear + ", "
                + mLocation + ", "
                + currency.format(mCost * 1_000_000_000) + ", "
                + oneDP.format(mMagnitude) + " magnitude]";
    }
}
