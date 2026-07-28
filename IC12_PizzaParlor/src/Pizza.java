public class Pizza {
    private char mSize;
    private boolean mPepperoni;
    private boolean mSausage;
    private boolean mMushrooms;
    private boolean mVegan;

    public Pizza(char size, boolean pepperoni, boolean sausage, boolean mushrooms, boolean vegan){
        mSize = size;
        mPepperoni = pepperoni;
        mSausage = sausage;
        mMushrooms = mushrooms;
        mVegan = vegan;
    }

    public char getSize() {
        return mSize;
    }

    public int getNumToppings() {
        int count = 0;
        if(mPepperoni)
            count++;
        if(mMushrooms)
            count++;
        if(mSausage)
            count++;
        if(mVegan)
            count++;
        return count;
    }

    @Override
    public String toString() {
        String output = "Pizza[Size=" + mSize;

        if(mPepperoni)
            output += ", Pepperoni";
        if(mSausage)
            output += ", Sausage";
        if(mMushrooms)
            output += ", Mushrooms";
        if(mVegan)
            output += ", Vegan";

        output += ']';
        return output;
    }
}
