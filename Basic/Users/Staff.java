package Basic.Users;

import java.util.Scanner;
import Basic.Tasks.*;

public class Staff {

    Scanner sc = new Scanner(System.in);
    Reminders r = new Reminders();

    public void menu(String id){

        System.out.println("\n\n\n");
        System.out.println("\t\tWelcome " + id + "!\n");

        System.out.println("----- REMINDER -------");
        r.callDisplayReminder(id, "public","Staff");

        while(true){
            System.out.println("-------------------------------\n");
            System.out.println("1) Send Private Message");
            System.out.println("2) Clear Reminders");
            System.out.println("3) View Reminders");
            System.out.println("4) Exit");
            System.out.println("--------------------------------\n\n");
            System.out.print("What do you want to do? : ");
            int option = sc.nextInt();
    
            switch (option) {
                case 1:
                    r.sendReminder(id);
                    break;
                case 2:
                    r.clearReminders(id, "Staff");
                    break;
                case 3:
                    r.callDisplayReminder(id, "Private", "Staff");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }   

    }
}
