import java.io.File;
import java.util.ArrayList;
import java.util.List;

//abstract
public abstract class User {

    private String ID; //0
    public String Name; //1
    public String Email; //2
    private String Password; //3
    static private List<User> Users; //TODO make its default value get imported from a saved list in a file
    public String UserType; //4


    private static String filepath = "user";

//    public static List<User> ShowUsers() {
//        return Users;
//    }


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

    protected User(String name, String email, String password, String userType) {
        Email = email;
        Name = name;
        Password = password;
        UserType = userType;

        //TODO add this USER to the USERS List Before re-saving the list into the file


    }

//    public static int getUsersCount(String target) {
//        String path = FilesStorage.FilePath + target;
//        int fileCount = 0;
//        File directory = new File(path);
//        File[] files = directory.listFiles();
//
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) { // Only count actual files, not subdirectories
//                    fileCount++;
//                }
//            }
//        }
//
//        return fileCount;
//    }

//    public static List<String> getallUsers() {
//        String path = FilesStorage.FilePath + "user";
//        List<String> filesnames = new ArrayList<String>();
//        File directory = new File(path);
//        File[] files = directory.listFiles();
//
//        if (files != null) {
//            for (File file : files) {
//                filesnames.add(file.getName().replace(".txt", ".com"));
//            }
//        }
//
//        return filesnames;
//    }




    public void Logout() {
        //Todo بصراحه مش عارف هعملها ازاي

    }

    public abstract List<User> getUsers();
}
