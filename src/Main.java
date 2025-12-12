import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("------BUG MANAGMENT SYSTEM-------");


        //TODO this will be the Home Page
        Scanner input = new Scanner(System.in);

        System.out.print("enter your email: ");
        String email = input.nextLine();
        System.out.print("enter your password: ");
        String pass = input.nextLine();


        User u = Auth.Login(email , pass);

        while (u == null) {
            System.out.println("invalid login credentials");
            System.out.print("enter your email: ");
            email = input.nextLine();
            System.out.print("enter your password: ");
            pass = input.nextLine();
            u = Auth.Login(email , pass);
        }

        System.out.println("Welcome " + u.Name + "!");
        System.out.println("you are a/an " + u.UserType + "\n\n" + "--------------------");



        if (u.UserType.equalsIgnoreCase("admin")){
            System.out.println("awaiting admin flowchart & completion of class\n");
            for (int i = 0; i <= 100; i++) {
                printProgressBar(i, 100);
                // Simulate work being done
                Thread.sleep(50);
            }

            System.out.println("exiting!");
            Thread.sleep(10);

        } else if (u.UserType.equalsIgnoreCase("developer")) {


        }


    }

    private static void printProgressBar(int iteration, int total) {
        int barLength = 50; // Length of the progress bar in characters
        // Calculate the percentage completed
        double percent = ((double) iteration / total) * 100;
        // Calculate the number of filled characters
        int filledLength = (int) (barLength * iteration / total);

        // Build the bar string
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█"); // Use block character
            } else {
                bar.append("-");
            }
        }

        // Print the progress bar on the same line using carriage return (\r)
        String progress = String.format("\rProgress: |%s| %.1f%% (%d/%d)",
                bar.toString(), percent, iteration, total);
        System.out.print(progress);

        // Ensure the output is flushed immediately
        System.out.flush();
    } //can be removed safely
}