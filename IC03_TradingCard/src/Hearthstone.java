import java.util.Objects;
import java.text.NumberFormat;

public class Hearthstone extends TradingCard{
    private String mCardClass;
    private boolean mGolden;

    public Hearthstone(String name, double price, String rarity, String cardClass, boolean golden) {
        super(name, price, rarity);
        mCardClass = cardClass;
        mGolden = golden;
    }

    public String getCardClass() {
        return mCardClass;
    }

    public void setCardClass(String cardClass) {
        mCardClass = cardClass;
    }

    public boolean isGolden() {
        return mGolden;
    }

    public void setGolden(boolean golden) {
        mGolden = golden;
    }

    @Override
    public String toString() {
        return "Hearthstone[" +
                "Name=" + mName +
                ", Price=" + currency.format(mPrice) +
                ", Rarity=" + mRarity +
                ", Card Class=" + mCardClass +
                ", Golden=" + mGolden +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hearthstone that = (Hearthstone) o;
        return mGolden == that.mGolden && Objects.equals(mCardClass, that.mCardClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mCardClass, mGolden);
    }
}
