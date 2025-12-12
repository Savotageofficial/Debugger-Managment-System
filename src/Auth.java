import java.util.List;

public class Auth {


    public static User Login(String email, String password) {
//        email = email.replace(".com", ".txt");
//        List<String> list = FilesStorage.readlines("developer/" + email); // target path to be changed

        Developer dev = new Developer();
        List<User> users = dev.getUsers();

        User u = null;

        for(User user : users){
            if (user.Email.equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
                u = user;
                break;
            }
        }
        return u;

    }
}
