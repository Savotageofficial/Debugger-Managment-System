import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("------BUG MANAGMENT SYSTEM-------");
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
                Thread.sleep(50);
            }

            System.out.println("exiting!");
            Thread.sleep(10);

        } else if (u.UserType.equalsIgnoreCase("developer") || u instanceof Developer) {
            Developer dev = (Developer) u;

            List<String> projectsIDs = dev.getAssignedProjectsIDs();

            List<Project> projects = new ArrayList<Project>();

            for(String projectid : projectsIDs){
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

            for (String bugid : selectedproject.getBugsid()){

                BugReport bug = FilesStorage.fetchBugData(bugid);
                bugReports.add(bug);
            }
            System.out.println("------Choose Bug to Alter------\n");


            for (int i = 0; i < bugReports.toArray().length; i++) {
                System.out.println(i + "\t" + bugReports.get(i).getTitle() + "\t" + bugReports.get(i).getStatus() + "\t" + bugReports.get(i).getSeverity());
                
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