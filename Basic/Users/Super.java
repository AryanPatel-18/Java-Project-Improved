package Basic.Users;

// All the imports
import Basic.Main;
import java.util.*;
import Basic.Tasks.*;
import java.io.*;


final public class Super implements Main {
    // All the objects
    Scanner sc = new Scanner(System.in);
    Create c = new Create();
    Clear cl = new Clear();
    
    public void Menu(){
        while(true){
            System.out.println("-------------------------------\n");
            System.out.println("1) Create admin id");
            System.out.println("2) Clear database");
            System.out.println("3) Exit");
            System.out.println("--------------------------------\n\n");

            System.out.print("What do you want to do? : ");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    c.callCreateAdId(generateStaffId());
                    break;
                case 2:
                    cl.clearDataBase();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid input");
                    break;
            }
        }
    }




    // To generate the staff id
    String generateStaffId(){
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

    // To check the staff id
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

    // To update the staff id
    private void updateStaffId(String id){
        // Just appending the new id at the end of the id file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ids/StaffId.txt",true))){
            writer.write(id);
        } catch (Exception e) {
            System.out.println("There was a problem while updating the staff id");
        }
    }
}
