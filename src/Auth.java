import java.util.List;

public class Auth {


    public static User Login(String email, String password) {
        email = email.replace(".com", ".txt");
        List<String> list = FilesStorage.readlines("developer/" + email); // target path to be changed

        if (password.equalsIgnoreCase(list.get(3))) {
            User u = new Developer(list.get(1), list.get(2), list.get(3), list.get(4));
            return u;
        }else {
            return null;
        }



    }
}
