package Basic.Users;

// All the imports
import Basic.Tasks.*;
import Basic.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Admin implements Main{

    // All the objects for this class
    Scanner sc = new Scanner(System.in);
    Clear cr = new Clear();
    Reminders r = new Reminders();
    Timetable t = new Timetable();
    Student s = new Student();
    Create c = new Create();

    // Pending
    public void Menu(String id){
        System.out.println("\n\n\n");
        System.out.println("\t\tWelcome " + id + "!\n");

        System.out.println("----- REMINDER -------");
        r.callDisplayReminder(id, "public","Admin");
        while (true) {
            System.out.println("-------------------------------\n");
            System.out.println("1) Set Attendence");
            System.out.println("2) Send Public Message");
            System.out.println("3) Send Private Message");
            System.out.println("4) Clear Reminders");
            System.out.println("5) View Reminders");
            System.out.println("6) Set Time Table");
            System.out.println("7) Update Information");
            System.out.println("8) Delete User");
            System.out.println("9) Create Proffessor id");
            System.out.println("10) Create Staff id");
            System.out.println("11) Exit");
            System.out.println("--------------------------------\n\n");

            System.out.print("What do you want to do? : ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    // To set Attendence
                    String inputId = "";

                    // Just checking if the input user actually exists or not
                    while(true){
                        System.out.print("Please enter the id of the student : ");
                        inputId = sc.next();
                        if(idExists(inputId, "Student")){
                            break;
                        }else{
                            System.out.println("This id does not exist please try another id!");
                        }
                    }
                    System.out.print("Enter the number of attended days : ");
                    double attendedDays = sc.nextDouble();
                    System.out.print("Enter The total number of days : ");
                    double totalDays = sc.nextDouble();

                    s.setAttendence(inputId, attendedDays, totalDays);
                    break;
                case 2:
                    // To send Public Message
                    r.sendPublicReminder();
                    break;
                case 3:
                    // To send Private Message
                    r.sendReminder(id);
                    break;
                case 4:
                    // To clear messages
                    r.clearReminders(id, "Admin");
                    break;
                case 5:
                    // To view private reminders
                    r.callDisplayReminder(id, "Private", "Admin");
                    break;
                case 6:
                    // To update the time table
                    System.out.println("Enter the division : ");
                    String division = sc.next();
                    if(t.checkDivision(division)){
                        t.callSetTimeTable(division);
                    }else{
                        System.out.print("Please enter a valid division");
                    }
                    break;
                case 7:
                    // To update the information
                    System.out.print("Enter the id you want to update : ");
                    String updateId = sc.next();
                    updateUser(updateId);
                case 8:
                    // To delete a user
                    deleteUser();
                    break;
                case 9:
                    // TO create Professor id
                    c.callProffCreate(callGenerateStaffId());
                    break;
                case 10:
                    // To create Staff id
                    c.callStaffId(callGenerateStaffId());
                    break;
                case 11:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    private void updateUser(String id){
        System.out.println(id);
        int option = checkPermission(id);
        String designation = getDesignation(option);

        // Only Staff Student and Professor id can be changed by the admin
        if(option > 4 || option < 2){
            System.out.println("You cannot update this id");
            return;
        }

        // Calling the update function according to the user id
        if(idExists(id,designation)){
            if (option == 2) {
                updateStudentInfo(id);
            }
            
            if (option == 3) {
                updateStaffInfo(id);
            }
            
            if (option == 4) {
                updateProffInfo(id);
            }
        }else{
            System.out.println("This id does not exists");
        }
    }

    // For updating the student information
    private void updateStudentInfo(String id){
        // We can update all info or just any info
        System.out.println("--------------------------");
        System.out.println("1) First Name");
        System.out.println("2) Last Name");
        System.out.println("3) Mentor Name");
        System.out.println("4) Address");
        System.out.println("--------------------------");

        System.out.print("Please enter what you want to update : ");
        int option = sc.nextInt();

        // Checking for the validity of the option
        if(option > 4 || option < 0){
            System.out.println("Please enter a valid option");
            return;
        }
        // Creating an array out of the information of the user
        String infoArr[] = createInfoArray(id, "Student");
        
        // Updating the new info from the user using indexes
        System.out.print("Enter the new info : ");
        infoArr[option] = sc.next(); 

        // Adding the new info to the text file
        addNewInfo(infoArr, "Student", id);
    }

    // For updating the staff information
    private void updateStaffInfo(String id){

    }

    // For updating the proffessor information
    private void updateProffInfo(String id){

    }

    // To fetch all the information of the users
    private String[] createInfoArray(String id, String path){
        StringBuilder infoString = new StringBuilder();

        // Just taking all the values from the text file then adding them to a string with space between them and then just using split
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/" + path + "/" + id + ".txt"))){
            String line;
            while((line = reader.readLine()) != null){
                infoString.append(line + " ");
            }   
        } catch (Exception e) {
            System.out.println("There was a problem while accessing the information");
        }
        return infoString.toString().split(" ");
    }

    // For writing the new info
    private void addNewInfo(String[] arr, String path, String id){

        // Just travelling through the array and added all the new info back to the text file
        // In this case all the orignal data is deleted once and then new data is added

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/" + path + "/" + id + ".txt"))){
            for(int i = 0 ; i < arr.length ; i++){
                writer.write(arr[i] + System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("There was a problem while writing the new information");
        }

    }
    // For deleting a user
    private void deleteUser(){
        System.out.print("Enter the id you want to delete : ");
        String inputId = sc.next();
        int option = checkPermission(inputId);
        String designation = getDesignation(option);

        // Going through the registered file and adding the values present except for the input id and then adding the new info
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/" + designation + "/registered.txt"))){
            String line;
            while((line =reader.readLine())!= null){
                if(line.equals(inputId)){
                    continue;
                }else{
                    data.append(line + " ");
                }
            }
            addNewInfo(data.toString().split(" "), designation, "registered");
            cr.callDeleteUser(inputId, designation);
        } catch (Exception e) {
            System.out.println("There was a problem while updating the registered file");
        }
    }

    // For generating the random 6 digit staff id
    private String generateStaffId(){
        Random random = new Random();
        int min = 100000; 
        int max = 999999;
        int randomSixDigit = random.nextInt(max - min + 1) + min;
        String id = "" + randomSixDigit;

        // Using recursion if the id already exists
        if(!checkStaffId(id)){
            updateStaffId(id);
            return id;
        }
        return generateStaffId();
    }

    // A calling function to call the generate staff Id
    public String callGenerateStaffId(){
        return generateStaffId();
    }

    // Checking for already used staff ids
    private boolean checkStaffId(String id){
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/StaffId.txt"))){
            String line;
            if((line = reader.readLine()) != null){
                if(line.equals(id)){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem while checking the staff ids");
        }
        return false;
    }

    // Updating the staff id
    private void updateStaffId(String id){
        // Just appending the new id at the end of the id file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/StaffId.txt",true))){
            writer.write(id);
        } catch (Exception e) {
            System.out.println("There was a problem while updating the staff id");
        }
    }
} 

