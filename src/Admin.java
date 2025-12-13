import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User{

    public Admin(String id ,String email, String name, String password) {
        super(id , email, name, password, "admin");
    }

    public Admin() {
        super();
    }

    public void creatuser(String username, String email, String passowrd, String Usertype) {



    }
    public void creatproject(String name, String description){

    }
    public BugReport generateReport(String name, String description){
        return null;

    }
    public void deletproject(String projectId){

    }
    public void Assignto(String projectId, String developerId){}








    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "admin";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(FilesStorage.fetchAdmin(file.getName().replace(".txt" , "")));
            }
        }

        return Users;
    }
}

