import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // GUI Login
        new LoginFrame().setVisible(true);

        System.out.println("------BUG MANAGMENT SYSTEM-------");

        Scanner input = new Scanner(System.in);

        System.out.print("enter your email: ");
        String email = input.nextLine();

        System.out.print("enter your password: ");
        String pass = input.nextLine();

        User u = Auth.Login(email, pass);

        while (u == null) {
            System.out.println("invalid login credentials");
            System.out.print("enter your email: ");
            email = input.nextLine();
            System.out.print("enter your password: ");
            pass = input.nextLine();
            u = Auth.Login(email, pass);
        }

        System.out.println("Welcome " + u.Name + "!");
        System.out.println("you are a/an " + u.UserType);
        System.out.println("--------------------");

        /* ================= ADMIN ================= */
        if (u.UserType.equalsIgnoreCase("admin")) {

            int choice = -1;

            while (choice != 0) {

                System.out.println("\n ADMIN MENU ");
                System.out.println("1- Create Project");
                System.out.println("2- View Projects");
                System.out.println("3- Generate Report");
                System.out.println("4- Assign Bug To Project");
                System.out.println("0- Logout");

                System.out.print("Choice: ");
                choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Create Project");
                        break;
                    case 2:
                        System.out.println("View Projects");
                        break;
                    case 3:
                        System.out.println("Generate Report");
                        break;
                    case 4:
                        System.out.println("Assign Bug");
                        break;
                    case 0:
                        System.out.println("Logout");
                        break;
                }
            }

            System.out.println("exiting!");
            Thread.sleep(10);
        }

        /* ================= TESTER ================= */
        else if (u.UserType.equalsIgnoreCase("tester") || u instanceof Tester) {

            Tester tes = (Tester) u;
            Project pro = new Project();
            List<Project> projects = pro.getProjects();

            boolean continueSelectingProjects = true;

            while (continueSelectingProjects) {

                System.out.println("\n------Choose Preferred Project------\n");

                for (int i = 0; i < projects.size(); i++) {
                    System.out.println(i + "\t" + projects.get(i).getName());
                }
                System.out.println(projects.size() + "\tExit");

                System.out.print("choice: ");
                int choice = input.nextInt();
                input.nextLine();

                if (choice == projects.size()) {
                    continueSelectingProjects = false;
                    continue;
                }

                Project selectedproject = projects.get(choice);

                System.out.println("\n------Actions------");
                System.out.println("1\tReport Bug");
                System.out.println("2\tExit");

                System.out.print("choice: ");
                int actionChoice = input.nextInt();
                input.nextLine();

                if (actionChoice == 1) {

                    System.out.print("Enter Bug Title: ");
                    String Bugtitle = input.nextLine();

                    System.out.print("Enter Bug Description: ");
                    String BugDescription = input.nextLine();

                    System.out.print("Enter Bug Severity: ");
                    String BugSeverity = input.nextLine();

                    tes.reportBug(
                            selectedproject,
                            Bugtitle,
                            BugDescription,
                            Severity.valueOf(BugSeverity.toUpperCase()),
                            null,
                            null
                    );

                    System.out.println("Bug Reported successfully!");
                } else {
                    continueSelectingProjects = false;
                }
            }
        }

        /* ================= DEVELOPER ================= */
        else if (u.UserType.equalsIgnoreCase("developer") || u instanceof Developer) {

            Developer dev = (Developer) u;

            System.out.println("\n--- Developer Mode ---");
            System.out.println("1- Use GUI");
            System.out.println("2- Use Console");
            System.out.print("Choice: ");

            int devChoice = input.nextInt();
            input.nextLine();

            if (devChoice == 1) {
                new DeveloperDashboardFrame(dev).setVisible(true);
            }
            else if (devChoice == 2) {
                DeveloperConsole.start(dev, input);
            }
            else {
                System.out.println("Invalid choice");
            }
        }
    }
}
