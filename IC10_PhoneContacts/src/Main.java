import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner keyboard = new Scanner(System.in);
		Contact[] myContacts = new Contact[100];
		int count = 0, choice;
		String firstName, lastName, mobile, birthday;
		boolean isFavorite;

		File binaryFile = new File("Contacts.dat");
		
		System.out.println("Loading Contact Information from Database...");
		// DONE: Load contacts from binary file
		if(binaryFile.exists() && binaryFile.length() >= 5){
			try {
				ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));

				//Load contacts into temp array
				Contact[] temp = (Contact[]) fileReader.readObject();

				//Update count and copy objects to main array
				count = temp.length;
				for (int i = 0; i < temp.length; i++) {
					myContacts[i] = temp[i];
				}

				fileReader.close();
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error: " + e.getMessage());
			}
		}


		System.out.println("Done! " + count + " contacts loaded");
		
		do {
		System.out.print(
				  "\n********************************************************************\n"
				+ "**                                                                **\n"
				+ "**                      NATHAN'S PHONE CONTACTS                   **\n"
				+ "**                                                                **\n"
				+ "********************************************************************\n"
				+ "1) Add New Contact...\n"
				+ "2) View Contact Names\n"
				+ "3) View Contact Details\n"
				+ "4) Exit\n"
				+ "********************************************************************\n"
				+ ">> ");
			choice = keyboard.nextInt();
			
			switch (choice)
			{
			case 1:  // Add New Contact...
				// Clear out \n from keyboard
				keyboard.nextLine();
				System.out.print("First Name: ");
				firstName = keyboard.nextLine();
				System.out.print("Last  Name: ");
				lastName = keyboard.nextLine();
				System.out.print("Mobile   #: ");
				mobile = keyboard.nextLine();
				System.out.print("Birthday  : ");				
				birthday = keyboard.nextLine();
				System.out.print("Favorite (Y/N): ");
				isFavorite = keyboard.nextLine().equalsIgnoreCase("Y");
				
				// DONE: Instantiate new Contact, add it to the array;
				myContacts[count++] = new Contact(firstName, lastName, mobile, birthday, isFavorite);
				break;
				
			case 2:  // View Contact Names
				System.out.println("\n********************************************************************");
				System.out.println("                        Contact Names");
				System.out.println("********************************************************************");
				// DONE: Print contact names (only)
				// Loop through myContacts array
				for (int i = 0; i < count; i++) {
					System.out.println(myContacts[i].getFullName());
				}
				break;
				
			case 3:  // View Contact Details
				System.out.println("\n********************************************************************");
				System.out.println("                        Contact Details");
				System.out.println("********************************************************************");
				// DONE: Print contact details
				for (int i = 0; i < count; i++) {
					System.out.println(myContacts[i]);
				}
				break;
				
			case 4:  // Exit
				System.out.println("Saving Contact Information to Database...");
				break;
			default:  // Error - Invalid input
				System.err.println("Invalid choice. Please select (1-4)");
				Thread.sleep(500); // To pause a bit of time (e.g. 0.5 second) before restarting loop
				
			}
		
		}
		while (choice != 4);
		
		// DONE: Save contacts to binary file
		if(count > 0)
			try {
				ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));

				//Create temp array
				Contact[] temp = new Contact[count];

				//Copy contacts into temp
				for(int i = 0; i < count; i++) {
					temp[i] = myContacts[i];
				}

				//Write temp array
				fileWriter.writeObject(temp);

				fileWriter.close();
			} catch (IOException e) {
				System.err.println("Error; " + e.getMessage());
			}
		System.out.println("Done! " + count + " contacts saved");
		
		keyboard.close();
	}

}
