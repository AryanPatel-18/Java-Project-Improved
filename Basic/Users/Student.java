package Basic.Users;

// All the imports for this file
import java.io.*;
import java.util.*;
import Basic.Main;
import Basic.Tasks.*;


public class Student implements Main{

    // All the objects for this class
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    Reminders r = new Reminders();
    Timetable t = new Timetable();

    // For viewing the attendence
    void viewAttendence(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Attendence/" + id + ".txt"))) {

            // All the variables for this method
            String arr[] = new String[3];
            String line;
            int i = 0;

            // Reading the line and appending to the array
            while ((line = reader.readLine()) != null) {
                arr[i] = line;
                i++;
            }

            // Printing all the data
            System.out.println("Number of days you have attended : " + arr[0]);
            System.out.println("Number of total days : " + arr[1]);
            System.out.println("Total percentage : " + arr[2]);
            System.out.println("\n");

        } catch (IOException e) {
            System.out.println("There was problem while viewing the attendence");
        }
    }

    // For setting attendence by providing all the number of days
    protected void setAttendence(String id, double attendedDays, double totalDays) {
        double percentage = (attendedDays / totalDays) * 100;

        // Converting the data into the correct format
        String content = attendedDays + "\n" + totalDays + "\n" + percentage;

        // Setting the data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Attendence/" + id + ".txt"))) {
            writer.write(content);
        } catch (Exception e) {
            System.out.println("There was a problem while writing the attendence");
        }
    }

    // For updating the attendence if the student was present in class ( Basically Daily Attendence)
    protected void updateAttendence(boolean isPresent, String id) {
        String arr[] = new String[3];

        // Getting already entered attendence details
        try (BufferedReader reader = new BufferedReader(new FileReader("Attendence/" + id + ".txt"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                arr[index] = line;
                index++;
            }
        } catch (Exception e) {
            System.out.println("There was a problem while updating the attendence");
        }

        // Updating according to the present status
        if (isPresent) {
            setAttendence(id, Double.parseDouble(arr[0]) + 1, Double.parseDouble(arr[1]) + 1);
        } else {
            setAttendence(id, Double.parseDouble(arr[0]), Double.parseDouble(arr[1]) + 1);
        }
    }

    // For generating the random enrollment number
    private int generateEnrollmentNumber() {

        // Generate a random 9-digit number
        int randomNineDigitNumber = 100000000 + random.nextInt(900000000);
        if (!checkEnrollmentNumber(randomNineDigitNumber)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/Student/EnrollmentNumber.txt", true))){
                writer.write(randomNineDigitNumber);
            } catch (Exception e) {
                System.out.println("There was a problem while updating the enrollment number");
            }
            return randomNineDigitNumber;
        }
        return generateEnrollmentNumber();
    }

    // For checking if the id is already used or not
    private boolean checkEnrollmentNumber(int number) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/Student/EnrollmentNumber.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if ((Integer.parseInt(line)) == number) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem while reading the enrollment number file");
        }
        return false;
    }

    // For getting the division of the user
    String getClass(String id){
        String division = "D";
        Random random = new Random();

        // Generate a random number between 1 and 3
        int randomNumber = random.nextInt(4) + 1; 
        division = division + randomNumber;
        // Print the random number
        return division;
    }

    
    // Pending
    public void Menu(String id){
        
        System.out.println("\n\n\n");
        System.out.println("\t\tWelcome " + id + "!\n");

        System.out.println("----- REMINDER -------");
        // Displaying reminders remaining

        while (true) {
            System.out.println("-------------------------------\n");
            System.out.println("1) View Attendence");
            System.out.println("2) View Time Table");
            System.out.println("3) Send Private Message");
            System.out.println("4) Clear Reminders");
            System.out.println("5) View Reminders");
            System.out.println("6) Exit");
            System.out.println("--------------------------------\n\n");

            System.out.print("What do you want to do? : ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    viewAttendence(id);
                    break;
                case 2:
                    // Time Table
                    t.viewTimeTable(readSpecificLine(7, id , "Student"));
                case 3:
                    // Send Private Message
                    r.sendReminder(id);
                    break;
                case 4:
                    // Clear Messages
                    r.clearReminders(id, "Student");
                    break;
                case 5:
                    // View Private Messages
                    r.callDisplayReminder(id, "Private", "Student");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }  
    }

    // To Set all the information about the student user
    private void addInformation(String id, String firstName, String lastName) {

        // Taking all the inputs
        System.out.print("Enter your mentor name : ");
        String mentorName = sc.nextLine();
        System.out.print("Enter your Address : ");
        String address = sc.nextLine();
        String enrollmentNumber = "" + generateEnrollmentNumber();
        createReminderFiles("Student", id);

        // Writing within the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/Student/" + id + ".txt"))) {
            writer.write(id + System.lineSeparator());
            writer.write(firstName + System.lineSeparator());
            writer.write(lastName + System.lineSeparator());
            writer.write(mentorName + System.lineSeparator());
            writer.write(address + System.lineSeparator());
            writer.write(enrollmentNumber + System.lineSeparator());
            writer.write(getClass(id) + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("There was problem while writing the student data");
        }

        System.out.println("-------------------------------\n");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Mentor Name : " + mentorName);
        System.out.println("Address : " + address);
        System.out.println("Enrollment Number :" + enrollmentNumber + "\n");
        System.out.println("--------------------------------\n\n");
    }

    public void callStudentInfo(String id, String firstName, String lastName){
        addInformation(id, firstName, lastName);
    }

}
