import java.util.Scanner;

class Main{
    public static void main(String[] args){

        // All the objects for the main class
        Scanner sc = new Scanner(System.in);
        // Login l = new Login(); 
        // Create c = new Create();

        // First setup menu
            System.out.println("------------------------------------\n");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit\n");
            System.out.println("------------------------------------\n\n");
            System.out.print("Please enter what you want to do : ");
            int option = sc.nextInt();
    
            switch (option) {
                case 1:
                    // l.login();
                    break;
                case 2:
                    // c.studentUser();
                    break;    
                case 3:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid input");
                    break;
            }

    }


}