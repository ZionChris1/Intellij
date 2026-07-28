package edu.miracostacollege.cs112.ic15_nobelpeaceprize.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The <code>Model</code> class represents the business logic (data and calculations) of the application.
 * In the Nobel Peace Prize Laureates app, it either loads laureates from a CSV file (first load) or a binary file (all
 * subsequent loads).  It is also responsible for saving data to a binary file.
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Model {

  public static final String CSV_FILE = "NobelPeacePrizeWinners.csv";
	public static final String BINARY_FILE = "NobelPeacePrizeWinners.dat";

  //DONE: Implement the 4 methods below
	/**
	 * Determines whether the binary file exists and has data (size/length > 5L bytes).
	 * @return True if the binary file exists and has data, false otherwise.
	 */
	public static boolean binaryFileHasData() {
		File binaryFile = new File(BINARY_FILE);
		return binaryFile.exists() && binaryFile.length() >= 5;
	}

	/**
	 * Populates the list of all laureates from the binary file. This will only be called once, the first time the app
	 * loaded to seed initial data from the CSV file.  All subsequent loads will be extracted from
	 * the binary file.be called everytime the application loads,
	 * @return The list of all laureates populated from the CSV file
	 */
	public static ObservableList<NobelLaureate> populateListFromCSVFile() {
		ObservableList<NobelLaureate> allLaureates = FXCollections.observableArrayList();

		try {
			Scanner input = new Scanner(new File(CSV_FILE));
			int year;
			double prizeAmount;
			String line, name, motivation, country;
			String[] parts;

			//Skip CSV header
			input.nextLine();

			while(input.hasNextLine()) {
				line = input.nextLine();

				parts = line.split(",");

				year = Integer.parseInt(parts[0]);
				prizeAmount = Double.parseDouble(parts[6]);
				name = parts[13];
				motivation = parts[9];
				country = parts[26];

				allLaureates.add(new NobelLaureate(name, year, motivation, country, prizeAmount));
			}

			input.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error: " + e.getMessage());
		}

		return allLaureates;
	}

	/**
	 * Populates the list of all laureates from the binary file. This will be called everytime the application loads,
	 * other than the very first time, since it needs initial data from CSV.
	 * @return The list of all laureates populated from the binary file
	 */
	public static ObservableList<NobelLaureate> populateListFromBinaryFile()
	{

		ObservableList<NobelLaureate> allLaureates = FXCollections.observableArrayList();

		try {
			ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));

			//Load array from file and copy to list
			NobelLaureate[] tempArray = (NobelLaureate[]) fileReader.readObject();
			allLaureates.addAll(Arrays.asList(tempArray));

			fileReader.close();

		} catch (ClassNotFoundException |IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		return allLaureates;
	}

	/**
	 * Saves the list of all laureates to the binary file. This will be called each time the application stops,
	 * which occurs when the user exits/closes the app.  Note this method is called in the View, by the controller,
	 * during the stop() method.
	 * @return True if the data were saved to the binary file successfully, false otherwise.
	 */
	public static boolean writeDataToBinaryFile(ObservableList<NobelLaureate> allLaureatesList)
	{
		if(allLaureatesList.size() == 0)
			return false;

		try {
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));

			//Create temp array and copy list to it
			NobelLaureate[] tempArray = new NobelLaureate[allLaureatesList.size()];
			for(int i = 0; i < tempArray.length; i++) {
				tempArray[i] = allLaureatesList.get(i);
			}

			fileWriter.writeObject(tempArray);

			fileWriter.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		return true;
	}

}
