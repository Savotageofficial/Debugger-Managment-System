import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("------BUG MANAGMENT SYSTEM-------");


        //TODO this will be the Home Page
        Scanner input = new Scanner(System.in);

        System.out.print("enter your email: ");
        String email = input.nextLine();
        System.out.print("enter your password: ");
        String pass = input.nextLine();


        User u1 = Auth.Login(email , pass);

        if (u1 != null) {
            System.out.println("Welcome " + u1.Name + "!");
        }else {
            System.out.println("invalid login credentials");
        }









    }
}