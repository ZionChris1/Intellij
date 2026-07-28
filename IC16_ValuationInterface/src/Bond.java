import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;

public class Bond extends Security implements Comparable<Security>, Valuation {
    private double mCouponRate;
    private double mHoldingPeriod;
    private double mPrincipal;

    public Bond(String ISIN, String issuer, double couponRate, double holdingPeriod, double principal) {
        super(ISIN, issuer);
        mCouponRate = couponRate;
        mHoldingPeriod = holdingPeriod;
        mPrincipal = principal;
    }

    public double getCouponRate() {
        return mCouponRate;
    }

    public double getHoldingPeriod() {
        return mHoldingPeriod;
    }

    public double getPrincipal() {
        return mPrincipal;
    }

    public void setCouponRate(double couponRate) {
        mCouponRate = couponRate;
    }

    public void setHoldingPeriod(double holdingPeriod) {
        mHoldingPeriod = holdingPeriod;
    }

    public void setPrincipal(double principal) {
        mPrincipal = principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bond bond = (Bond) o;
        return Double.compare(bond.mCouponRate, mCouponRate) == 0 && Double.compare(bond.mHoldingPeriod, mHoldingPeriod) == 0 && Double.compare(bond.mPrincipal, mPrincipal) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mCouponRate, mHoldingPeriod, mPrincipal);
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

        return "Bond ["
                + mISIN + ", "
                + mIssuer + ", "
                + "Principal: " + currency.format(mPrincipal) + ", "
                + "Coupon: " + twoDP.format(mCouponRate * 100) + "%, "
                + "Holding: " + mHoldingPeriod + " years, "
                + "Total Return: " + totalReturn + ", "
                + "Percent Return: " + twoDP.format(percentReturn() * 100) + "%]";
    }

    @Override
    public double percentReturn() {
        return mCouponRate * mHoldingPeriod;
    }

    @Override
    public double totalReturn() {
        return mPrincipal * mCouponRate * mHoldingPeriod;
    }

    @Override
    public int compareTo(Security o) {
        //Check if o is a commonStock
        if(o instanceof Bond other) {
            //Compare issuers
            int issuerComp = mIssuer.compareTo(other.mIssuer);
            if (issuerComp != 0) return issuerComp;

            //Compare principal
            int principalComp = Double.compare(mPrincipal, other.mPrincipal);
            if(principalComp != 0) return principalComp;

            //Compare coupon rates
            int couponComp = Double.compare(mCouponRate, other.mCouponRate);
            if(couponComp != 0) return couponComp;

            //Compare holding periods
            int holdingComp = Double.compare(mHoldingPeriod, other.mHoldingPeriod);
            if(holdingComp != 0) return holdingComp;

            return mISIN.compareTo(other.mISIN);
        }
        return super.compareTo(o);
    }
}
