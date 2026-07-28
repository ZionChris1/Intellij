import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File taskFile = new File("TaskList.dat");
        Scanner keyboard = new Scanner(System.in);
        String name, dueDate, deadLine, priority = "";

        Task[] tasks = new Task[100];
        int count = 0;

        System.out.println("Previously saved Tasks from binary file:");

        //If there is a tasks file and it is not empty
        if(taskFile.exists() && taskFile.length() >= 5) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(taskFile));

                //Read tasks into temp array
                Task[] temp = (Task[]) fileReader.readObject();

                //Copy tasks into main array
                count = temp.length;
                for (int i = 0; i < temp.length; i++) {
                    tasks[i] = temp[i];
                }

                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
            //Print all tasks
            for (int i = 0; i < count; i++) {
                System.out.println(tasks[i]);
            }
        }
        else
            System.out.println("[None, please enter new Tasks]");

        do {
            System.out.print("\nPlease enter task name (or \"quit\" to exit): ");
            name = keyboard.nextLine();

            if(name.equals("quit"))
                break;

            //Get task data
            System.out.print("Please enter due date (in form MM/DD/YYYY): ");
            dueDate = keyboard.nextLine();
            System.out.print("Please enter deadline: ");
            deadLine = keyboard.nextLine();
            System.out.print("Please enter priority: ");
            //Convert priority from int to String
            switch(keyboard.nextInt()) {
                case 1:
                    priority = "High";
                    break;
                case 2:
                    priority = "Medium";
                    break;
                case 3:
                    priority = "Low";
                    break;
            }
            //Clear keyboard buffer
            keyboard.nextLine();

            //Make new task object
            tasks[count++] = new Task(name, dueDate, deadLine, priority);
        } while(true);

        //Write tasks to file
        if(count > 0)
            try {
                ObjectOutputStream fileReader = new ObjectOutputStream(new FileOutputStream(taskFile));

                //Copy tasks into temp array
                Task[] temp = new Task[count];
                for (int i = 0; i < count; i++) {
                     temp[i] = tasks[i];
                }

                fileReader.writeObject(temp);

                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        keyboard.close();
    }
}