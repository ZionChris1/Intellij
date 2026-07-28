import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		Scanner keyboard = new Scanner(System.in);
		String name, location;
		int year, windSpeed, choice, count = 0;
		double cost, magnitude;
		// Maintain an array (named log) of NaturalDisaster objects, with a length of 10.
		NaturalDisaster[] log = new NaturalDisaster[10];
	
		//When the program first runs (before the user prompt), open the binary file (named "NaturalDisasters.dat")
		// for reading and read all the NaturalDisaster objects into the array.
		File binaryFile = new File("NaturalDisasters.dat");
		System.out.println("~~~Previously Recorded Natural Disasters~~~");
		if(binaryFile.exists() && binaryFile.length() > 1) {
			try {
				ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));

				NaturalDisaster[] temp = (NaturalDisaster[]) fileReader.readObject();

				for (int i = 0; i < temp.length; i++) {
					log[i] = temp[i];
					System.out.println(log[i]);
				}

				count = temp.length;
				fileReader.close();
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
		// If the file does not exist or is empty, display the message "[No natural disasters recorded.]  
		// Otherwise, loop through the array and print each of the NaturalDisaster objects to the console.  
		else
			System.out.println("[No natural disasters recorded.]");

			
		// In a separate loop, prompt the user with 3 options to record an Earthquake (option 1), 
		// Hurricane (option 2) in the log. Option 3 is to display all natural disasters, 
		// their average cost and the NaturalDisaster with the highest cost.  Option 4 is to exit.
		do {
			//Print menu
			System.out.print(
					"\n********************************************************************\n"
							+ "**                                                                **\n"
							+ "**                  NATURAL DISASTER LOG                          **\n"
							+ "**                                                                **\n"
							+ "********************************************************************\n"
							+ "** Please select from the following choices:                      **\n"
							+ "** 1) Record an Earthquake                                        **\n"
							+ "** 2) Record a  Hurricane                                         **\n"
							+ "** 3) Display entire log (w/ stats)                               **\n"
							+ "** 4) Exit                                                        **\n"
							+ "********************************************************************\n"
							+ ">> ");
			//Get user's choice and clear \n out of keyboard buffer
			choice = keyboard.nextInt();
			keyboard.nextLine();

			switch(choice) {
				// If the user enters option 1, prompt for name, year, location, cost and magnitude.
				// Create a new Earthquake object and add it to the array.
				case 1:
					//Get data for an Earthquake
					System.out.print("Enter name of Earthquake: ");
					name = keyboard.nextLine();
					System.out.print("Enter year              : ");
					year = keyboard.nextInt();
					keyboard.nextLine();
					System.out.print("Enter location          : ");
					location = keyboard.nextLine();
					System.out.print("Enter cost (billions)   $ ");
					cost = keyboard.nextDouble();
					System.out.print("Enter magnitude         : ");
					magnitude = keyboard.nextDouble();

					//Add a new Earthquake to the log
					log[count++] = new Earthquake(name, year, location, cost, magnitude);
				break;


				// Else if the user enters option 2, prompt for name, year, location, cost and max wind speed.
				// Create a new Hurricane object and add it to the array.
				case 2:
					try {
						//Get data for a Hurricane
						System.out.print("Enter name of Hurricane: ");
						name = keyboard.nextLine();
						System.out.print("Enter year             : ");
						year = keyboard.nextInt();
						keyboard.nextLine();
						System.out.print("Enter location         : ");
						location = keyboard.nextLine();
						System.out.print("Enter cost (billions)  $ ");
						cost = keyboard.nextDouble();
						System.out.print("Enter max wind speed   : ");
						windSpeed = keyboard.nextInt();

						//If the wind speed is too low throw an exception
						if (windSpeed < 74)
							throw new InsufficientWindSpeedException();

						//Add a new Hurricane to the log
						log[count++] = new Hurricane(name, year, location, cost, windSpeed);
					} catch (InsufficientWindSpeedException e) {
						System.err.println(e.getMessage());
					}
					break;

				// Else if the user enters option 3, display all the (non-null) objects in the array,
				// the average cost of all NaturalDisasters (formatted as currency)
				// and the NaturalDisaster with the highest cost.
				case 3:
					//Print all NaturalDisasters in log
					System.out.println("~~~ All Recorded Natural Disasters~~~");
					for (int i = 0; i < count; i++) {
						System.out.println(log[i]);
					}

					//Print stats
					System.out.println("\nThe average cost per natural disaster = " + currency.format(calculateAverageCost(log, count) * 1_000_000_000));

					System.out.println("\n~~~ The Costliest Natural Disaster ~~~");
					System.out.println(findCostliestDisaster(log, count));

					System.out.println("\n~~~         For (+5 Points) Extra Credit           ~~~");
					System.out.println("~~~ The California Earthquake w/ Highest Magnitude ~~~");
					System.out.println(findHighestMagnitudeCAEarthquake(log, count));
				break;

				// Else if the user enters option 4 (exit), your program should write the array to the binary file
				// (named "NaturalDisasters.dat") and exit.
				case 4:
					System.out.println("Thanks for keeping us safe!");
				break;
			}
		} while(choice != 4);

		//Don't write log to file if it is empty
		if(count != 0)
			try {
				ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
				NaturalDisaster[] temp = new NaturalDisaster[count];

				//Copy all items from main array to temp array
				for (int i = 0; i < count; i++) {
					temp[i] = log[i];
				}

				//Write the temp array to the file
				fileWriter.writeObject(temp);

				fileWriter.close();
			} catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}

		keyboard.close();
	}
	
	// Create a helper method named public static double calculateAverageCost(NaturalDisaster[] log, int count)  
	// that will find average cost of ALL the natural disasters in the log. Use this in your main method (under case 3).
	public static double calculateAverageCost(NaturalDisaster[] log, int count) {
		double sum = 0.0;

		//Sum all the NaturalDisaster costs
		for (int i = 0; i < count; i++) {
			sum += log[i].getCost();
		}

		return sum / count;
	}
	
	// Create a helper method named public static NaturalDisaster findCostliestDisaster(NaturalDisaster[] log, int count)  
	// that will find the natural disaster with the highest cost. Use this in your main method (under case 3).
	public static NaturalDisaster findCostliestDisaster(NaturalDisaster[] log, int count) {
		NaturalDisaster maxCost = null;
		double maxCostAmount = Double.MIN_VALUE;

		//for each NaturalDisaster
		for (int i = 1; i < count; i++) {
			//If log[i] cost more that maxCostAmount update MaxCost and maxCostAmount
			if(log[i].getCost() > maxCostAmount) {
				maxCost = log[i];
				maxCostAmount = maxCost.getCost();
			}
		}
		return maxCost;
	}
	
	// +5 points extra credit] Create a helper method named public static NaturalDisaster findHighestMagnitudeCAEarthquake(NaturalDisaster[] log, int count)  
	// that will find the Earthquake (ignore Hurricanes) in California (ignore other locations) with the highest magnitude. Use this in your main method (under case 3).
	public static NaturalDisaster findHighestMagnitudeCAEarthquake(NaturalDisaster[] log, int count) {
		Earthquake MaxCaQuake = null;
		double MaxCaQuakeMagnitude = Double.MIN_VALUE;

		for (int i = 0; i < count; i++) {
			//If lig[i] is an Earthquake
			if(log[i] instanceof Earthquake)
				//If the Earthquake is in California
				if("California".equalsIgnoreCase(log[i].getLocation()))
					//If the Earthquake's magnitude > MaxCaQuakeMagnitude update the quake and max magnitude
					if(((Earthquake)log[i]).getMagnitude() > MaxCaQuakeMagnitude) {
						MaxCaQuake = (Earthquake) log[i];
						MaxCaQuakeMagnitude = MaxCaQuake.getMagnitude();
					}
		}

		return MaxCaQuake;
	}
}
