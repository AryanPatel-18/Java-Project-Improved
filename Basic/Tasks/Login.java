package Basic.Tasks;

// All the imports
import Basic.Main;
import Basic.Users.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Login implements Main{

    // All the objects for this class
    Scanner sc = new Scanner(System.in);
    Admin ad = new Admin();
    Student s = new Student();
    Proffessor p = new Proffessor();

    public void login(){
        System.out.print("Please enter your login id : ");
        String id = sc.next();

        // All the variables
        int option = checkPermission(id);
        String designation = getDesignation(option);

        // Password validation
        if (!checkPassword(id, designation)) {
            System.out.println("The password you have entered is incorrect please try again later");
            System.exit(0);
        }

        // Checking for the account existence
        if(!isExist(designation, id)){
            System.out.println("The account does not exist");
            return;
        }

        // Displaying menu according to the option
        switch (option) {
            case 1:
                // For the admin Menu
                ad.Menu(id);
                break;
            case 2:
                // For the student menu
                s.Menu(id);
                break;
            case 3:
                // For the Staff Menu
                
                break;
            case 4:
                // For the professor Menu
                p.Menu(id);
                break;
            case 5:
                // For the super menu
            default:
                System.out.println("Please enter a valid input");
                break;
        }
    }

    protected boolean checkPassword(String id, String path) {
        System.out.print("Please enter the password for the id : ");
        String inputPsw = sc.next();

        try (BufferedReader reader = new BufferedReader(new FileReader("Passwords/" + path + "/" + id + ".txt"))) {
            String actualPsw = reader.readLine();
            if (actualPsw.equals(inputPsw)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("There was a problem while verifying the password");
        }
        return false;
    }

}
