package edu.miracostacollege.cs112.ic15_nobelpeaceprize.controller;

import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.Model;
import edu.miracostacollege.cs112.ic15_nobelpeaceprize.model.NobelLaureate;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> is a Singleton object that relays all commands between the Model and View
 * (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
 * method.
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class Controller {
	private ObservableList<NobelLaureate> mAllLaureatesList;

  //DONE: Implement the singleton pattern to ensure there is only one Controller object ever instantiated.
	private static Controller theInstance;

	public static Controller getInstance() {
		//If theInstance is null make new Controller
		if(theInstance == null) {
			theInstance = new Controller();

			//Load data from appropriate file
			if(Model.binaryFileHasData())
				theInstance.mAllLaureatesList = Model.populateListFromBinaryFile();
			else
				theInstance.mAllLaureatesList = Model.populateListFromCSVFile();
		}
		return theInstance;
	}

	private Controller () {}

	/**
	 * Gets the list of all laureates.
	 * @return The list of all laureates.
	 */
	public ObservableList<NobelLaureate> getAllLaureates() {
		return mAllLaureatesList;
	}

	/**
	 * Makes a request for the model to save all the laureates data (the list of all laureates) to
	 * a persistent binary file.
	 */
	public void saveData() {
		Model.writeDataToBinaryFile(mAllLaureatesList);
	}
}
