import java.text.NumberFormat;

public class PizzaOrder {
    private static final int MAX_PIZZAS = 100;
    private int mNumPies = 0;
    Pizza[] mPies;

    public PizzaOrder() {
        mNumPies = 0;
        mPies = new Pizza[MAX_PIZZAS];
    }

    public boolean addPizzaToOrder(char size, boolean pepperoni, boolean sausage, boolean mushrooms, boolean vegan) {
        //If order is full
        if(mNumPies >= MAX_PIZZAS)
            return false;
        mPies[mNumPies++] = new Pizza(size, pepperoni, sausage, mushrooms, vegan);
        return true;
    }

    public double calcCost() {
        double cost = 0.0;
        for(int i = 0; i < mNumPies; i++) {
            //Add the cost of the pizza size
            switch(mPies[i].getSize()) {
                case 'S':
                case 's':
                    cost += 8.0;
                    break;
                case 'M':
                case 'm':
                    cost += 10.0;
                    break;
                case 'L':
                case 'l':
                    cost += 12.0;
                    break;
            }
            //Add the cost of the toppings
            cost += mPies[i].getNumToppings();
        }
        return cost;
    }

    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String output =  "PizzaOrder: Number of Pies = " + mNumPies + "\n";
        for (int i = 0; i < mNumPies; i++) {
            output += "Pie #:" + (i + 1) + ": " + mPies[i] + "\n";
        }
        output += "Total Cost = " + currency.format(calcCost()) + "\n";
        return output;
    }
}
