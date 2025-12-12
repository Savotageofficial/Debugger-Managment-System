import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class admin extends User{
    public admin(String email, String name, String password, String userType) {
        super(email, name, password, userType);
    }
    public void creatuser(String username,String email,String passowrd, String Usertype)
    {

    }
    public void creatproject(String name, String description){

    }
    public void deletproject(String projectId){

    }
    public void Assignto(String projectId, String developerId){}



//shgaiyfgyiasiy




    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "developer";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(new Developer(
                        FilesStorage.readline("admin/" + file.getName() , 1),
                        FilesStorage.readline("admin/" + file.getName() , 2),
                        FilesStorage.readline("admin/" + file.getName() , 3),
                        FilesStorage.readline("admin/" + file.getName() , 4)
                ));
            }
        }

        return Users;
    }
}

