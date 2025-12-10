import java.io.*;

public class Main {
    public static void main(String[] args) {


        //TODO this will be the Home Page
        User u1 = User.Login("mohamedsafwat7706@gmail.com" , "123456");

        System.out.println(User.getUsers("user"));


    }
}