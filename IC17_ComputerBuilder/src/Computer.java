import java.text.NumberFormat;

public class Computer {
    private CPU mCPU;
    private RAM mRAM;
    private Storage mStorage;
    private VideoCard mVideoCard;
    private NumberFormat currency = NumberFormat.getCurrencyInstance();

    public CPU getCPU() {
        return mCPU;
    }

    public RAM getRAM() {
        return mRAM;
    }

    public Storage getStorage() {
        return mStorage;
    }

    public VideoCard getVideoCard() {
        return mVideoCard;
    }

    public NumberFormat getCurrency() {
        return currency;
    }

    public String toString() {
        return "~~~Computer Specifications~~~\n"
                + mCPU + "\n"
                + mRAM + "\n"
                + mStorage + "\n"
                + mVideoCard + "\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "Total Cost as Configured: " + currency.format(calculateCost());
    }

    public double calculateCost() {
        return mCPU.mPrice + mRAM.mPrice + mStorage.mPrice + mVideoCard.mPrice;
    }

    class CPU {
        private String mManufacturer;
        private String mCore;
        private double mSpeed;
        private double mPrice;

        public CPU(String manufacturer, String core, double speed, double price) {
            mManufacturer = manufacturer;
            mCore = core;
            mSpeed = speed;
            mPrice = price;

            mCPU = this;
        }

        @Override
        public String toString() {
            return "CPU ["
                    + mManufacturer + " "
                    + mCore + " "
                    + mSpeed + "GHz "
                    + currency.format(mPrice) +
                    ']';
        }
    }

    class RAM {
        private double mCapacity;
        private String mManufacturer;
        private double mSpeed;
        private double mPrice;

        public RAM(String manufacturer, double capacity, double speed, double price) {
            mManufacturer = manufacturer;
            mCapacity = capacity;
            mSpeed = speed;
            mPrice = price;

            mRAM = this;
        }

        @Override
        public String toString() {
            return "RAM ["
                    + mManufacturer + " "
                    + mCapacity + " "
                    + mSpeed + " MHz "
                    + currency.format(mPrice) +
                    ']';
        }
    }

    class Storage {
        private String mManufacturer;
        private double mCapacity;
        private double mPrice;
        private String mType;

        public Storage(String manufacturer, double capacity, double price, String type) {
            mCapacity = capacity;
            mManufacturer = manufacturer;
            mPrice = price;
            mType = type;

            mStorage = this;
        }

        @Override
        public String toString() {
            return "Storage ["
                    + mManufacturer + " "
                    + mCapacity + " TB "
                    + mType + " "
                    + currency.format(mPrice) +
                    ']';
        }
    }

    class VideoCard {
        private String mManufacturer;
        private double mCapacity;
        private String mMaxRes;
        private double mPrice;

        public VideoCard(String manufacturer, double capacity, String maxRes, double price) {
            mCapacity = capacity;
            mManufacturer = manufacturer;
            mMaxRes = maxRes;
            mPrice = price;

            mVideoCard = this;
        }

        @Override
        public String toString() {
            return "Video Card ["
                    + mManufacturer + " "
                    + mCapacity + " "
                    + mMaxRes + " pixels "
                    + currency.format(mPrice) +
                    ']';
        }
    }
}
