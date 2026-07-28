import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Hurricane extends NaturalDisaster implements Serializable {
    private int mMaxWindSpeed;

    public Hurricane(String name, int year, String location, double cost, int maxWindSpeed) {
        super(name, year, location, cost);
        mMaxWindSpeed = maxWindSpeed;
    }

    public int getMaxWindSpeed() {
        return mMaxWindSpeed;
    }

    public void setMaxWindSpeed(int maxWindSpeed) {
        mMaxWindSpeed = maxWindSpeed;
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Hurricane [" + mName + ", "
                + mYear + ", "
                + mLocation + ", "
                + currency.format(mCost * 1_000_000_000) + ", "
                + mMaxWindSpeed + " mph]";
    }
}
