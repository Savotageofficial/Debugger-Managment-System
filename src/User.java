import java.util.List;

public abstract class User {

    private String ID;
    public String Name;
    public String Email;
    private String Password;
    static private List<User> Users; //TODO make its default value get imported from a saved list in a file
    public String UserType;

    public static List<User> ShowUsers() {
        return Users;
    }



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


    public User(String email, String name, String password, String userType) {
        Email = email;
        Name = name;
        Password = password;
        UserType = userType;

        //TODO add this USER to the USERS List Before re-saving the list into the file


    }

    public User Login(String email , String password){
        //TODO Check if email & password provided are included in the file storage then return the user with these credentials


        return null; // TODO Replace null with the User fetched from the file storage
    }
    public void Logout(){
        //Todo بصراحه مش عارف هعملها ازاي

    }
}
