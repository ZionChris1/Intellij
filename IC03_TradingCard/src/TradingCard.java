import java.text.NumberFormat;
import java.util.Objects;

public abstract class TradingCard {
    protected String mName;
    protected double mPrice;
    protected String mRarity;

    NumberFormat currency = NumberFormat.getCurrencyInstance();

    public TradingCard(String name, double price, String rarity) {
        mName = name;
        mPrice = price;
        mRarity = rarity;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public String getRarity() {
        return mRarity;
    }

    public void setRarity(String rarity) {
        mRarity = rarity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingCard that = (TradingCard) o;
        return Double.compare(that.mPrice, mPrice) == 0 && Objects.equals(mName, that.mName) && Objects.equals(mRarity, that.mRarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mPrice, mRarity);
    }
}
