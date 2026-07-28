import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class CommonStock extends Security implements Comparable<Security>, Valuation {
    private double mCurrentPrice;
    private double mPurchasePrice;
    private int mShares;

    public CommonStock(String ISIN, String issuer, double currentPrice, double purchasePrice, int shares) {
        super(ISIN, issuer);
        mCurrentPrice = currentPrice;
        mPurchasePrice = purchasePrice;
        mShares = shares;
    }

    @Override
    public double percentReturn() {
        return (mCurrentPrice - mPurchasePrice) / mPurchasePrice * 100;
    }

    @Override
    public double totalReturn() {
        return (mCurrentPrice - mPurchasePrice) * mShares;
    }

    @Override
    public int compareTo(Security o) {
        //Check if o is a commonStock
        if(o instanceof CommonStock other) {
            //Compare issuers
            int issuerComp = mIssuer.compareTo(other.mIssuer);
            if (issuerComp != 0) return issuerComp;

            //Compare current prices
            int currentComp = Double.compare(mCurrentPrice, other.mCurrentPrice);
            if(currentComp != 0) return currentComp;

            //Compare purchase prices
            int purchaseComp = Double.compare(mPurchasePrice, other.mPurchasePrice);
            if(purchaseComp != 0) return currentComp;

            //Compare shares
            int sharesComp = mShares - other.mShares;
            if(sharesComp != 0) return sharesComp;

            return mISIN.compareTo(other.mISIN);
        }
        return super.compareTo(o);
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public double getPurchasePrice() {
        return mPurchasePrice;
    }

    public int getShares() {
        return mShares;
    }

    public void setCurrentPrice(double currentPrice) {
        mCurrentPrice = currentPrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        mPurchasePrice = purchasePrice;
    }

    public void setShares(int shares) {
        mShares = shares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommonStock that = (CommonStock) o;
        return Double.compare(that.mCurrentPrice, mCurrentPrice) == 0 && Double.compare(that.mPurchasePrice, mPurchasePrice) == 0 && mShares == that.mShares;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mCurrentPrice, mPurchasePrice, mShares);
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        DecimalFormat twoDP = new DecimalFormat("0.00");
        String totalReturn = "";

        //Add parentheses if needed to totalReturn
        if(totalReturn() >= 0)
            totalReturn += currency.format(totalReturn());
        else
            totalReturn += "(" + currency.format(Math.abs(totalReturn())) + ")";

        return "CommonStock ["
                + mISIN + ", "
                + mIssuer + ", "
                + "Purchase Price: " + currency.format(mPurchasePrice) + ", "
                + "Current: " + currency.format(mCurrentPrice) + ", "
                + "Shares: " + mShares + ", "
                + "Total Return: " + totalReturn + ", "
                + "Percent Return: " + twoDP.format(percentReturn()) + "%]";
    }
}
