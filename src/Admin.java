import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {

    public Admin(String id, String email, String name, String password) {
        super(id, email, name, password, "Admin");
    }
public Admin(){}

    public void creatdeveloper(String username, String email, String password, String Usertype) {
        String id = Auth.generateID("developer");
        List<String> lines = new ArrayList<>();
        lines.add(id);
        lines.add(username);
        lines.add(email);
        lines.add(password);
        lines.add("developer");
        lines.add("");
        lines.add("");
        FilesStorage.writefile("developer", lines, id + ".txt");
    }

    public void creattester(String username, String email, String password, String Usertype) {
        String id = Auth.generateID("tester");
        List<String> lines = new ArrayList<>();


        lines.add(id);
        lines.add(username);
        lines.add(email);
        lines.add(password);
        lines.add("tester");

        FilesStorage.writefile("tester", lines, id + ".txt");
    }
}





    @Override
    public List<User> getUsers() {
        String path = FilesStorage.FilePath + "admin";
        List<User> Users = new ArrayList<User>();
        File directory = new File(path);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                Users.add(new Admin(
                        FilesStorage.readline("admin/" + file.getName() , 0),
                        FilesStorage.readline("admin/" + file.getName() , 1),
                        FilesStorage.readline("admin/" + file.getName() , 2),
                        FilesStorage.readline("admin/" + file.getName() , 3)
                ));
            }
        }

        return Users;
    }
}
