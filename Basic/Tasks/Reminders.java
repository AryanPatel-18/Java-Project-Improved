package Basic.Tasks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// All the imports
import java.util.Scanner;
import Basic.Main;


public class Reminders implements Main{
    // All the objects for this class
    Scanner sc = new Scanner(System.in);
    
    // Sending Private Reminders
    public void sendReminder(String id){
        System.out.print("Enter the id you want to send to message to : ");
        String sendId = sc.next();
        sc.nextLine();
        int option = checkPermission(sendId);
        String designation = getDesignation(option);

        // Trying to write the message to the sendId file
        // Here the true allows so that the data is not replaced but we can simply add new data on top of it
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reminders/" + designation + "/Private/" + sendId + ".txt", true))) {
            System.out.print("Please enter your message : ");
            String message = sc.nextLine();
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("The person you are trying to send the message to does not exist");
        }
    }

    // Sending Public Reminders
    public void sendPublicReminder() {
        System.out.println("-----------------------------");
        System.out.println("1) Students");
        System.out.println("2) Proffessors");
        System.out.println("3) Staff");
        System.out.println("-----------------------------");
        System.out.print("Please enter to whom you want to send the messages to : ");
        int option = sc.nextInt();
        String type = "";

        if(option == 1){type = "Student";}
        if(option == 2){type = "Proffessor";}
        if(option == 3){type = "Staff";}

        // Getting the message input
        System.out.print("Please enter the message : ");
        String message = sc.nextLine();
        sc.next();
        StringBuilder ids = new StringBuilder();

        // Getting all the ids according to the entered option from the registered.txt file in their respective folders
        try(BufferedReader reader = new BufferedReader(new FileReader("Ids/" + type + "/registered.txt"))) {
            String line;
            while((line = reader.readLine()) != null){
                if(line.equalsIgnoreCase("registered") || line.equalsIgnoreCase("EnrollmentNumber") || line.equalsIgnoreCase("IdNumber")){
                    continue;
                }else{
                    ids.append(line + " ");
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem while reading the ids");
        }

         // Converting all the fetched ids into an array for easier use
         String arr[] = ids.toString().split(" ");

        // Writing message to all the ids
        boolean problem = false;
        for(int i = 0 ; i < arr.length ; i++){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reminders/" + type + "/public/" + arr[i] + ".txt"))){
                writer.write(message);
            } catch (Exception e) {
                System.out.println("there was a problem while writing in " + arr[i] + ".txt file");
                problem = true;
            }
        }
        // Just printing a message if the task if completed without any error
        if(!problem){
            System.out.println("Sent the message to the user!");
        }
    }

    // For clearing All reminders
    public void clearReminders(String id, String path) {
        // Just adding a null line so that all the data is deleted
        
        // Clearing public reminders
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reminders/" + path + "/public/" + id + ".txt"))) {
            writer.write(System.lineSeparator());
            System.out.println("Cleared all the reminders!\n\n");
        } catch (Exception e) {
            System.out.println("There was a problem while clearing the reminders");
        }

        // Clearing Private Reminders
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Reminders/" + path + "/Private/" + id + ".txt"))) {
            writer.write(System.lineSeparator());
            System.out.println("Cleared all the reminders!\n\n");
        } catch (Exception e) {
            System.out.println("There was a problem while clearing the reminders");
        }
    }

    // For displaying both public and private reminders
    private void displayReminders(String id, String type, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Reminders/" +path+"/" + type + "/" + id + ".txt"))) {
            String line;

            // Used line builder so that we can easily add or append values to the string, also supports \n which is to leave a line
            // String builder is the same as a string just using for append feature
            StringBuilder lineBuilder = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                lineBuilder.append(line + System.lineSeparator());
            }

            String conversion = lineBuilder.toString().trim();
            
            // Checking if there are any reminders or not
            if(conversion.length() != 0){
                System.out.println("\n\n");
                System.out.println(conversion);
                System.out.println("\n\n");
            }else{
                System.out.println("\n");
                System.out.println("No Reminders");
                System.out.println("\n");
            }
        } catch (Exception e) {
            System.out.println("There was a problem while reading the " + type + " reminders");
        }
    }

    // To call the private function of display reminders
    public void callDisplayReminder(String id ,String type, String path){
        displayReminders(id, type, path);
    }
}
