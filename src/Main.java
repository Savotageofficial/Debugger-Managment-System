import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
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
        System.out.println("you are a/an " + u.UserType + "\n\n" + "--------------------");

        if (u.UserType.equalsIgnoreCase("admin")) {

            int choice = -1;

            while (choice != 0) {

                System.out.println("\n ADMIN MENU ");
                System.out.println("1- Create Project");
                System.out.println("2- View Projects");
                System.out.println("3- Generate Report");
                System.out.println("4- Assign Developer To Project");
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

        } else if (u.UserType.equalsIgnoreCase("tester") || u instanceof Tester) {

            Tester tes = (Tester) u;

            Project pro = new Project();

            List<Project> projects = pro.getProjects();

            boolean continueSelectingProjects = true;

            while (continueSelectingProjects) {
                System.out.println("\n------Choose Preferred Project------\n");

                for (int i = 0; i < projects.toArray().length; i++) {
                    Project project = projects.get(i);
                    System.out.println(i + "\t" + project.getName());
                }
                System.out.println(projects.size() + "\tExit");

                System.out.print("choice: ");
                int choice = input.nextInt();
                input.nextLine();
                if (choice == projects.size()) {
                    System.out.println("Goodbye!");
                    continueSelectingProjects = false;
                    continue;
                }

                Project selectedproject = projects.get(choice);

                List<BugReport> bugReports = new ArrayList<BugReport>();

                for (String bugid : selectedproject.getBugsid()) {
                    BugReport bug = FilesStorage.fetchBugData(bugid);
                    bugReports.add(bug);
                }

                System.out.println("\n------Current Bug Reports------\n");

                for (int i = 0; i < bugReports.toArray().length; i++) {
                    System.out.println(i + "\t" + bugReports.get(i).getTitle() + "\t"
                            + bugReports.get(i).getStatus() + "\t" + bugReports.get(i).getSeverity());
                }

                System.out.println("\n------Actions------");
                System.out.println("1\tReport Bug");
                System.out.println("2\tVerify Bug Fix");
                System.out.println("3\tExit");

                System.out.print("choice: ");
                int actionChoice = input.nextInt();
                input.nextLine();

                switch (actionChoice) {
                    case 1:

                        System.out.print("Enter Bug Title: ");
                        String Bugtitle = input.nextLine();

                        System.out.print("Enter Bug Description: ");
                        String BugDescription = input.nextLine();

                        System.out.print("Enter Bug Severity: ");
                        String BugSeverity = input.nextLine();

                        tes.reportBug(selectedproject, Bugtitle, BugDescription,
                                Severity.valueOf(BugSeverity.toUpperCase()), null, null);

                        System.out.println("Bug Reported successfully!");
                        break;

                    case 2:
                        System.out.println("\n------FIXED BUGS------");
                        BugReport bugInterface = new BugReport();

                        List<BugReport> allbugs = bugInterface.getBugReports();
                        List<BugReport> bugs = new ArrayList<>();

                        for (BugReport bug : allbugs) {
                            if (bug.getStatus() == Status.FIXED) {
                                bugs.add(bug);
                            }
                        }

                        if (bugs.toArray().length == 0) {
                            System.out.println("\n\nTHERE ARE NO FIXED BUGS");
                            break;
                        }

                        for (int i = 0; i < bugs.toArray().length; i++) {
                            System.out.println(i + "\t" + bugs.get(i).getTitle() + "\t"
                                    + bugs.get(i).getAssignedProject().getName());
                        }

                        System.out.print("choice: ");
                        int bugchoice = input.nextInt();
                        input.nextLine();

                        while (!(bugchoice >= 0 && bugchoice < bugs.toArray().length)) {
                            System.out.println("Invalid Choice");
                            System.out.println("choice: ");
                            bugchoice = input.nextInt();
                            input.nextLine();
                        }
                        BugReport selectedBug = bugs.get(bugchoice);

                        System.out.println("------" + selectedBug.getTitle() + "------\n");

                        System.out.println("Status : " + selectedBug.getStatus().name());

                        int newchoice = 0;

                        while (newchoice != 2 && newchoice != 1) {
                            System.out.print("verify fix? 1:Yes , 2:No : ");

                            newchoice = input.nextInt();
                            input.nextLine();
                            if (newchoice == 2) {
                                break;
                            } else {
                                System.out.println("Bug Closed!");

                                tes.verifyBug(selectedBug, Status.CLOSED);
                                break;
                            }
                        }

                    case 3:

                        continueSelectingProjects = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }

        } else if (u.UserType.equalsIgnoreCase("developer") || u instanceof Developer) {
            Developer dev = (Developer) u;

            List<String> projectsIDs = dev.getAssignedProjectsIDs();

            List<Project> projects = new ArrayList<Project>();

            for (String projectid : projectsIDs) {
                Project project = FilesStorage.fetchProjectData(projectid);
                projects.add(project);
            }

            System.out.println("------Choose Prefered Project------\n");

            for (int i = 0; i < projects.toArray().length; i++) {
                Project project = projects.get(i);

                System.out.println(i + "\t" + project.getName());

            }

            System.out.print("choice: ");
            int choice = input.nextInt();

            Project selectedproject = projects.get(choice);

            choice = 0;

            List<BugReport> bugReports = new ArrayList<BugReport>();

            for (String bugid : selectedproject.getBugsid()) {

                BugReport bug = FilesStorage.fetchBugData(bugid);
                bugReports.add(bug);
            }
            System.out.println("------Choose Bug to Alter------\n");

            System.out.println("\n------Choose Bug to Alter------\n");

            for (int i = 0; i < bugReports.toArray().length; i++) {
                System.out.println(i + "\t" + bugReports.get(i).getTitle() + "\t" + bugReports.get(i).getStatus() + "\t"
                        + bugReports.get(i).getSeverity());

            }

            System.out.print("choice: ");
            choice = input.nextInt();

        }

    }

    private static void printProgressBar(int iteration, int total) {
        int barLength = 50;

        double percent = ((double) iteration / total) * 100;

        int filledLength = (int) (barLength * iteration / total);


        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("█");
            } else {
                bar.append("-");
            }
        }

        String progress = String.format("\rProgress: |%s| %.1f%% (%d/%d)",
                bar.toString(), percent, iteration, total);
        System.out.print(progress);

        System.out.flush();
    }
}