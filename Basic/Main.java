package Basic;

// All the imports
import java.io.*;

public interface Main {

    // For checking the designation of the id
    default int checkPermission(String id){
        if(id.charAt(0) == 'A' && id.charAt(1) == 'D'){
            return 1;
        }
        if(id.charAt(0) == 'S' && id.charAt(1) == 'T'){
            return 2;
        }
        if(id.charAt(0) == 'S' && id.charAt(1) == 'F'){
            return 3;
        }
        if(id.charAt(0) == 'P' && id.charAt(1) == 'F'){
            return 4;
        }
        if(id.equals("SPmain")){
            return 5;
        }
        return 0;
    }

    // Checking the existence of the id
    default boolean idExists(String id , String path){
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/" + path + "/registered.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals(id)){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem while reading the " + path + " registered file");
        }
        return false;
    }

    // Returning the designation in terms of String
    default String getDesignation(int option){
        String designation = "";
        if (option == 1) {
            designation = "Admin";
        }
        
        if (option == 2) {
            designation = "Student";
        }
        
        if (option == 3) {
            designation = "Staff";
        }
        
        if (option == 4) {
            designation = "Proffessor";
        }
        
        if (option == 5) {
            designation = "Super";
        }

        return designation;
    }

    // To check is the account exists
    default boolean isExist(String path, String fileName) {

        // Checking the existence of the account in the database
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/" + path + "/registered.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase(fileName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem while reading the file database");
        }
        return false;
    }


    // to read a specific line from the code
    default String readSpecificLine(int lineNumber, String id, String path){
        try(BufferedReader reader = new BufferedReader(new FileReader("Ids/" + path + "/" + id + ".txt"))) {
            String line;
            int count = 1;
            while((line = reader.readLine()) != null){
                if(count == lineNumber){
                    return line;
                }
                count ++;
            }
        } catch (Exception e) {
            System.out.println("There was a problem while reading the specific line");
        }
        return "No line at that point";
    }

    // To create all the reminder Files
    default void createReminderFiles(String path, String id)
    {
        // For writing in the private file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Reminders/" + path + "/Private/" + id + ".txt"))) {
            writer.write("");
        } catch (Exception e) {
            System.out.println("There was a problem while writing in the Private reminders file");
        }

        // For writing in the public reminder file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Reminders/" + path + "/Public/" + id + ".txt"))) {
            writer.write("");
        } catch (Exception e) {
            System.out.println("There was a problem while writing in the Public reminder file");
        }
    }

    // To create all the attendence Files
    default void createAttendenceFile(String id){
        String totalDays = "";
        try(BufferedReader reader = new BufferedReader(new FileReader("Attendence/TotalDays.txt"))) {
            totalDays = reader.readLine();
        } catch (Exception e) {
            System.out.println("There was a problem while checking the total attendence");
        }
        
        // For creating the total attendence file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Attendence/" + id + ".txt"))){
            writer.write("0" + System.lineSeparator());
            writer.write(totalDays + System.lineSeparator());
            writer.write("0");
        } catch (Exception e) {
            System.out.println("There was a problem while creating the attendence file");
        }
    }
}
