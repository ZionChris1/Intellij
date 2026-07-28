
import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        Scanner keyboard = new Scanner(System.in);
        Cycle[] inventory = new Cycle[10];
        int choice, count = 0, frameSize, cranks, displacement;
        double price, fuelCapacity;
        String manufacturer;

        //Read inventory from binary file
        File binaryFile = new File("CycleInventory.dat");
        if(binaryFile.exists() && binaryFile.length() > 1)
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));

                //Read file into temp array
                Cycle[] temp = (Cycle[])fileReader.readObject();

                //Copy the Cycle objects into inventory array
                for (int i = 0; i < temp.length; i++) {
                    inventory[i] = temp[i];
                    System.out.println(inventory[i]);
                }

                //update count
                count = temp.length;

                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        else
            System.out.println("[No data.  Please enter cycles into inventory.]");

        do {
            //Print menu
            System.out.print(
                    "\n********************************************************************\n"
                            + "**                                                                **\n"
                            + "**             WELCOME TO THE CYCLE INVENTORY                     **\n"
                            + "**                                                                **\n"
                            + "********************************************************************\n"
                            + "** Please select from the following choices:                      **\n"
                            + "** 1) Enter new Bicycle                                           **\n"
                            + "** 2) Enter new Motorcycle                                        **\n"
                            + "** 3) Display entire inventory                                    **\n"
                            + "** 4) Exit                                                        **\n"
                            + "********************************************************************\n"
                            + ">> ");

            //Get user's choice and clear the keyboard buffer
            choice = keyboard.nextInt();
            keyboard.nextLine();

            switch (choice) {
                case 1:
                    try {
                        //Get stats for a Bicycle
                        System.out.print("Enter manufacturer: ");
                        manufacturer = keyboard.nextLine();
                        System.out.print("Enter price       $ ");
                        price = keyboard.nextDouble();
                        System.out.print("Enter frame size  : ");
                        frameSize = keyboard.nextInt();
                        //If frame size is invalid throw an InvalidFrameException
                        if(frameSize != 15 && frameSize != 17 && frameSize != 19)
                            throw new InvalidFrameException();
                        System.out.print("Enter cranks      : ");
                        cranks = keyboard.nextInt();
                        inventory[count++] = new Bicycle(manufacturer, price, frameSize, cranks);
                    } catch (InvalidFrameException e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case 2:
                    //Get stats for a Motorcycle
                    System.out.print("Enter manufacturer : ");
                    manufacturer = keyboard.nextLine();
                    System.out.print("Enter price        $ ");
                    price = keyboard.nextDouble();
                    System.out.print("Enter displacement : ");
                    displacement = keyboard.nextInt();
                    System.out.print("Enter fuel capacity: ");
                    fuelCapacity = keyboard.nextDouble();
                    inventory[count++] = new Motorcycle(manufacturer, price, displacement, fuelCapacity);
                    break;

                case 3:
                    System.out.println("~~~Current inventory~~~");
                    //Print inventory
                    for (int i = 0; i < count; i++) {
                        System.out.println(inventory[i]);
                    }

                    //Print stats
                    System.out.println("\nAverage price of all cycles: " + currency.format(findAveragePrice(inventory, count)));
                    System.out.println("Motorcycle with largest displacement: " + findMaxDisplacement(inventory, count));
                    break;

                case 4:
                    //Exit
                    System.out.println("Have a nice day!");
                    break;
            }

        } while (choice != 4);

        //Write inventory to file if not empty
        if(count != 0)
            try {
                ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));

                //Create temp array
                Cycle[] temp = new Cycle[count];

                //Copy all Cycle objects into temp array
                for (int i = 0; i < count; i++) {
                    temp[i] = inventory[i];
                }

                //Write temp array to file
                fileWriter.writeObject(temp);

                fileWriter.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }

        keyboard.close();
    }

    public static double findAveragePrice(Cycle[] inventory, int count) {
        double sum = 0;

        //Sum the prices of all Cycle objects
        for (int i = 0; i < count; i++) {
            sum += inventory[i].getPrice();
        }

        return sum / count;
    }

    public static Motorcycle findMaxDisplacement(Cycle[] inventory, int count) {
        int maxDisplacement = 0;
        Motorcycle maxMotor = null;

        //For each Cycle
        for (int i = 0; i < count; i++) {
            //If inventory[i] is a Motorcycle
            if(inventory[i] instanceof Motorcycle)
                //If inventory[i]'s displacement > maxDisplacement update maxMotor and maxDisplacement
                if(((Motorcycle)inventory[i]).getDisplacement() > maxDisplacement) {
                    maxMotor = (Motorcycle) inventory[i];
                    maxDisplacement = maxMotor.getDisplacement();
            }
        }
        return maxMotor;
    }

}