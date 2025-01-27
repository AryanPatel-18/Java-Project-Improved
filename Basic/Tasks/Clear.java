package Basic.Tasks;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Clear {
    private void deleteUserFiles(String id, String path){
        String paths[] = getPath(id, path);
        
        for(int i = 0 ; i< paths.length ;i++){
            Path filePath = Paths.get(paths[i] + "/" + id + ".txt");
            System.out.println(paths[i] + "/" + id + ".txt");
            try {
                Files.delete(filePath);
            } catch (Exception e) {
                System.out.println("There was a problem while deleting " + id + ".txt file");
            }
        }
    }

    public void callDeleteUser(String id, String path){
        deleteUserFiles(id, path);
    }
    
    // For getting the path of the file
    private String[] getPath(String id, String path){
        if(path.equals("Student")){
            String paths[] = {"Ids/" + path , "Passwords/" + path,"Reminders/" + path + "/public","Reminders/" + path + "/Private"};
            return paths;
        }else{
            String paths[] = {"Ids/" + path , "Passwords/" + path,"Reminders/" + path + "/public","Reminders/" + path + "/Private", "Attendence/"};
            return paths;
        } 
    }
}
