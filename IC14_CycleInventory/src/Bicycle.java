import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public class Bicycle extends Cycle implements Serializable{
    private int mFrameSize;
    private int mCranks;

    public Bicycle(String manufacturer, double price, int frameSize, int cranks) {
        super(manufacturer, price);
        mFrameSize = frameSize;
        mCranks = cranks;
    }

    public int getFrameSize() {
        return mFrameSize;
    }

    public int getCranks() {
        return mCranks;
    }

    public void setFrameSize(int frameSize) {
        mFrameSize = frameSize;
    }

    public void setCranks(int cranks) {
        mCranks = cranks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bicycle bicycle = (Bicycle) o;
        return mFrameSize == bicycle.mFrameSize && mCranks == bicycle.mCranks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mFrameSize, mCranks);
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Bicycle[ " + mManufacturer + ", "
                + currency.format(mPrice) + ", " + mFrameSize + "\" frame, "
                + mCranks + " gears]";
    }
}
