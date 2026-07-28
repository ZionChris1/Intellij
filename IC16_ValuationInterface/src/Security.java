import java.util.Objects;

public abstract class Security implements Comparable<Security>, Valuation {
    protected String mISIN;
    protected String mIssuer;

    protected Security(String ISIN, String issuer) {
        mISIN = ISIN;
        mIssuer = issuer;
    }

    public String getISIN() {
        return mISIN;
    }

    public String getIssuer() {
        return mIssuer;
    }

    public void setISIN(String ISIN) {
        mISIN = ISIN;
    }

    public void setIssuer(String issuer) {
        mIssuer = issuer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Security security = (Security) o;
        return Objects.equals(mISIN, security.mISIN) && Objects.equals(mIssuer, security.mIssuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mISIN, mIssuer);
    }

    @Override
    public int compareTo(Security other) {

        //Compare issuers
        int issuerComp = mIssuer.compareTo(other.mIssuer);
        if (issuerComp != 0) return issuerComp;

        //Compare ISINs
        return mISIN.compareTo(other.mISIN);
    }
}
