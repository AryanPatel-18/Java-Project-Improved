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
    int getId(String path){
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

    // To create the admin User
    public void adminUser(){
        // For creating admin user which requires a super user
        String id = "";
        while(true){
            System.out.print("Enter the id : ");
            id = sc.next();
            if(!isExist("Admin",id)){
                break;
            }else{
                System.out.println("This id already exists please try another one");
            }
        }
        id = "AD" + id + getId("Admin");
        // a.addInformation(id);
        setPassword(id, "Admin");
        registerUser(id, "Admin");
        createReminderFiles("Admin", id);
        // a.Menu(id);

    }


    // To get the info of the professor user



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

    // Function to call the private function studentUser
    public void callStudentUser(){
        studentUser();
    }

}
