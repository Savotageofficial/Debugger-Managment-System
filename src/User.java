import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private String ID;
    public String Name;
    public String Email;
    private String Password;
    static private List<User> Users;
    public String UserType;


    private static String filepath = "user";

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    protected User() {
    }

    protected User(String id , String name, String email, String password, String userType) {
        ID = id;
        Email = email;
        Name = name;
        Password = password;
        UserType = userType;

    }






    public void Logout() {


    }

    public abstract List<User> getUsers();
}
