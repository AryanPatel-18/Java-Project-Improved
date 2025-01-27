package Basic.Tasks;

// All the imports
import java.io.*;
import java.util.Scanner;


public class Timetable {
    // All the objects for this class
    Scanner sc = new Scanner(System.in);

    // For checking if the division exists or not
    public boolean checkDivision(String division){
        // This is to check if the division entered in input is valid or not
        try(BufferedReader reader = new BufferedReader(new FileReader("TimeTables/list.txt"))) {
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals(division)){
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem while checking the divison");
        }
        return false;
    }

    // Setting the time table
    protected void setTimetable(String division) {
        // Setting a array to take in all the days and their lectures as input
        String arr[][] = new String[6][4];

        // Running the array to get input for each day
        for(int i = 0 ; i < arr.length ; i++){
            System.out.println("Enter lectures for " + getDay(i));
            for(int j = 0 ; j < arr[i].length ; j++){
                System.out.print("Enter lecture" + (j+1) + " :");
                String lecture = sc.next();
                arr[i][j] = lecture;
            }
            System.out.println("\n");
        }

        // Writing all the content in format in the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TimeTables/" + division + ".txt"))){
            for(int i = 0 ; i < arr.length ; i++){
                for(int j = 0 ; j < arr[i].length ; j++){
                    writer.write(arr[i][j] + System.lineSeparator());
                }
                writer.write("-----" + System.lineSeparator());

            }
        } catch (Exception e) {
            System.out.println("There was a problem while setting the timetable");
        }
    }

    // To call the protected function setTimeTable
    public void callSetTimeTable(String division){
        setTimetable(division);
    }

    // To get the day by the given input value
    String getDay(int day) {
        // This is to get the day but sending the value of index from the array
        if (day == 0)
            return "Monday";
        if (day == 1)
            return "Tuesday";
        if (day == 2)
            return "Wednesday";
        if (day == 3)
            return "Thursday";
        if (day == 4)
            return "Friday";
        if (day == 5)
            return "Saturday";
        return "Sunday";
    }


    // For viewing the time table
    public void viewTimeTable(String division) {
        
        // Getting all the values of the timetable into an array so that it can be easily be accessed
        String arr[][] = new String[6][4];
        try (BufferedReader reader = new BufferedReader(new FileReader("Timetables/" + division + ".txt"))) {
            String line;
            int i = -1;
            int j = 0;
            while ((line = reader.readLine()) != null) {
                i++;
                if (line.equals("-----")) {
                    j++;
                    i = -1;
                    continue;
                }
                arr[j][i] = line;
            }
        } catch (Exception e) {
            // Random error occuring everytime   
        }
        
        // Printing all the tables in the correct format
        System.out.println("\t---------------------- TIMETABLE ----------------------");
        System.out.println("\t\tFIRST\t\tSECOND\t\tTHIRD\t\tFOURTH\n\n");
        for(int i = 0 ; i < arr.length ; i++){
            System.out.print(getDay(i) + "   \t");
            for(int j = 0 ;j < arr[i].length ; j++){
                System.out.print(arr[i][j] + "\t\t");
            }
            System.out.println("\n");
        }
    }
}
