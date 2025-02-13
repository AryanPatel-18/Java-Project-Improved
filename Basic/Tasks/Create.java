package Basic.Tasks;

import java.util.*;
import java.io.*;
import Basic.Main;
import Basic.Users.*;

public class Create implements Main{
    // All the objects for this class
    Scanner sc = new Scanner(System.in);
    Student s = new Student();

    // To set the password of the user
    private void setPassword(String id, String path){
        System.out.print("Please enter the password you want for the id : ");
        String psw = sc.next();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Passwords/" + path + "/" + id + ".txt"))){
            writer.write(psw);
        } catch (Exception e) {
            System.out.println("There was a problem while saving the password");
        }
    }

    // For updating the ids file
    void updateId(String path){
        int id = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/" + path + "/idNumber.txt"))) {
            String line = reader.readLine();
            id = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println("There was a problem while reading the id number");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/" + path + "/IdNumber.txt"))) {
            String anotherLine = "" + (++id);
            writer.write(anotherLine);
        } catch (Exception e) {
            System.out.println("There was a problem while updating the id number");
        }
    }

    // To get the new id from the ids file
    public int getId(String path){
        // For getting the id number from the database
        // Basically for checking how many ids we have created for each user category
        // All elminating any copies of ids dues to them having the same name

        String currentId = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("Ids/" + path + "/IdNumber.txt"))){
            currentId = reader.readLine();
            updateId(path);
            return Integer.parseInt(currentId);
        } catch (Exception e) {
            System.out.println("There was a problem while reading the id number file");
        }
        return 0;
    }

    // To register User into the register File
    void registerUser(String id, String path){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/" + path + "/registered.txt", true))){
            writer.write(id + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("There was a problem while registering the student into the database;");
        }
    }


    // To create the user file
    private void studentUser(){
        System.out.print("Please enter your first name : ");
        String firstName = sc.next();
        System.out.print("Please enter your last name : ");
        String lastName = sc.next();

        // Specific way to create user id
        String id = "ST" + firstName.toUpperCase().charAt(0) + lastName.toUpperCase().charAt(0) + getId("Student");
        registerUser(id, "Student");
        setPassword(id, "Student");
        s.callStudentInfo(id, firstName, lastName); 
        createReminderFiles("Students", id);
        createAttendenceFile(id);
        s.Menu(id);

    }

    // Create professor information
    private void proffUserInfo( String staffId){
        System.out.print("Enter the full name of the professor : ");
        String name = sc.nextLine();
        System.out.print("Enter the gender : ");
        String gender = sc.next();
        System.out.print("Enter the specialization : ");
        String specialization = sc.next();
        String id = "PF" + specialization + getId("Proffessor");
        System.out.print("Enter the contact number : ");
        String number = sc.next();
        System.out.print("String enter the address : ");
        String address = sc.next();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/Proffessor/" + id + ".txt"))) {
            writer.write(id + System.lineSeparator());
            writer.write(name + System.lineSeparator());
            writer.write(specialization + System.lineSeparator());
            writer.write(number + System.lineSeparator());
            writer.write(address + System.lineSeparator());
            writer.write(gender + System.lineSeparator());
            writer.write(staffId + System.lineSeparator());
        } catch (Exception e) {
            System.out.print("There was a problem while writing the professor data");
        }
        registerUser(id, "Proffessor");
        setPassword(id, "Proffessor");
        createReminderFiles("Proffessor", id);
    }

    // Function to call the private function studentUser
    public void callStudentUser(){
        studentUser();
    }

    private void createStaffId(String staffId){
        System.out.print("Enter the full name of the professor : ");
        String name = sc.nextLine();
        System.out.print("Enter the gender : ");
        String gender = sc.next();
        String firstName[] = name.split(" ");
        String id = "SF" +  firstName[0] + getId("Proffessor");
        System.out.print("Enter the contact number : ");
        String number = sc.next();
        System.out.print("String enter the address : ");
        String address = sc.next();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/Proffessor/" + id + ".txt"))) {
            writer.write(id + System.lineSeparator());
            writer.write(name + System.lineSeparator());
            writer.write(gender + System.lineSeparator());
            writer.write(number + System.lineSeparator());
            writer.write(address + System.lineSeparator());
            writer.write(staffId + System.lineSeparator());
        } catch (Exception e) {
            System.out.print("There was a problem while writing the professor data");
        }
        registerUser(id, "Staff");
        setPassword(id, "Staff");
        createReminderFiles("Staff", id);
    }

    public void callStaffId(String staffId){
        createStaffId(staffId);
    }

    // To call the professor information function
    public void callProffCreate(String staffId){
        proffUserInfo(staffId);
    }

    private void createAdminId(String staffId){
        System.out.print("Enter the full name of the Admin : ");
        String name = sc.nextLine();
        System.out.print("Enter the gender : ");
        String gender = sc.next();
        System.out.print("Enter the specialization : ");
        String specialization = sc.next();
        String id = "AD" + specialization + getId("Admin");
        System.out.print("Enter the contact number : ");
        String number = sc.next();
        System.out.print("String enter the address : ");
        String address = sc.next();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/Admin/" + id + ".txt"))) {
            writer.write(id + System.lineSeparator());
            writer.write(name + System.lineSeparator());
            writer.write(gender + System.lineSeparator());
            writer.write(specialization + System.lineSeparator());
            writer.write(number + System.lineSeparator());
            writer.write(address + System.lineSeparator());
            writer.write(staffId + System.lineSeparator());
        } catch (Exception e) {
            System.out.print("There was a problem while writing the professor data");
        }
        registerUser(id, "Admin");
        setPassword(id, "Admin");
        createReminderFiles("Admin", id);
    }

    public void callCreateAdId(String Staffid){
        createAdminId(Staffid);
    }
}
