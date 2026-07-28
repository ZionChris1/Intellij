import java.util.Objects;
import java.text.NumberFormat;

public class Pokemon extends TradingCard{
    private String mEnergyType;

    private int mHitPoints;

    public Pokemon(String name, double price, String rarity, String energyType, int hitPoints) {
        super(name, price, rarity);
        mEnergyType = energyType;
        mHitPoints = hitPoints;
    }

    public String getEnergyType() {
        return mEnergyType;
    }

    public void setEnergyType(String energyType) {
        mEnergyType = energyType;
    }

    public int getHitPoints() {
        return mHitPoints;
    }

    public void setHitPoints(int hitPoints) {
        mHitPoints = hitPoints;
    }

    @Override
    public String toString() {
        return "Pokemon[" +
                "Name=" + mName +
                ", Price=" + currency.format(mPrice) +
                ", Rarity=" + mRarity +
                ", Energy Type=" + mEnergyType +
                ", Hit Points=" + mHitPoints +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pokemon pokemon = (Pokemon) o;
        return mHitPoints == pokemon.mHitPoints && Objects.equals(mEnergyType, pokemon.mEnergyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mEnergyType, mHitPoints);
    }
}
