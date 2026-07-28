import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String[] appointments = new String[6];
        String name;
        int time, count = 0;

        do {
            try {
                System.out.print("\nWhat time (1-6pm) would you like to schedule an appointment? ");
                time = keyboard.nextInt() - 1;

                //If time is invalid throw an exception
                if(time < 0 || time >= appointments.length)
                    throw new InvalidTimeException();


                //If time slot is in use throw an exception
                if (appointments[time] != null)
                    throw new TimeInUseException();

                //Clear the key buffer
                keyboard.nextLine();

                System.out.print("Enter name of person reserving the appointment: ");
                name = keyboard.nextLine();

                //Add the appointment
                appointments[time] = name;
                count++;

                //Print appointment list
                System.out.println("\nHere are the current appointments:");
                for(int i = 0; i < appointments.length; i++) {
                    System.out.print(i + 1 + ":00 pm: [");

                    if(appointments[i] == null)
                        System.out.println("empty]");
                    else
                        System.out.println(appointments[i] + "]");
                }
            } catch (InvalidTimeException | TimeInUseException e) {
                System.out.println(e.getMessage());
            }
        } while(count < 6);

        System.out.println("\nAll appointments have been booked!");

        keyboard.close();
    }
}