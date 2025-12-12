import java.util.List;

public class Auth {


    public static User Login(String email, String password) {
//        email = email.replace(".com", ".txt");
//        List<String> list = FilesStorage.readlines("developer/" + email); // target path to be changed

        User u = null;
        Admin adm = new Admin();
        Developer dev = new Developer();
        List<User> devs = dev.getUsers();
        List<User> adms = adm.getUsers();

        if (u == null) {
            for (User user : devs) {
                if (user.Email.equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)) {
                    u = user;
                    break;
                }
            }
        }

        if (u == null){
            for(User user : adms){
                if (user.Email.equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)){
                    u = user;
                    break;
                }
            }
        }




        return u;

    }
}
