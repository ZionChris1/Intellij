public class HugeNumber {
    private LinkedList<String> mDigits;

    public HugeNumber() {
        mDigits = new LinkedList<>();
    }

    public void addDigit(String digit) {
        mDigits.add(digit);
    }

    public void resetNumber() {
        mDigits.clear();
    }

    public String toString() {
        String output = "";
        
        //Concatenate all digits to output
        for(long i = 0; i < mDigits.size(); i++) {
            output += mDigits.get(i);
        }

        return output;
    }
}